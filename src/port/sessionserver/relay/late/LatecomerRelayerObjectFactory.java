package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCServerInputPort;

public interface LatecomerRelayerObjectFactory {
	LatecomerRelayer createRelayer(GroupRPCServerInputPort aGroupCServerInputPort, 
			LocalLatecomerSessionsServer aSessionsServer);
}
