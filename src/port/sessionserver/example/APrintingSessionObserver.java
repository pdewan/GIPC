package port.sessionserver.example;

import java.io.Serializable;

import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionObserver;

public class APrintingSessionObserver implements SessionObserver {

	@Override
	public void joined(SessionParticipantDescription sessionClientDescription) {
			System.out.println("Session member has joined: " + sessionClientDescription);
		
	}

	@Override
	public void left(SessionParticipantDescription sessionClientDescription) {
		System.out.println("Session member has left: " + sessionClientDescription);
		
	}

	@Override
	public void newState(Serializable newState) {
		System.out.println("New state: " + newState);

	}

}
