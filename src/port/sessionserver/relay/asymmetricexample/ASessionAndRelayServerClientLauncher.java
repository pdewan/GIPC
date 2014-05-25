package port.sessionserver.relay.asymmetricexample;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import port.ParticipantChoice;
import port.PortLauncherSupport;
import port.relay.Relayer;
import port.sessionserver.SessionObserver;
import port.sessionserver.asymmetricexample.ASessionServerClientLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ASessionAndRelayServerClientLauncher extends ASessionServerClientLauncher{
	public static String RELAYER_HOST = "localhost";

	String relayerServerId;
	String relayerServerName; 
	String sessionServerHost;
	String relayerServerHost;
//	DuplexRPCClientInputPort relayerClientPort;
	Relayer relayerProxy;
//	RelayerConnectingConnectListener connectListener;


	public ASessionAndRelayServerClientLauncher( 
			String aSessionServerHost, String aSessionServerId, String aSessionServerName, 
//			String aRelayerServerHost, 
//			String aRelayerServerId, String aRelayerServerName, 
			String aMyId, String aMyName, ParticipantChoice aChoice) {
		super(null, aSessionServerId, aSessionServerName, aMyId, aMyName, aChoice);
//		super(aServerName);
		sessionServerHost = aSessionServerHost;	
//
//		relayerServerId = aRelayerServerId;
//		relayerServerName = aRelayerServerName;
//		relayerServerHost = aRelayerServerHost;
	}
	// this makes a difference in latecomer rather than relayer port as otherwise
	// a message with source is intercpeted by the session server listener
	protected  ReceiveListener getReceiveListener (InputPort anInputPort) {
		return null;
		
	}

//	@Override
//	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
//		return new ATracingConnectionListener(anInputPort);
//		
//		
//	}
	
	@Override
	protected  ConnectionListener getConnectionListener (InputPort anInputPort) {
		return new ARelayerConnectingConnectListener(anInputPort, SESSION_NAME, 
				ARelayerSupportingSessionServer.class,
				myPortDescription,
				myName,
				participantChoice, (JoinerTrackingSessionObserver) observer, this);
		
		
	}

//	protected  InputPort getAuxilliaryPort() {
//		return createRelayerClientPort();
//	}
	protected  InputPort getAuxilliaryPort() {
		return null;
	}
//	protected  InputPort createRelayerClientPort() {
//		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(relayerServerHost, 
//				relayerServerId, relayerServerName, myName);
//
//	}
//	
//	@Override
//	public  ConnectionListener getAuxilliaryConnectionListener (InputPort anInputPort) {
//		return  new ATracingConnectionListener(auxilliaryPort);
//		
//	}
	

//	@Override
//	public  ReceiveListener getAuxilliaryReceiveListener (InputPort anInputPort) {
//		return new AnEchoingMessageWithSourceReceiveListener();
//	}
	

	@Override
	protected SessionObserver createSessionObserver() {
//		return new AFrostyPeerSendingSessionObserver(myName, SESSION_NAME, relayerProxy);
		return new AJoinerTrackingSessionObserver(myName, SESSION_NAME);
	}
	
	protected PortLauncherSupport getPortLauncherSupport() {
//		return new ALatecomerRelayerAndSessionServerLauncherSupport();
		return new ADuplexRPCInputPortLauncherSupport();

	}
	protected void setStateBeforeAddingListeners() {
		super.setStateBeforeAddingListeners();
	}
	
//
//	@Override
//	public void createProxies() {
//		try {
//			 relayerProxy = (Relayer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) auxilliaryPort, null,
//					Relayer.class, null);
//		
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//	}

	@Override
	public void createUI(InputPort anInputPort) {
		relayerProxy = ((RelayerConnectingConnectListener) connectionListener).getRelayerProxy();
		super.createUI(anInputPort);
	}

	@Override
	protected void sendMessage(Object aMessage) {
		MessageWithSource messageWithSource = new AMessageWithSource(myName, aMessage);
		if (participantChoice == ParticipantChoice.SERVER_ONLY) {
			return;
		}
		if (participantChoice == ParticipantChoice.SYMMETRIC_JOIN)
//			relayerProxy.relayOthers(SESSION_NAME, messageWithSource);
			relayerProxy.relayOthers(messageWithSource);

		else
//			relayerProxy.relay(SESSION_NAME, ((JoinerTrackingSessionObserver) observer).getJoiners(), messageWithSource) ;
		   relayerProxy.relay(((JoinerTrackingSessionObserver) observer).getJoiners(), messageWithSource) ;


	}

}
