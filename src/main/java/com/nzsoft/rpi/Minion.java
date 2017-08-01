package com.nzsoft.rpi;

/**
 * The minion's behaviour
 */
public interface Minion {

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
	 * @return whether the button has been pressed
	 */
	boolean isButtonPressed();

}
