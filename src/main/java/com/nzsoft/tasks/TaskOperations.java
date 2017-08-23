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
	 * @return the Id of the task, if such exists in the drive folder
	 */
	String getTask();

	/**
	 * Gets the {@link Priority} of a task
	 * 
	 * @param taskId
	 *            the Id of the task
	 * @return the {@link Priority} of the task
	 */
	Priority getTaskPriority(String taskId);

	/**
	 * @param taskId
	 *            the Id of the task
	 * @return the body of the task
	 */
	String getTaskBody(String taskId);

	/**
	 * @param taskId
	 *            the Id of the task to complete
	 */
	void markTaskCompleted(String taskId);
}
