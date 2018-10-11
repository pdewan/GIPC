package sessionport.datacomm.duplex.buffer.fullp2p;

import java.net.InetAddress;
import java.nio.ByteBuffer;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import variableserverport.SimplexVariableServerClientPort;
import variableserverport.datacomm.duplex.buffer.AnAbstractDuplexDirectBufferVariableServerPort;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;


public class ABufferDuplexSessionPortFullP2P  
	extends AnAbstractDuplexDirectBufferVariableServerPort 
	implements 	DuplexSessionPort<ByteBuffer>, Runnable, ConnectionListener {
	
//	String sessionServerHost; 
//	String sessionServerId;
	String sessionName;
	DuplexRPCClientInputPort sessionServerClientPort;
//	String sessionsServerName;
	String serverId;
	SessionBasedFP2PBufferConnectionsManager bufferDuplexSessionConnectionManager;
//	ParticipantChoice joinChoice;
	
	
	public ABufferDuplexSessionPortFullP2P (DuplexRPCClientInputPort aSessionServerClientPort, String aSessionName, String anId, String aName, ParticipantChoice aJoinChoice) {
		super(aName, aJoinChoice);
		serverId = anId;
		sessionServerClientPort = aSessionServerClientPort;
		sessionServerClientPort.addConnectionListener(this);
//		joinChoice = aJoinChoice;
		if (participantChoice != ParticipantChoice.CLIENT_ONLY ) {
		try {
			ServerPortDescription aServerPortDescription =  new AServerPortDescription(InetAddress.getLocalHost().getHostName(), anId, aName);
			bufferDuplexSessionConnectionManager.createServerInputPort(aServerPortDescription);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		sessionName = aSessionName;		
	}
	@Override
	protected SimplexBufferVariableServerConnectionsManager createFP2PBufferConnectionsManager(
								SimplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice) {
		try {
			bufferDuplexSessionConnectionManager =  new ASessionBasedFP2PBufferConnectionsManager(this, participantChoice);
			return bufferDuplexSessionConnectionManager;
//		return  new ASessionBasedFP2PBufferConnectionsManager(this, aServerPortDescription);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}
	boolean connected;
	@Override
	public void connect() {
		sessionServerClientPort.connect();
//		((SessionBasedFP2PBufferConnectionsManager) connectionsManager).joinSessionsServer (sessionServerClientPort, sessionName);
//		connected = true;
	}
	// only if we really want to join
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {	
		notifyConnect(aRemoteEndName, aConnectionType);
		if (sessionServerClientPort.getLogicalRemoteEndPoint().equals(aRemoteEndName)) {

		connected = true;
		Thread thread = new Thread(this);
		thread.setName("Session Server Invoker");
		thread.start();
		}
		
	}
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void run() {
		((SessionBasedFP2PBufferConnectionsManager) connectionsManager).joinSessionsServer (sessionServerClientPort, sessionName);

		
	}
	
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}
	@Override
	public String getServerId() {
		return serverId;
	}
	@Override
	public boolean isConnected(String remoteName) {
		return connected;
	}
//	@Override
//	public String getLogicalRemoteEndPoint() {
//		return sessionName;
//	}
//	@Override
//	public String getPhysicalRemoteEndPoint() {
//		return null;
//	}
//	@Override
//	public void setPhysicalRemoteEndPoint(String newVal) {
//		
//	}
//	@Override
//	public void setLogicalRemoteEndPoint(String newVal) {
//		sessionName = newVal;
//	}
	@Override
	public ReceiveReturnMessage<ByteBuffer> receive() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String aSource) {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
