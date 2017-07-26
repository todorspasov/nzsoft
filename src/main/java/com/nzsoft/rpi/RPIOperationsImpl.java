package com.nzsoft.rpi;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class RPIOperationsImpl implements RPIOperations {

    // For pin numbering (RPi 3, Model B), see:
    // http://pi4j.com/pins/model-3b-rev1.html
    private static final Pin LED_OUTPUT_PIN = RaspiPin.GPIO_01;
    private static final Pin BUTTON_INPUT_PIN = RaspiPin.GPIO_25;

    private final GpioController gpio;
    private final GpioPinDigitalInput button;
    private final GpioPinDigitalOutput led;

    public RPIOperationsImpl() {
        gpio = GpioFactory.getInstance();
        button = gpio.provisionDigitalInputPin(BUTTON_INPUT_PIN, PinPullResistance.PULL_DOWN);
        led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.LOW);
        led.setShutdownOptions(true, PinState.LOW);
    }

    @Override
    public void changeLED(LEDState state) {
        switch (state) {
            case ON:
                led.high();
                System.out.println("LED ON");
                break;
            case OFF:
                led.low();
                System.out.println("LED OFF");
                break;
        }
    }

    @Override
    public void registerButtonListener(GpioPinListenerDigital listener) {
        button.addListener(listener);
    }
}