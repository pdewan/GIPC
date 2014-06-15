package port;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ReplicationChoice;

public interface PortDescription {
	public PortKind getPortKind();
	public void setPortKind(PortKind aPortKind);
	public PortAccessKind getPortAccessKind();
	public void setPortAccessKind(PortAccessKind aPortAccessKind);
	public PortMessageKind getPortMessageKind();
	public void setPortMessageKind(PortMessageKind aPortMessageKind);
	public SessionChoice getSessionChoice();
	public void setSessionChoice(SessionChoice aSessionChoice);
	public ReplicationChoice getReplicationChoice();
	public void setReplicationChoice(ReplicationChoice aReplicationChoice);
	public ParticipantBindTime getParticipantBindTime();
	public void setParticipantBindTime (ParticipantBindTime newVal);
}