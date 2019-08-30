package com.nzsoft.rpi;

public final class BlackHatMinionImpl implements Minion {

	@Override
	public void switchLed(LedState state) {
		System.out.println("LED is " + state.name());
	}

	@Override
	public boolean isButtonPressed() {
		return Math.random() < 0.1;
	}

	@Override
	public void emitMorseCode(String[] morseCode) {
		System.out.println("BlackHatMinion");

		System.exit(0);
	}
}
