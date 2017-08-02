package com.nzsoft.rpi;

public final class MockMinionImpl implements Minion {

    @Override
    public void switchLed(LedState state) {
        System.out.println("LED is " + state.name());
    }

    @Override
    public boolean isButtonPressed() {
        return Math.random() < 0.1;
    }

}
