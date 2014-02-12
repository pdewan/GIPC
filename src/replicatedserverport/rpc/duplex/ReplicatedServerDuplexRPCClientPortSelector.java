package replicatedserverport.rpc.duplex;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class ReplicatedServerDuplexRPCClientPortSelector {
	static ReplicatedServerDuplexRPCClientPortFactory factory = 
		new AReplicatedServerDuplexRPCClientPortFactory();
	public static void setReplicatedServerDuplexRPCClientPortFactory (ReplicatedServerDuplexRPCClientPortFactory aFactory) {
		factory  = aFactory;
	}	
	public static DuplexRPCClientInputPort createDuplexRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createDuplexRPCPort(aServerList, anId, aName, aChoice);
	}
}
