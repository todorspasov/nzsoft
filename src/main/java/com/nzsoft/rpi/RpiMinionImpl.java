package com.nzsoft.rpi;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.Pin;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public final class RpiMinionImpl implements Minion {

	/**
	 * Interval length in milliseconds of a unit of morse code.
	 * Rules:
	 * 1. The length of a dot is one unit
	 * 2. The length of a dash is three units
	 * 3. The space between parts of the same letter is one unit
	 * 4. The space between letters is three units
	 * 5. The space between words is seven units
	 */
	private static final int BASE_INTERVAL = 250;

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

	@Override
	public void emitMorseCode(String[] morseCode) {
		//morse code array contains each character encoded in String
		try {
			int keyCounter = 0;
			for (String morseKey : morseCode) {
				int beforeInterval = 0;
				if (keyCounter++ > 0) {
					//at the end of morse key character emit 3 units of darkness
					beforeInterval = 3 * BASE_INTERVAL;
				}
				//morseKey consists of . -
				for (int i = 0; i < morseKey.length(); i++) {
					if (i > 0) {
						//one unit of darkness between dashes and dots
						Thread.sleep(BASE_INTERVAL);
					}
					char currentChar = morseKey.charAt(i);
					int sleepInterval = 0;
					boolean shouldTurnOn = false;
					if (currentChar == '.') {
						sleepInterval = BASE_INTERVAL;
						shouldTurnOn = true;
					} else if (currentChar == '-') {
						shouldTurnOn = true;
						sleepInterval = 3 * BASE_INTERVAL;
					} else if (currentChar == ' ') {
						beforeInterval += 3 * BASE_INTERVAL;
					} else {
						throw new IllegalArgumentException(String.format("Invalid char %s detected in morse code text", currentChar));
					}
					if (beforeInterval > 0) {
						Thread.sleep(beforeInterval);
					}
					if (shouldTurnOn) {
						switchLed(LedState.ON);
					}
					Thread.sleep(sleepInterval);
					if (shouldTurnOn) {
						switchLed(LedState.OFF);
					}
				}
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}

}