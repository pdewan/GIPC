package port.sessionserver.relay.asymmetricexample;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.example.UICreator;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ParticipantChoice;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.asymmetricexample.ASessionParticipatingConnectListener;
import port.sessionserver.asymmetricexample.ASessionServerClientLauncher;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;

public class ARelayerConnectingConnectListener extends ASessionParticipatingConnectListener implements RelayerConnectingConnectListener {
	DuplexRPCClientInputPort relayerPort;
	String myName;
	Relayer relayerProxy;
	ParticipantChoice participantChoice;
	JoinerTrackingSessionObserver sessionObserver;
	UICreator uiCreator;
	ServerPortDescription serverPortDescription;
	ReceiveListener receiveListener;
	public ARelayerConnectingConnectListener(InputPort anInputPort,
			String aSessionName, 
			Class aSessionServerClass,
			ServerPortDescription aServerPortDescription,
			String aName, ParticipantChoice aChoice,
			JoinerTrackingSessionObserver anObserver,
			UICreator aUICreator) {
		super(anInputPort, aSessionName, aSessionServerClass);
		myName = aName;
		serverPortDescription = aServerPortDescription;
		participantChoice = aChoice; 
		sessionObserver = anObserver;
		uiCreator = aUICreator;
		receiveListener = new  AnEchoingMessageWithSourceReceiveListener();
		
	}
	@Override
	protected void participateInSession() {
		ConnectionListener connectionListener = ASessionServerClientLauncher.createParticipatingConnectionListener(
				inputPort, participantChoice, ARelayerSupportingSessionServer.class,  serverPortDescription, sessionObserver, uiCreator);
		ServerPortDescription relayerDesccription = ((RelayerSupportingSessionServer) sessionServerProxy).getRelayerDescripton(sessionName);

		if (relayerDesccription == null) {
			relayerPort = (DuplexRPCClientInputPort) inputPort;
//			relayerPort.addReceiveListener(receiveListener);
//			connectionListener.connected(relayerPort.getLogicalRemoteEndPoint()); // fake a connection
//			return;
			
		}
		else {			
		relayerPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(relayerDesccription.getHost(), 
				relayerDesccription.getID(), relayerDesccription.getName(), myName);
		}
		
		try {
			 relayerProxy = (Relayer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) relayerPort, null,
					ARelayer.class, null);
		

		} catch (Exception e) {
			e.printStackTrace();
		}	
		if (sessionObserver != null) // JOIN_ONLY
			sessionObserver.setRelayerProxy(relayerProxy);
//		relayerPort.addConnectionListener(
//				ASessionServerClientLauncher.createParticipatingConnectionListener(
//						inputPort, participantChoice, RelayerSupportingSessionServer.class,  serverPortDescription, sessionObserver, uiCreator));
		relayerPort.addReceiveListener(receiveListener);
		if (relayerPort != inputPort) {
		relayerPort.addConnectionListener(connectionListener);
//		relayerPort.addReceiveListener(receiveListener);
		relayerPort.connect();
		} else {
			connectionListener.connected(relayerPort.getLogicalRemoteEndPoint(), null); // fake a connection

		}
		
	}
	@Override
	public Relayer getRelayerProxy() {
		return relayerProxy;
	}	
	
}
