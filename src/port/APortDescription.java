package port;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;

public class APortDescription implements PortDescription {
	
	PortKind portKind;
	PortAccessKind portAccessKind;
	PortMessageKind portMessageKind;
	SessionChoice sessionChoice;
	ReplicationChoice replicationChoice;
	ParticipantBindTime participantBindTime = ParticipantBindTime.DYNAMIC;
	public APortDescription (PortKind aPortKind, 
			PortAccessKind aPortAccessKind, 
			PortMessageKind aPortMessageKind) {
		portKind = aPortKind;
		portAccessKind = aPortAccessKind;
		portMessageKind = aPortMessageKind;
	}
	public APortDescription (PortKind aPortKind, 
			PortAccessKind aPortAccessKind, 
			PortMessageKind aPortMessageKind,
			SessionChoice aSessionChoice) {
		portKind = aPortKind;
		portAccessKind = aPortAccessKind;
		portMessageKind = aPortMessageKind;
		sessionChoice = aSessionChoice;
	}
	public APortDescription (PortKind aPortKind, 
			PortAccessKind aPortAccessKind, 
			PortMessageKind aPortMessageKind,
			SessionChoice aSessionChoice,
			ParticipantBindTime aParticipantBindTime) {
		portKind = aPortKind;
		portAccessKind = aPortAccessKind;
		portMessageKind = aPortMessageKind;
		sessionChoice = aSessionChoice;
		participantBindTime = aParticipantBindTime;
	}
	public APortDescription (PortKind aPortKind, 
			PortAccessKind aPortAccessKind, 
			PortMessageKind aPortMessageKind,
			SessionChoice aSessionChoice,
			ReplicationChoice aReplicationChoice) {
		portKind = aPortKind;
		portAccessKind = aPortAccessKind;
		portMessageKind = aPortMessageKind;
		sessionChoice = aSessionChoice;
		replicationChoice = aReplicationChoice;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#getPortKind()
	 */
	@Override
	public PortKind getPortKind() {
		return portKind;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#setPortKind(inputport.PortKind)
	 */
	@Override
	public void setPortKind(PortKind aPortKind) {
		this.portKind = aPortKind;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#getPortAccessKind()
	 */
	@Override
	public PortAccessKind getPortAccessKind() {
		return portAccessKind;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#setPortAccessKind(inputport.PortAccessKind)
	 */
	@Override
	public void setPortAccessKind(PortAccessKind aPortAccessKind) {
		this.portAccessKind = aPortAccessKind;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#getPortMessageKind()
	 */
	@Override
	public PortMessageKind getPortMessageKind() {
		return portMessageKind;
	}
	/* (non-Javadoc)
	 * @see inputport.PortDescription#setPortMessageKind(inputport.PortMessageKind)
	 */
	@Override
	public void setPortMessageKind(PortMessageKind aPortMessageKind) {
		this.portMessageKind = aPortMessageKind;
	}
	public String toString() {
		return portKind + ":" + portMessageKind + ":" + portAccessKind + ":" + sessionChoice + ":" + participantBindTime;
	}
	@Override
	public SessionChoice getSessionChoice() {
		return sessionChoice;
	}
	@Override
	public void setSessionChoice(SessionChoice aSessionChoice) {
		sessionChoice = aSessionChoice;
	}
	@Override
	public ReplicationChoice getReplicationChoice() {
		return replicationChoice;
	}
	@Override
	public void setReplicationChoice(ReplicationChoice aReplicationChoice) {
		replicationChoice = aReplicationChoice;
		
	}
	@Override
	public ParticipantBindTime getParticipantBindTime() {
		return participantBindTime;
	}
	@Override
	public void setParticipantBindTime(ParticipantBindTime newVal) {
		participantBindTime = newVal;
	}

}
