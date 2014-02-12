package port.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

public interface RelayerPortFactory {
//	public  GroupRPCServerInputPort createRelayerPort(
//			String aRelayerServerId, String aRelayerServerName, 
//			String sessionsServerHost, String sessionsServerId, String sessionsServerName, String aSessionName);
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName);

}
