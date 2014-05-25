package replicatedserverport.rpc.group;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.rpc.duplex.ReplicatedServerDuplexRPCClientPortSelector;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;

public class AReplicatedServerSessionPortFactory  implements ReplicatedServerSessionPortFactory{	
	
	public AReplicatedServerSessionPortFactory() {
		super();
	}
	
	
	public GroupSessionPort createObjectGroupSessionPort(
			SessionParticipantDescription[] aServerDescriptionList, String anId, String aName, 
			String aSessionServerName, String aSessionName, ParticipantChoice aChoice) {
		// treating a bunch of server ports as a single duplex port
		DuplexRPCClientInputPort sessionManagerClientPort = 
			ReplicatedServerDuplexRPCClientPortSelector.createDuplexRPCPort(aServerDescriptionList, 
					aSessionServerName, 
					aName,
					ParticipantChoice.MEMBER); // this fails if it is SERVER_ONLY because the idea is to
											 // create a client port
		// creating a session port out of this
		// this is geared towards the session mananger only.
		// all it does is allow replication of the session manager
		GroupSessionPort<Object> sessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(sessionManagerClientPort, aSessionName, anId, aName, aChoice);
		return sessionPort;
	}
}
