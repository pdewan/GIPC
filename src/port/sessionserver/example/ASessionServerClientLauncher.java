package port.sessionserver.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;
import inputport.datacomm.simplex.buffer.example.UICreator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;

import java.net.InetAddress;

import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.delay.example.AnEchoingObjectReceiveListener;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ASessionServer;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;

public class ASessionServerClientLauncher extends 
	AnAbstractInteractiveSessionServerClientLauncher 	{

	protected DuplexRPCClientInputPort serverClientPort;
//	protected GroupInputPort<Object> myServerPort;
	protected ServerPortDescription myPortDescription;
	protected SessionObserver observer;
	protected String myHost;
//	ReceiveListener receiveListener;
	
	public ASessionServerClientLauncher(String aSessionServerHost, 
			String aServerId, String aServerName,
			String aMyId, String aMyName,
			ParticipantChoice aParticipantChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aParticipantChoice);
		try {
		myHost = InetAddress.getLocalHost().getCanonicalHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		myPortDescription = new AServerPortDescription(myHost, myId, myName);		
//		receiveListener = new  AnEchoingObjectReceiveListener();
	}
	
	protected SessionObserver createSessionObserver() {
		return new AJoinerConnectingSessionObserver(myName);
	}
	


	protected  InputPort getAuxilliaryPort() {
		return createP2PPort();
	}
	protected  InputPort createP2PPort() {
		if (participantChoice == ParticipantChoice.MEMBER ||
				participantChoice == ParticipantChoice.SERVER_ONLY ) {
		return GroupRPCInputPortSelector.createGroupRPCServerInputPort(myId, myName);
		} else {
			return null;
		}

	}
	protected void setStateBeforeAddingListeners() {
		if (participantChoice == ParticipantChoice.MEMBER ||
				participantChoice == ParticipantChoice.CLIENT_ONLY )
		observer = createSessionObserver();

	}
	

	@Override
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return createParticipatingConnectionListener(anInputPort, participantChoice, ASessionServer.class, myPortDescription, observer, this);
//		switch (participantChoice) {
//		case JOIN_AND_OBSERVE_ALL:
//		case JOIN_AND_OBSERVE: {
//			return new AJoiningAndObservingConnectListener(anInputPort, SESSION_NAME, myPortDescription, (JoinerProcessingSessionObserver) observer);
//		}
//		case JOIN_ONLY: {
//			return new AJoiningConnectListener(anInputPort, SESSION_NAME, myPortDescription);
//		}
//		case OBSERVE_ONLY: {
//			return new AnObservingConnectListener(anInputPort, SESSION_NAME, (JoinerProcessingSessionObserver) observer);
//		}
//		default: return null;
//		}
//		return new AJoiningAndObservingConnectListener(anInputPort, SESSION_NAME, myPortDescription, (ProcessingSessionObserver) observer);
		
		
	}
	
	public  static  ConnectionListener createParticipatingConnectionListener(
			InputPort anInputPort, 
			ParticipantChoice aParticipantChoice, 
			Class aRemoteClass,
			ServerPortDescription aPortDescription, SessionObserver anObserver, UICreator aUICreator) {
		switch (aParticipantChoice) {
		case SYMMETRIC_JOIN:
		case MEMBER: {
			return new AJoiningAndObservingConnectListener(anInputPort, SESSION_NAME, aRemoteClass, aPortDescription, (JoinerProcessingSessionObserver) anObserver, aUICreator);
		}
		case SERVER_ONLY: {
			return new AJoiningConnectListener(anInputPort, SESSION_NAME, aRemoteClass, aPortDescription);
		}
		case CLIENT_ONLY: {
			return new AnObservingConnectListener(anInputPort, SESSION_NAME, aRemoteClass, (JoinerProcessingSessionObserver) anObserver, aUICreator);
		}
		default: return null;
		}
		
	}
	// as the connect listener is creating or not creatint the ui, we do not duplicate this step here 
	protected void doPostConnectsAsyncOperations() {
//		createUI(mainPort);
	}
	@Override
	public  ConnectionListener getAuxilliaryConnectionListener (InputPort anInputPort) {
		return new AFrostyObjectConnectionListener((GroupRPCServerInputPort) auxilliaryPort);
		
	}
	
	@Override
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
//		return receiveListener;
		return null;
//		return new  AnEchoingObjectReceiveListener();
		
	}
	
	@Override
	protected  ReceiveListener getAuxilliaryReceiveListener (InputPort anInputPort) {
//		return receiveListener;
		return new  AnEchoingObjectReceiveListener();
		
	}

	
	protected  InputPort getPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(serverHost, 
					serverId, serverName, myName);	
		
	}
	
	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
		return new AGroupRPCInputPortLauncherSupport();

	}
	
//	protected void doPostConnectsAsyncOperations() {
//		createUI(mainPort);
//	}
//	


	@Override
	protected void sendMessage(Object aMessage) {
		switch (participantChoice) {
		case SERVER_ONLY: return;
		case SYMMETRIC_JOIN: 
			sendAllUsingGroupServerPort(aMessage); // more optimal
			return;
		case CLIENT_ONLY:
		case MEMBER:
			sendAllUsingClientPorts(aMessage);
			return;
		}

		
	}
	
	protected void sendAllUsingGroupServerPort(Object aMessage) {
	     ((GroupRPCServerInputPort) auxilliaryPort).sendAll(aMessage);

	}
	
	protected void sendAllUsingClientPorts(Object aMessage) {
		for (DuplexClientInputPort aPort: ((JoinerConnectingSessionObserver) observer).getClientInputPorts()) {
			aPort.send(aMessage);
		}
	}
		
//	}

}
