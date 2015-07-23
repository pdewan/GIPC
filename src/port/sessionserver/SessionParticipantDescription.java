package port.sessionserver;

import java.io.Serializable;

import port.ParticipantChoice;

public interface SessionParticipantDescription extends ServerPortDescription {
	public ParticipantChoice getParticipantChoice() ;

	public void setParticipantChoice(ParticipantChoice participantChoice) ;
	Serializable getApplicationDefinedDescription();
	void setApplicationDefinedDescription (Serializable aParticipantDescription);
	Serializable getParticipantDescription();
	void setParticipantDescription(Serializable participantDescription);

}
