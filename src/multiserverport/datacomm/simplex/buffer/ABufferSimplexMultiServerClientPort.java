package multiserverport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;
import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import variableserverport.SimplexVariableServerClientPort;
import variableserverport.datacomm.simplex.buffer.AnAbstractSimplexBufferMultiServerPort;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;


public  class ABufferSimplexMultiServerClientPort extends AnAbstractSimplexBufferMultiServerPort 
//	implements DuplexReplicatedServerClientPort<ByteBuffer> {
	implements SimplexMultiServerClientPort<ByteBuffer> {


	SessionParticipantDescription[] otherServers;
	SimplexBufferVariableServerConnectionsManager directBufferBroadcastConnectionsManager;
	
	String logicalRemoteEndPoint;
	
	String phsyicalRemoteEndPoint;
	
	
	public ABufferSimplexMultiServerClientPort (
			SessionParticipantDescription[] anOtherServers, String aLogicalServerName, String aName) {
		super (aName, null);
		logicalRemoteEndPoint = aLogicalServerName;
		otherServers = anOtherServers;
	}

	@Override
	protected SimplexBufferVariableServerConnectionsManager createFP2PBufferConnectionsManager(SimplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice ) {
		directBufferBroadcastConnectionsManager = new ABufferMultiServerConnectionsManager(this, ParticipantChoice.MEMBER);
		return directBufferBroadcastConnectionsManager;
		
	}
	// this really again implies that static session port should not be subclass of multiserver port
	@Override
	public void send(ByteBuffer message) {
		send(getLogicalRemoteEndPoint(), message);
	}
	
	@Override
	public void connect() {
		for (int i = 0; i < otherServers.length; i++) {
			connectionsManager.joined(otherServers[i]);

		}
	}

	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
}
	public String getPhysicalRemoteEndPoint() {
		return phsyicalRemoteEndPoint;
}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		phsyicalRemoteEndPoint = newVal;;
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;

		
	}
	
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return logicalRemoteEndPoint != null;
	}

	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}

}
