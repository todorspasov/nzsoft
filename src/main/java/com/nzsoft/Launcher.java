package com.nzsoft;

import com.nzsoft.rpi.MockRPIOperationsImpl;
import com.nzsoft.rpi.RPIOperations;
import com.nzsoft.tasks.MockTaskOperationsImpl;
import com.nzsoft.tasks.TaskOperations;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

import static com.nzsoft.rpi.RPIOperations.LEDState.OFF;
import static com.nzsoft.rpi.RPIOperations.LEDState.ON;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class Launcher {

    public static void main(String[] args) throws Exception {
        RPIOperations rpiOperations = new MockRPIOperationsImpl();
        TaskOperations taskOperations = new MockTaskOperationsImpl();

        ButtonListener button = new ButtonListener();
        rpiOperations.registerButtonListener(button);

        while (true) {
            //if there is an existing task get it, otherwise sleep
            String taskName = taskOperations.getTask();
            if (isNotBlank(taskName)) {
                //turn on the LED
                rpiOperations.changeLED(ON);
                //wait for the button to be clicked
                while (!button.isClicked()) {
                    //sleep 100ms then check again the button state
                    System.out.println("Processing task...");
                    sleep(100);
                }
                //mark the task as completed
                taskOperations.markTaskCompleted(taskName);
                //turn off the LED
                rpiOperations.changeLED(OFF);
                //sleep
                sleep(1000);
            } else {
                System.out.println("No tasks available. Sleeping for 1sec");
                sleep(1000);
            }
        }
    }

    static final class ButtonListener implements GpioPinListenerDigital {

        private boolean clicked;

        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            System.out.println(" --> BUTTON PIN STATE: " + event.getPin() + " = "
                    + event.getState());
            clicked = event.getState() == PinState.HIGH;
        }

        public boolean isClicked() {
            return clicked;
        }
    }
}
