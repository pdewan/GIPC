package port.sessionserver.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

public class SessionServerRelayerPortSelector  {
	static SessionServerRelayerPortFactory factory;
	public static void setRelayerFactory(SessionServerRelayerPortFactory aFactory) {
		factory = aFactory;
	}
	public static SessionServerRelayerPortFactory getRelayerFactory() {
		return factory;
	}
	public static  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			String aSessionsServerHost, String aSessionServerId, String aSessionServerName, String aSessionName) {
		return factory.createRelayerPort(aRelayerServerId, aRelayerServerName, aSessionsServerHost, aSessionServerId, aSessionServerName, aSessionName);
	}

}
