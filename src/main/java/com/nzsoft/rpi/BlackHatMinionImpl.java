package com.nzsoft.rpi;

public final class BlackHatMinionImpl implements Minion {

	private static final int BASE_INTERVAL = 250;

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
		// morse code array contains each character encoded in String
		System.out.println("BlackHatMinion : Will now create hacker file on Drive....");
		// TODO CREATE BLOCKING FILE/FOLDER ON DRIVE SO THAT ALL OTHER MINIONS ARE
		// HACKED:
		// DriveHelper drive = new DriveHelper();
		// Drive service = drive.getDriveService();
		System.exit(0);
	}
}
