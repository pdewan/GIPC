package port.sessionserver.relay.late.mvc.dynamic.example;


import inputport.ConnectionType;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.late.LatecomerJoinInfo;
import port.sessionserver.relay.late.LatecomerSessionServer;
import port.sessionserver.relay.late.mvc.example.ATwoDepPortLatecomerClientLauncher;
import util.trace.Tracer;

public class ADynamicServerLatecomerClientLauncher extends ATwoDepPortLatecomerClientLauncher  {
	DynamicServerSessionObserver dynamicServerSessionObserver;
	public ADynamicServerLatecomerClientLauncher(String aClientName,
			String aSessionServerHost, String aSessionServerId,
			String aSessionServerName, String aSessionName) {
		super(aClientName, aSessionServerHost, aSessionServerId, aSessionServerName,
				aSessionName);		
	}
	protected void mvcServerHasNotJoined() {
		Tracer.info(this, "MVC Server not yet registered. Will wait for join notification");		
	}
	@Override
	protected JoinInfo joinSessionServer() {
		// created before session join so  MVC server description and message list is null
		dynamicServerSessionObserver = new ADynamicServerSessionObserver(serverName, null, clientName, null,
				( GenericRelayingCollaborativeFrostyModel) getFrostyModel(), null, this);
		dynamicServerSessionObserver.initInputPort(mainPort);
		return ((LatecomerSessionServer) sessionServerProxy).
				lateJoin(sessionName, new AServerPortDescription(null, null, clientName), 
						dynamicServerSessionObserver);
	}	
	protected void processMessages (LatecomerJoinInfo  latecomerJoinInfo) {
		super.processMessages(latecomerJoinInfo);
		dynamicServerSessionObserver.initMessageList(messages); // now we have the message list
	}
	@Override
	protected void chainConnectListeners(ServerPortDescription relayerPortDescription, ServerPortDescription mvcServerPortDescription) {	
		if (mvcServerPortDescription != null) { // server already registered	
			// now we have the mvc server description
			dynamicServerSessionObserver.initNextServerPortDescription(mvcServerPortDescription);
			dynamicServerSessionObserver.connected(serverName, ConnectionType.CLIENT_TO_SESSION);
		}
	}

	
}
