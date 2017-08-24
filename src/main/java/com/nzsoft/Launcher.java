package com.nzsoft;

import com.nzsoft.rpi.MockMinionImpl;
import com.nzsoft.morse.MorseCodes;
import com.nzsoft.rpi.Minion;
import com.nzsoft.rpi.RpiMinionImpl;
import com.nzsoft.tasks.DriveTaskOperations;
import com.nzsoft.tasks.MockTaskOperationsImpl;
import com.nzsoft.tasks.TaskOperations;

import static com.nzsoft.rpi.Minion.LedState.OFF;
import static com.nzsoft.rpi.Minion.LedState.ON;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class Launcher {

    private static final int MORSE_CODE_EMIT_RETRIES = 1;

	public static void main(String[] args) throws Exception {
        TaskOperations taskOperations = null;
        Minion minion = null;

        if ("test".equalsIgnoreCase(args[0])) {
            taskOperations = new MockTaskOperationsImpl();
        } else {
        	taskOperations = new DriveTaskOperations();
        }
        if ("testdrive".equalsIgnoreCase(args[0])) {
        	minion = new MockMinionImpl();
        } else {
        	minion = new RpiMinionImpl();
        }

        while (true) {
            //if there is an existing task get it, otherwise sleep
            String taskName = taskOperations.getTask();
            if (isNotBlank(taskName)) {
                //turn on the LED
                minion.switchLed(ON);
                //wait for the button to be clicked
                while (!minion.isButtonPressed()) {
                    //sleep 100ms then check again the button state
                    System.out.println("Waiting to hit the button to accept task...");
                    sleep(100);
                }
                String taskBody = taskOperations.getTaskBody(taskName);
                for (int i = 0; i < MORSE_CODE_EMIT_RETRIES; i++) {
                    //encode the task body into morse and display it on the minion.
                    minion.emitMorseCode(MorseCodes.convertToMorse(taskBody));
                    sleep(10000);
                }
                while (!minion.isButtonPressed()) {
                    //sleep 100ms then check again the button state
                    System.out.println("Waiting to hit the button to complete task...");
                    sleep(100);
                }
                //mark the task as completed
                taskOperations.markTaskCompleted(taskName);
                //turn off the LED
                minion.switchLed(OFF);
                //sleep
                sleep(1000);
            } else {
                System.out.println("No tasks available. Waiting...");
                sleep(1000);
            }
        }
    }
}
