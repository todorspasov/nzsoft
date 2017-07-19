package com.nzsoft.rpi;

import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * Supported RPI Operations
 */
public interface RPIOperations {

	/**
	 * Contains the possible states of a LED
	 */
	enum LEDState {
		ON, OFF
	}

	/**
	 * Changes the LED light of the RPI
	 * 
	 * @param state
	 *            the {@link LEDState} to change to
	 */
	void changeLED(LEDState state);

    /**
     * Registers a listener for button clicks
     *
     * @param listener
     *      the {@link GpioPinListenerDigital} that activates when the button is pressed
     */
    void registerButtonListener(GpioPinListenerDigital listener);
}
