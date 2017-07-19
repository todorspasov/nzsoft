package com.nzsoft.rpi;

import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MockRPIOperationsImpl implements RPIOperations {

    @Override
    public void changeLED(LEDState state) {
        System.out.println("LED is " + state.name());
    }

    @Override
    public void registerButtonListener(GpioPinListenerDigital listener) {
        System.out.println("Registered button listener");
    }
}
