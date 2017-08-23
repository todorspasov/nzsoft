package com.nzsoft.tasks;

import java.util.UUID;

public class MockTaskOperationsImpl implements TaskOperations {

	@Override
	public String getTask() {
		if (Math.random() < 0.3) {
			// 30% chance to have a task
			String taskId = UUID.randomUUID().toString();
			System.out.println("Returned task " + taskId);
			return taskId;
		}
		return null;
	}

	@Override
	public Priority getTaskPriority(String taskId) {
		Priority taskPriority = Math.random() < 0.33 ? Priority.LOW
				: (Math.random() < 0.66 ? Priority.MEDIUM : Priority.HIGH);
		System.out.println(String.format("Task %s has priority %s", taskId, taskPriority));
		return taskPriority;
	}

	@Override
	public void markTaskCompleted(String taskId) {
		System.out.println(String.format("Marking task with Id %s as completed.", taskId));
	}

	@Override
	public String getTaskBody(String taskId) {
		String[] bodies = new String[] { "sos sos" };
		return bodies[(int) (Math.random() * bodies.length)];
	}

}
