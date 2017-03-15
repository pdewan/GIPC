package multiserverport.datacomm.duplex.buffer;

import inputport.ConnectionType;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;

import java.nio.ByteBuffer;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import util.trace.Tracer;
import variableserverport.SimplexVariableServerClientPort;
import variableserverport.datacomm.duplex.buffer.AnAbstractDuplexDirectBufferVariableServerPort;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;

// subclassed by static session but not session port
public  class ABufferDuplexMultiServerClientPort extends AnAbstractDuplexDirectBufferVariableServerPort 
//	implements DuplexReplicatedServerClientPort<ByteBuffer> {
	implements DuplexMultiServerClientPort<ByteBuffer> {

//	String physicalRemoteEndPoint;

	SessionParticipantDescription[] otherServers;
//	BufferDuplexMultiServerConnectionsManager multiServerConnectionManager;
	
//	String logicalRemoteEndPoint;
	
	
	public ABufferDuplexMultiServerClientPort (
			SessionParticipantDescription[] anOtherServers, String aLogicalServerName, String aName, ParticipantChoice aJoinChoice) {
		super (aName, aJoinChoice);
		logicalRemoteEndPoint = aLogicalServerName;
		otherServers = anOtherServers;
		Tracer.info(this, "Asking connection manager to init other P2P servers:" + otherServers);
		((BufferDuplexMultiServerConnectionsManager) connectionsManager).initPeers(otherServers);
	}

	@Override
	protected SimplexBufferVariableServerConnectionsManager createFP2PBufferConnectionsManager(SimplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice ) {
		return new ABufferDuplexMultiServerConnectionsManager(this, participantChoice);
//		return multiServerConnectionManager;
//		multiServerConnectionManager = new ABufferDuplexMultiServerConnectionsManager(this);
//		return multiServerConnectionManager;
		
	}
	
	@Override
	public void connect() {
		Tracer.info(this, "Asking connection manager to join other P2P servers:" + otherServers);
		((BufferDuplexMultiServerConnectionsManager) connectionsManager).connect(otherServers);

	}
	boolean connectionNotified;
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
//		if (connectionNotified) return;
//		connectRegistrarAndNotifier.notifyConnect(getLogicalRemoteEndPoint());
		if (remoteEnd != null) {
			Tracer.info (this, "Notifying about individual connect to:" + remoteEnd);
//			connectRegistrarAndNotifier.notifyConnect(remoteEnd, ConnectionType.TO_PHYSICAL_SERVER);
			connectRegistrarAndNotifier.notifyConnect(remoteEnd, aConnectionType);

			connectionNotified = true; // this var is not used

		} else { // this branch will not be taken, not cleaning right now
			Tracer.info (this, "Notifying about agregate connect to:" + getLogicalRemoteEndPoint());
			connectRegistrarAndNotifier.notifyConnect(getLogicalRemoteEndPoint(), ConnectionType.TO_LOGICAL_SERVER);
			
		}

	}
	
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		if (remoteEnd != null)  {
			Tracer.info (this, "Notifying about individual connect failure to " + remoteEnd);
			connectRegistrarAndNotifier.notifyConnectFailure(remoteEnd, message, null);
		} else {
			Tracer.info (this, "Notifying about aggregate connect failure to " + getLogicalRemoteEndPoint());

			connectRegistrarAndNotifier.notifyConnectFailure(getLogicalRemoteEndPoint(), message, null);
		}

//		connectRegistrarAndNotifier.notifyConnectFailure(remoteEnd, message);
	}

	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
   }
	public String getPhysicalRemoteEndPoint() {
		return physicalRemoteEndPoint;
   }
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		physicalRemoteEndPoint = newVal;
		
	}

	@Override
	public boolean isConnected(String remoteName) {
		
		return connectionNotified;
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;

		
	}

	
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}

	@Override
	public ReceiveReturnMessage<ByteBuffer> receive() {
		return receive(getSender());
	}

	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}


}
