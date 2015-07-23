package port.sessionserver;

import java.io.Serializable;

import port.ParticipantChoice;

public class ASessionParticipantDescription extends AServerPortDescription implements SessionParticipantDescription{
	protected ParticipantChoice participantChoice;
	
	
	
	protected Serializable participantDescription;
	public ASessionParticipantDescription (String aHost, String anID, String aName, ParticipantChoice aChoice, Serializable aParticipantDescription) {
		super(aHost, anID, aName);
		participantChoice= aChoice;
		participantDescription = aParticipantDescription;
	}
	public ASessionParticipantDescription (String aHost, String anID, String aName, ParticipantChoice aChoice) {
		super(aHost, anID, aName);
		participantChoice= aChoice;
	}
	public ASessionParticipantDescription (String aHost, String anID, String aName, Serializable aParticipantDescription) {
		super(aHost, anID, aName);
		participantChoice= ParticipantChoice.SERVER_ONLY;
	}
	public ASessionParticipantDescription (ServerPortDescription aServerPortDescription, ParticipantChoice aChoice, Serializable aParticipantDescription) {
		super(aServerPortDescription.getHost(), aServerPortDescription.getID(), aServerPortDescription.getName());
		participantChoice= aChoice;
		participantDescription = aParticipantDescription;
	}
	public ASessionParticipantDescription (ParticipantChoice aChoice) {
		participantChoice= aChoice;
	}
	public ASessionParticipantDescription () {
	}

	
	
	@Override
	public Serializable getApplicationDefinedDescription() {
		return participantDescription;
	}
	@Override
	public void setApplicationDefinedDescription(Serializable aParticipantDescription) {		
		participantDescription = aParticipantDescription;
	}
	@Override
	public ParticipantChoice getParticipantChoice() {
		return participantChoice;
	}

	public void setParticipantChoice(ParticipantChoice participantChoice) {
		this.participantChoice = participantChoice;
	}
	
	@Override
	public Serializable getParticipantDescription() {
		return participantDescription;
	}
	@Override
	public void setParticipantDescription(Serializable participantDescription) {
		this.participantDescription = participantDescription;
	}
	public void initSerializedObject() {
		
	}

}
