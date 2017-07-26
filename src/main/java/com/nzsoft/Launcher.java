package com.nzsoft;

import static com.nzsoft.rpi.RPIOperations.LEDState.OFF;
import static com.nzsoft.rpi.RPIOperations.LEDState.ON;

import java.util.Timer;
import java.util.TimerTask;

import org.apache.commons.lang3.StringUtils;

import com.nzsoft.rpi.MockRPIOperationsImpl;
import com.nzsoft.rpi.RPIOperations;
import com.nzsoft.tasks.MockTaskOperationsImpl;
import com.nzsoft.tasks.TaskOperations;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class Launcher {

    public static void main(String[] args) throws Exception {
        RPIOperations rpiOperations = new MockRPIOperationsImpl();
        TaskOperations taskOperations = new MockTaskOperationsImpl();
        final ButtonListener listener = new ButtonListener();
        rpiOperations.registerButtonListener(listener);

        //a second thread randomly clicks the button.
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                listener.isClicked = !listener.isClicked;
            }
        };
        Timer timer = new Timer(true);
        timer.scheduleAtFixedRate(timerTask, 0, 5000);
        while (true) {
            //if there is an existing task get it, otherwise sleep
            String taskName = taskOperations.getTask();
            if (StringUtils.isNotBlank(taskName)) {
                //turn on the LED
                rpiOperations.changeLED(ON);
                //wait for the button to be clicked
                while (!listener.isClicked) {
                    //sleep 100ms then check again the button state
                    Thread.sleep(100);
                }
                //mark the task as completed
                taskOperations.markTaskCompleted(taskName);
                //turn off the LED
                rpiOperations.changeLED(OFF);
                //sleep
                Thread.sleep(1000);
            } else {
                System.out.println("No tasks available. Sleeping for 1sec");
                Thread.sleep(1000);
            }
        }
    }

    static final class ButtonListener implements GpioPinListenerDigital {

        boolean isClicked;
        @Override
        public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
            System.out.println(" --> BUTTON PIN STATE: " + event.getPin() + " = "
                    + event.getState());
            isClicked = event.getState() == PinState.HIGH;
        }
    }
}
