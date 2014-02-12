package port.sessionserver;

import java.io.Serializable;

import port.ParticipantChoice;

public interface SessionParticipantDescription extends ServerPortDescription {
	ParticipantChoice getParticipantChoice();
	void getParticipantChoice(ParticipantChoice aChoice);
	Serializable getApplicationDefinedDescription();
	void setApplicationDefinedDescription (Serializable aParticipantDescription);

}
