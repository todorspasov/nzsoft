package com.nzsoft.radio.operations;

import java.util.UUID;

import com.nzsoft.radio.signal.RadioSignal;

public class MockRadioOperationsImpl implements RadioOperations {

	@Override
	public RadioSignal getSignal() {
		if (Math.random() < 0.3) {
			// 30% chance to have an incoming signal
			String signalId = UUID.randomUUID().toString();
			System.out.println("Returned signal Id: " + signalId);
			String[] bodies = new String[] { "sos sos" };
			return new RadioSignal(signalId, bodies[(int) (Math.random() * bodies.length)]);
		}
		return null;
	}

	@Override
	public Priority getSignalPriority(String signalId) {
		Priority signalPriority = Math.random() < 0.33 ? Priority.LOW
				: (Math.random() < 0.66 ? Priority.MEDIUM : Priority.HIGH);
		System.out.println(String.format("Signal %s has priority %s", signalId, signalPriority));
		return signalPriority;
	}

	@Override
	public void markSignalReceived(String signalId) {
		System.out.println(String.format("Marking signal with Id %s as completed.", signalId));
	}

}
