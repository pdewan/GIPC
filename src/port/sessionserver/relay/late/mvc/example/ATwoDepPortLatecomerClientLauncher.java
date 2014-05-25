package port.sessionserver.relay.late.mvc.example;


import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;

import java.util.List;

import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.asymmetricexample.APrintingSessionObserver;
import port.sessionserver.relay.late.ALatecomerSessionServer;
import port.sessionserver.relay.late.ASynchronizedLatecomerMessageList;
import port.sessionserver.relay.late.LatecomerJoinInfo;
import port.sessionserver.relay.late.LatecomerSessionServer;
import port.sessionserver.relay.mvc.example.AThreeDepPortClientLauncher;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ATwoDepPortLatecomerClientLauncher extends AThreeDepPortClientLauncher implements ReceiveListener   {
	protected List<MessageWithSource> messages = new ASynchronizedLatecomerMessageList();
	public ATwoDepPortLatecomerClientLauncher(String aClientName,
			String aSessionServerHost, String aSessionServerId,
			String aSessionServerName, String aSessionName) {
		super(aClientName, aSessionServerHost, aSessionServerId, aSessionServerName,
				aSessionName);
	}
//	protected void createUI(InputPort anInputPort) {
//		communicateWithSessionServer();
//		// this is the infinite loop
//		super.createUI(anInputPort);
//	}
	// called after join
	@Override
	protected ReceiveListener getReceiveListener(InputPort anInputPort) {
		return this;
	}
	@Override
	protected void chainConnectListeners(ServerPortDescription relayerPortDescription, ServerPortDescription mvcServerPortDescription) {
//		((DuplexRPCClientInputPort)  mainPort).addReceiveListener(this);
		ConnectionListenerWithPort relayerConnectionListener = new ALatecomerRelayerConnectionListener(mvcServerPortDescription, clientName, null, 
				( GenericRelayingCollaborativeFrostyModel) getFrostyModel(), messages, this);
		relayerConnectionListener.initInputPort(mainPort);
		relayerConnectionListener.connected(serverName, ConnectionType.CLIENT_TO_SESSION);	
	}
	@Override
	protected void processMemberJoinInfo (JoinInfo joinInfo) {
		super.processMemberJoinInfo(joinInfo);
		processMessages((LatecomerJoinInfo) joinInfo);
		
	}
	@Override
	protected Class sessionServerClass() {
		return ALatecomerSessionServer.class;
	}
	@Override
	protected JoinInfo joinSessionServer() {
		return ((LatecomerSessionServer) sessionServerProxy).lateJoin(sessionName, new AServerPortDescription(null, null, clientName), new APrintingSessionObserver());
	}
	protected void processMessages (LatecomerJoinInfo  latecomerJoinInfo) {
//		System.out.println("Adding all Messages");

		messages.addAll (0, latecomerJoinInfo.getMessages());
//		((DuplexRPCClientInputPort)  mainPort).addReceiveListener(this);
//		((DuplexRPCClientInputPort)  mainPort).addReceiveListener(( GenericRelayingCollaborativeDuplexModel) getFrostyModel());
//
//		for (MessageWithSource message:messages) {
//			Tracer.info(this, "Process message:" + message);
//			((DuplexRPCClientInputPort)  mainPort)			
//			.notifyPortReceive(((DuplexRPCClientInputPort) mainPort).getLogicalRemoteEndPoint(), message);
//		}
		
	}
	@Override
	public void messageReceived(String aSourceName, Object aMessage) {
		messages.add((MessageWithSource) aMessage);		
	}	
	
}
