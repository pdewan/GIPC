package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCServerInputPort;

public class ALatecomerRelayerObjectFactory implements LatecomerRelayerObjectFactory {

	@Override
	public LatecomerRelayer createRelayer(GroupRPCServerInputPort aGroupPCServerInputPort,
			LocalLatecomerSessionsServer aSessionsServer) {
		return new ALatecomerRelayer(aGroupPCServerInputPort, aSessionsServer);
	}

}
