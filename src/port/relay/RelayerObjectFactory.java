package port.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

public interface RelayerObjectFactory {
	Relayer createRelayer(GroupRPCServerInputPort aGroupCServerInputPort);
}
