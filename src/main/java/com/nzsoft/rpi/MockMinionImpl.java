package com.nzsoft.rpi;

public final class MockMinionImpl implements Minion {

    @Override
    public void changeLED(LEDState state) {
        System.out.println("LED is " + state.name());
    }

    @Override
    public boolean isButtonPressed() {
        return Math.random() < 0.1;
    }

}
