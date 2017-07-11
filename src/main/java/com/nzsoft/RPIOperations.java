package com.nzsoft;

/**
 * Supported RPI Operations
 */
public interface RPIOperations {
	/**
	 * Contains the possible states of a LED light
	 */
	public enum LEDState {
		GREEN, YELLOW, RED, OFF
	}

	/**
	 * Changes the LED light of the RPI
	 * 
	 * @param state
	 *            the {@link LEDState} to change to
	 */
	void changeLED(LEDState state);
}
