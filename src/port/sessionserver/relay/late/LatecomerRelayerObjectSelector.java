package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCServerInputPort;

public class LatecomerRelayerObjectSelector {
	static LatecomerRelayerObjectFactory relayerObjectFactory;
	public static LatecomerRelayerObjectFactory getRelayerObjectFactory() {
		return relayerObjectFactory;
	}
	public static void setRelayerObjectFactory(
			LatecomerRelayerObjectFactory relayerObjectFactory) {
		LatecomerRelayerObjectSelector.relayerObjectFactory = relayerObjectFactory;
	}
	public static LatecomerRelayer createRelayer(GroupRPCServerInputPort aDuplexPCServerInputPort, LocalLatecomerSessionsServer aSessionsServer) {
		return relayerObjectFactory.createRelayer(aDuplexPCServerInputPort,  aSessionsServer);
	}

}
