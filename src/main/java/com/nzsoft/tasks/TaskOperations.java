package com.nzsoft.tasks;

/**
 * Contains Google Drive Operations related to tasks (files). A task represents
 * a file. The task priority is determined by the filename.
 */
public interface TaskOperations {
	/**
	 * Represents priority of a task
	 */
	public enum Priority {
		HIGH, MEDIUM, LOW
	}

	/**
	 * Gets a task from the google drive folder.
	 * 
	 * @return the name of the task, if such exists in the drive folder
	 */
	String getTask();

	/**
	 * Gets the {@link Priority} of a task
	 * 
	 * @param taskName
	 *            the name of the task
	 * @return the {@link Priority} of the task
	 */
	Priority getTaskPriority(String taskName);

	/**
	 * @param taskName
	 *            the name of the task to complete
	 */
	void markTaskCompleted(String taskName);
}
