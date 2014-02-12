package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCServerInputPort;

public class LatecomerRelayerAndSessionServerSelector {
	static LatecomerRelayerAndSessionServerFactory factory;
	public static void setLatecomerRelayerAndSessionServerFactory(LatecomerRelayerAndSessionServerFactory aFactory) {
		factory = aFactory;
	}
	public static LatecomerRelayerAndSessionServerFactory getLatecomerRelayerAndSessionServerFactory() {
		return factory;
	}
	public  static GroupRPCServerInputPort 
	createLatecomerSessionsServerAndRelayer(String aSessionServerId, 
			String aSessionServerName, 
			String aLogicalServerName) {
		return factory.createLatecomerSessionsServerAndRelayer(aSessionServerId, aSessionServerName, aLogicalServerName);
	}
	public  static GroupRPCServerInputPort 
	createLatecomerSessionsServerAndRelayer(String aSessionServerId, 
			String aSessionServerName) {
		return createLatecomerSessionsServerAndRelayer(aSessionServerId, aSessionServerName, aSessionServerName);
	}

}
