package com.nzsoft.radio.operations;

import com.nzsoft.radio.signal.RadioSignal;

/**
 * Contains Google Drive Operations related to radio signals. A signal
 * represents a string.
 */
public interface RadioOperations {
	/**
	 * Represents priority of a signal
	 */
	public enum Priority {
		HIGH, MEDIUM, LOW
	}

	/**
	 * Gets a signal (identificator) from the google drive folder.
	 * 
	 * @return the Id of the signal, if such exists in the drive folder
	 */
	RadioSignal getSignal();

	/**
	 * Gets the {@link Priority} of a signal
	 * 
	 * @param signalId the Id of the signal
	 * @return the {@link Priority} of the signal
	 */
	Priority getSignalPriority(String signalId);

	/**
	 * @param signalId the Id of the signal to mark as complete
	 */
	void markSignalReceived(String signalId);
}
