package replicatedserverport.rpc.group.flexibleresponse.flexible.example;

import inputport.InputPort;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.SessionChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.ADuplexMultiToReplicatedPortLauncherSupport;
import replicatedserverport.datacomm.duplex.earliest.AnEarliestAcceptingReplicatedPortLauncherSupport;
import replicatedserverport.rpc.duplex.earliestresponse.AnEarliestAcceptingReplicatedDuplexRPCLauncherSupport;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseReplicatedPortLauncherSupport;
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;
import sessionport.datacomm.group.object.flexible.AFlexibleSessionPortClientLauncher;

public class AFlexibleResponseReplicatedSessionPortLauncher 
			extends AFlexibleSessionPortClientLauncher{
//	final static int SESSION_SERVER_PORT = 9090;
//	final static String SESSION_SERVER_NAME = "Session Server";
	public static final ReplicationChoice REPLICATION_CHOICE =  ReplicationChoice.EARLIEST_ACCEPTING;
	public final static String SESSION_NAME = "Test Session";
	protected ReplicationChoice replicationChoice;
	
    

	public static final SessionParticipantDescription[] SERVERS_DESCRIPTION = {
		Server1Launcher.SERVER_1_DESCRIPTION,
		Server2Launcher.SERVER_2_DESCRIPTION,
		Server3Launcher.SERVER_3_DESCRIPTION};
	protected static SessionParticipantDescription[] serversDescription;
	
	

//	String id, name;
//	boolean addConnectListener, addReplyingReceiveListener, greetOnReadingInput;
	public AFlexibleResponseReplicatedSessionPortLauncher(String aSessionServerHost, 
			String aServerId, String aServerName, String aMyId, String aMyName, 
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoCausal,
			ReplicationChoice aReplicationChoice,
ParticipantChoice aJoinChoice, //			ParticipantChoice aChoice,
			SessionParticipantDescription[] aServersDescription) {
	
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoCausal, aJoinChoice);
		replicationChoice = aReplicationChoice;
		serversDescription = aServersDescription;
	}

	
	protected  InputPort getPort() {
		// the port is non rpc but the support is rpc, maybe that is why it is not working
		// not really as the session port is data from client point of view but inside it used a client rpc port
		return ReplicatedServerSessionPortSelector.
				createObjectGroupSessionPort(serversDescription, myId, myName, 
						AnEarliestReponseReplicatedSessionServerLauncher.SESSION_SERVER_NAME, SESSION_NAME, participantChoice);
		
	}

	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		switch (replicationChoice) {
		case ALL_ACCEPTING: 
			return new ADuplexMultiToReplicatedPortLauncherSupport();
		case EARLIEST_ACCEPTING: // this is a data group port, hence the problem I think
//			return new AnEarliestAcceptingReplicatedPortLauncherSupport();
			// changing the support to set call trappers
			return new AnEarliestAcceptingReplicatedDuplexRPCLauncherSupport();

		
		case SINGLE_RESPONSE:
			return getSingleResponseReplicatedPortLauncherSupport();
//			return new  ASingleResponseReplicatedPortLauncherSupport();
		}
		
		return null;
		
	}
	// this is an rpc support in rpc duplex package
	protected PortLauncherSupport getSingleResponseReplicatedPortLauncherSupport() {
		return new ASingleResponseReplicatedPortLauncherSupport();
	}

}
