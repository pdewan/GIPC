package port.sessionserver.relay.late.mvc.example;


import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;

import java.util.List;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserver.relay.late.ALatecomerSessionServer;
import port.sessionserver.relay.late.LatecomerJoinInfo;
import port.sessionserver.relay.late.LatecomerSessionServer;
import port.sessionserver.relay.mvc.example.AThreeDepPortClientPseudoLauncher;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ATwoDepPortLatecomerPseudoClientLauncher extends AThreeDepPortClientPseudoLauncher  {
	protected List<MessageWithSource> messages;
	public ATwoDepPortLatecomerPseudoClientLauncher(String aClientName,
			String aSessionServerHost, String aSessionServerId,
			String aSessionServerName, String aSessionName) {
		super(aClientName, aSessionServerHost, aSessionServerId, aSessionServerName,
				aSessionName);
	}
	// called after join
	@Override
	protected void chainConnectListeners(ServerPortDescription relayerPortDescription, ServerPortDescription mvcServerPortDescription) {
		ConnectionListenerWithPort relayerConnectionListener = new AnOldLatecomerRelayerConnectionListener(mvcServerPortDescription, clientName, null, messages);
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
		messages = latecomerJoinInfo.getMessages();
		
	}	
	
}
