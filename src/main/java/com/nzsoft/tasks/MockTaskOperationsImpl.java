package com.nzsoft.tasks;

import java.util.UUID;

public class MockTaskOperationsImpl implements TaskOperations {

	@Override
	public String getTask() {
		if (Math.random() < 0.3) {
			// 30% chance to have a task
			String taskName = UUID.randomUUID().toString();
			System.out.println("Returned task " + taskName);
			return taskName;
		}
		return null;
	}

	@Override
	public Priority getTaskPriority(String taskName) {
		Priority taskPriority = Math.random() < 0.33 ? Priority.LOW
				: (Math.random() < 0.66 ? Priority.MEDIUM : Priority.HIGH);
		System.out.println(String.format("Task %s has priority %s", taskName, taskPriority));
		return taskPriority;
	}

	@Override
	public void markTaskCompleted(String taskName) {
		System.out.println(String.format("Marking task with name %s as completed.", taskName));
	}

	@Override
	public String getTaskBody(String taskName) {
		String[] bodies = new String[] { "sos sos" };
		return bodies[(int) (Math.random() * bodies.length)];
	}

}
