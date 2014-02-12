package port.sessionserver;

import java.io.Serializable;
import java.rmi.Remote;

public interface SessionObserver extends Remote {
	// may need to add PraticipantChoice as an argument
	void joined (SessionParticipantDescription aSessionClientDescription);
	void left (SessionParticipantDescription aSessionClientDescription);
	void newState(Serializable newState);

}
