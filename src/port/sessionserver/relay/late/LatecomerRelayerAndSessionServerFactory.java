package port.sessionserver.relay.late;

import inputport.rpc.group.GroupRPCServerInputPort;

public interface LatecomerRelayerAndSessionServerFactory {
	public  GroupRPCServerInputPort 
	createLatecomerSessionsServerAndRelayer(String aSessionServerId, 
			String aSessionsServerName, 
			String aLogicalServerName);	

}
