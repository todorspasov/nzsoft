package com.nzsoft.rpi;

public final class MockMinionImpl implements Minion {

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
		try {
			int keyCounter = 0;
			for (String morseKey : morseCode) {
				int beforeInterval = 0;
				if (keyCounter++ > 0) {
					// at the end of morse key character emit 3 units of
					// darkness
					beforeInterval = 3 * BASE_INTERVAL;
				}
				// morseKey consists of . -
				for (int i = 0; i < morseKey.length(); i++) {
					if (i > 0) {
						// one unit of darkness between dashes and dots
						Thread.sleep(BASE_INTERVAL);
					}
					char currentChar = morseKey.charAt(i);
					int sleepInterval = 0;
					boolean shouldTurnOn = false;
					if (currentChar == '.') {
						sleepInterval = BASE_INTERVAL;
						shouldTurnOn = true;
						System.out.println(".");
					} else if (currentChar == '-') {
						shouldTurnOn = true;
						sleepInterval = 3 * BASE_INTERVAL;
						System.out.println("-");
					} else if (currentChar == ' ') {
						beforeInterval += 3 * BASE_INTERVAL;
						System.out.println("NEW WORD");
					} else {
						throw new IllegalArgumentException(
								String.format("Invalid char %s detected in morse code text", currentChar));
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
