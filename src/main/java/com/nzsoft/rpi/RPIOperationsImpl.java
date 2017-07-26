package com.nzsoft.rpi;

import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class RPIOperationsImpl implements RPIOperations {

    private static final Pin BUTTON_INPUT_PIN = RaspiPin.GPIO_26;
    private static final Pin LED_OUTPUT_PIN = RaspiPin.GPIO_18;

    private final GpioController gpio;
    private final GpioPinDigitalInput button;
    private final GpioPinDigitalOutput led;

    public RPIOperationsImpl() {
        gpio = GpioFactory.getInstance();
        button = gpio.provisionDigitalInputPin(BUTTON_INPUT_PIN, PinPullResistance.PULL_DOWN);
        led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.HIGH);
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