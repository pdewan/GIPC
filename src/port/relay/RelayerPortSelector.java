package port.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

public class RelayerPortSelector {
	static RelayerPortFactory factory;
	public static void setRelayerFactory(RelayerPortFactory aFactory) {
		factory = aFactory;
	}
	public static RelayerPortFactory getRelayerFactory() {
		return factory;
	}
	public static  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName) {
		return factory.createRelayerPort(aRelayerServerId, aRelayerServerName);
	}

}
