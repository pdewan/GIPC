package port.sessionserver.relay.late.mvc.dynamic.example;


import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.relay.late.LatecomerJoinInfo;
import port.sessionserver.relay.late.LatecomerSessionServer;
import port.sessionserver.relay.late.mvc.example.AnOldLatecomerRelayerConnectionListener;
import port.sessionserver.relay.late.mvc.example.ATwoDepPortLatecomerPseudoClientLauncher;
import util.trace.Tracer;

public class ADynamicServerLatecomerClientPseudoLauncher extends ATwoDepPortLatecomerPseudoClientLauncher  {
	DynamicServerSessionObserver dynamicServerSessionObserver;
//	List<MessageWithSource> messages;
	public ADynamicServerLatecomerClientPseudoLauncher(String aClientName,
			String aSessionServerHost, String aSessionServerId,
			String aSessionServerName, String aSessionName) {
		super(aClientName, aSessionServerHost, aSessionServerId, aSessionServerName,
				aSessionName);
		dynamicServerSessionObserver = new AnOldDynamicServerSessionObserver(serverName, null, clientName, null, null);
	}
	// called after join
	@Override
	protected void chainConnectListeners(ServerPortDescription relayerPortDescription, ServerPortDescription mvcServerPortDescription) {
		
		if (mvcServerPortDescription != null) { // server already registered
			dynamicServerSessionObserver.initNextServerPortDescription(mvcServerPortDescription);
			dynamicServerSessionObserver.connected(serverName, ConnectionType.CLIENT_TO_SESSION);
		}
		//		ConnectionListenerWithPort relayerConnectionListener = new ALatecomerRelayerConnectionListener(mvcServerPortDescription, clientName, null, messages);
		
//		relayerConnectionListener.connected(serverName, ConnectionType.CLIENT_TO_SESSION_SERVER);	
	}
//	@Override
//	protected void processJoinInfo (JoinInfo joinInfo) {
//		super.processJoinInfo(joinInfo);
//		processMessages((LatecomerJoinInfo) joinInfo);
//		
//	}
//	@Override
//	protected Class sessionServerClass() {
//		return ALatecomerSessionServer.class;
//	}
	protected void mvcServerHasNotJoined() {
		Tracer.info(this, "MVC Server not yet registered. Will wait for notification");		
	}
	@Override
	protected JoinInfo joinSessionServer() {
		dynamicServerSessionObserver.initInputPort(mainPort);
		return ((LatecomerSessionServer) sessionServerProxy).
				lateJoin(sessionName, new AServerPortDescription(null, null, clientName), 
						dynamicServerSessionObserver);
	}
	protected void processMessages (LatecomerJoinInfo  latecomerJoinInfo) {
		super.processMessages(latecomerJoinInfo);
		dynamicServerSessionObserver.initMessageList(messages);
	}
//	protected void processMessages (LatecomerJoinInfo  latecomerJoinInfo) {
//		messages = latecomerJoinInfo.getMessages();
//		
//	}	
	
}
