package replicatedserverport.rpc.simplex;

import inputport.rpc.simplex.SimplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class ReplicatedServerRPCClientPortSelector {
	static ReplicatedServerRPCClientPortFactory factory = new AReplicatedServerRPCClientPortFactory();
	public static void setReplicatedServerDuplexRPCClientPortFactory (ReplicatedServerRPCClientPortFactory aFactory) {
		factory  = aFactory;
	}	
	public SimplexRPCClientInputPort createRPCPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice){
		return factory.createRPCPort(aServerList, anId, aName, aChoice);
	}
}
