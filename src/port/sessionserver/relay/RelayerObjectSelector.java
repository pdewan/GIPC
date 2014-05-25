package port.sessionserver.relay;

import inputport.rpc.group.GroupRPCServerInputPort;
import port.relay.Relayer;
import port.relay.RelayerObjectFactory;

public class RelayerObjectSelector {
	static RelayerObjectFactory relayerObjectFactory;
	public static RelayerObjectFactory getRelayerObjectFactory() {
		return relayerObjectFactory;
	}
	public static void setRelayerObjectFactory(
			RelayerObjectFactory relayerObjectFactory) {
		RelayerObjectSelector.relayerObjectFactory = relayerObjectFactory;
	}
	public static Relayer createRelayer(GroupRPCServerInputPort aDuplexPCServerInputPort) {
		return relayerObjectFactory.createRelayer(aDuplexPCServerInputPort);
	}

}
