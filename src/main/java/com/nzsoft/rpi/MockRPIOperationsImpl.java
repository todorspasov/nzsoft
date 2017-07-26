package com.nzsoft.rpi;

import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import java.util.Timer;
import java.util.TimerTask;

public class MockRPIOperationsImpl implements RPIOperations {

    @Override
    public void changeLED(LEDState state) {
        System.out.println("LED is " + state.name());
    }

    @Override
    public void registerButtonListener(final GpioPinListenerDigital listener) {
        System.out.println("Registered button listener");

        //a second thread randomly clicks the button.
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                press();
                zzz();
                release();
            }

            private void release() {
                listener.handleGpioPinDigitalStateChangeEvent(new GpioPinDigitalStateChangeEvent(new Object(), null, PinState.LOW));
            }

            private void press() {
                listener.handleGpioPinDigitalStateChangeEvent(new GpioPinDigitalStateChangeEvent(new Object(), null, PinState.HIGH));
            }

            private void zzz() {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
    }

}
