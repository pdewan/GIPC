package port.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

public class ARelayerObjectFactory implements RelayerObjectFactory {

	@Override
	public Relayer createRelayer(GroupRPCServerInputPort aGroupPCServerInputPort) {
		return new ARelayer(aGroupPCServerInputPort);
	}

}
