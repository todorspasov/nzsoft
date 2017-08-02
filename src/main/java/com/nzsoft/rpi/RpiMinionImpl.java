package com.nzsoft.rpi;

import com.pi4j.io.gpio.*;

public final class RpiMinionImpl implements Minion {

    // For pin numbering (RPi 3, Model B), see:
    // http://pi4j.com/pins/model-3b-rev1.html
    private static final Pin LED_OUTPUT_PIN = RaspiPin.GPIO_25;
    private static final Pin BUTTON_INPUT_PIN = RaspiPin.GPIO_01;

    private final GpioPinDigitalInput button;
    private final GpioPinDigitalOutput led;

    public RpiMinionImpl() {
        final GpioController gpio = GpioFactory.getInstance();
        button = gpio.provisionDigitalInputPin(BUTTON_INPUT_PIN, PinPullResistance.PULL_UP);
        led = gpio.provisionDigitalOutputPin(LED_OUTPUT_PIN, "MinionLED", PinState.LOW);
        led.setShutdownOptions(true, PinState.LOW);
    }

    @Override
    public void switchLed(LedState state) {
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
    public boolean isButtonPressed() {
        return button.getState() == PinState.LOW;
    }

}