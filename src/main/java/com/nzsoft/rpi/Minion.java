package com.nzsoft.rpi;

/**
 * The minion's behaviour
 */
public interface Minion {

	/**
	 * Contains the possible states of a LED
	 */
	enum LedState {
		ON, OFF
	}

	/**
	 * Switches the minion's LED light
	 * 
	 * @param state the {@link LedState} to change to
	 */
	void switchLed(LedState state);

	/**
	 * @return whether the button has been pressed
	 */
	boolean isButtonPressed();

	/**
	 * @param morseCode the morse code to emit, each array entry is a single letter
	 * @throws InterruptedException
	 */
	void emitMorseCode(String[] morseCode) throws InterruptedException;
}
