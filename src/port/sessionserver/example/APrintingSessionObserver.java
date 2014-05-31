package port.sessionserver.example;

import java.io.Serializable;

import port.sessionserver.SessionObserver;
import port.sessionserver.SessionParticipantDescription;

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
