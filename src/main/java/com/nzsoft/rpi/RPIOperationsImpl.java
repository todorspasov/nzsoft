package com.nzsoft.rpi;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class RPIOperationsImpl implements RPIOperations {

    // TODO use the correct pin numbers
    private static final Pin BUTTON_INPUT_PIN = RaspiPin.GPIO_18;
    private static final Pin LED_OUTPUT_PIN = RaspiPin.GPIO_01;

    private final GpioController gpio;
    private final GpioPinAnalogInput button;
    private final GpioPinDigitalOutput led;

    public RPIOperationsImpl() {
        gpio = GpioFactory.getInstance();
        button = gpio.provisionAnalogInputPin(BUTTON_INPUT_PIN);
        led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.LOW);
        led.setShutdownOptions(true, PinState.LOW);
    }

    @Override
    public void changeLED(LEDState state) {
        switch (state) {
            case ON:
                led.high();
                break;
            case OFF:
                led.low();
                break;
        }
    }

    @Override
    public void registerButtonListener(GpioPinListenerDigital listener) {
        button.addListener(listener);
    }
}