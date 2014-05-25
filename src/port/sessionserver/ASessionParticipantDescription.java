package port.sessionserver;

import java.io.Serializable;

import port.ParticipantChoice;

public class ASessionParticipantDescription extends AServerPortDescription implements SessionParticipantDescription{
	protected ParticipantChoice paricipantChoice;
	protected Serializable participantDescription;
	public ASessionParticipantDescription (String aHost, String anID, String aName, ParticipantChoice aChoice, Serializable aParticipantDescription) {
		super(aHost, anID, aName);
		paricipantChoice= aChoice;
		participantDescription = aParticipantDescription;
	}
	public ASessionParticipantDescription (String aHost, String anID, String aName, ParticipantChoice aChoice) {
		super(aHost, anID, aName);
		paricipantChoice= aChoice;
	}
	public ASessionParticipantDescription (String aHost, String anID, String aName, Serializable aParticipantDescription) {
		super(aHost, anID, aName);
		paricipantChoice= ParticipantChoice.SERVER_ONLY;
	}
	public ASessionParticipantDescription (ServerPortDescription aServerPortDescription, ParticipantChoice aChoice, Serializable aParticipantDescription) {
		super(aServerPortDescription.getHost(), aServerPortDescription.getID(), aServerPortDescription.getName());
		paricipantChoice= aChoice;
		participantDescription = aParticipantDescription;
	}
	public ASessionParticipantDescription (ParticipantChoice aChoice) {
		paricipantChoice= aChoice;
	}
	public ASessionParticipantDescription () {
	}

	@Override
	public ParticipantChoice getParticipantChoice() {
		return paricipantChoice;
	}

	@Override
	public void getParticipantChoice(ParticipantChoice aChoice) {
		paricipantChoice  = aChoice;
		
	}
	@Override
	public Serializable getApplicationDefinedDescription() {
		return participantDescription;
	}
	@Override
	public void setApplicationDefinedDescription(Serializable aParticipantDescription) {		
		participantDescription = aParticipantDescription;
	}

}
