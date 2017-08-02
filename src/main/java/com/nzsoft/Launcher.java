package com.nzsoft;

import com.nzsoft.rpi.MockMinionImpl;
import com.nzsoft.rpi.Minion;
import com.nzsoft.tasks.MockTaskOperationsImpl;
import com.nzsoft.tasks.TaskOperations;

import static com.nzsoft.rpi.Minion.LedState.OFF;
import static com.nzsoft.rpi.Minion.LedState.ON;
import static java.lang.Thread.sleep;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

public final class Launcher {

    public static void main(String[] args) throws Exception {
        Minion minion = new MockMinionImpl();
        TaskOperations taskOperations = new MockTaskOperationsImpl();

        while (true) {
            //if there is an existing task get it, otherwise sleep
            String taskName = taskOperations.getTask();
            if (isNotBlank(taskName)) {
                //turn on the LED
                minion.switchLed(ON);
                //wait for the button to be clicked
                while (!minion.isButtonPressed()) {
                    //sleep 100ms then check again the button state
                    System.out.println("Processing task...");
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
