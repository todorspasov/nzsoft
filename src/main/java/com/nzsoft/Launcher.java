package com.nzsoft;

import com.nzsoft.rpi.MockRPIOperationsImpl;
import com.nzsoft.rpi.RPIOperations;
import com.nzsoft.rpi.RPIOperationsImpl;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import static com.nzsoft.rpi.RPIOperations.LEDState.OFF;
import static com.nzsoft.rpi.RPIOperations.LEDState.ON;

public class Launcher {

    public static void main(String[] args) throws Exception {
        RPIOperations rpiOperations = new MockRPIOperationsImpl();
        rpiOperations.registerButtonListener(new ButtonListener());

        while (true) {
            rpiOperations.changeLED(ON);
            Thread.sleep(1000);
            rpiOperations.changeLED(OFF);
        }
    }

    static final class ButtonListener implements GpioPinListenerDigital {

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            System.out.println(" --> BUTTON PIN STATE: " + event.getPin() + " = "
                    + event.getState());
        }
    }
}
