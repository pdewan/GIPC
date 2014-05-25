package port.sessionserver.relay;

import inputport.rpc.group.GroupRPCServerInputPort;
import port.relay.RelayerPortFactory;

public interface SessionServerRelayerPortFactory extends RelayerPortFactory{
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			String sessionsServerHost, String sessionsServerId, String sessionsServerName, String aSessionName);
//	public  GroupRPCServerInputPort createRelayerPort(
//			String aRelayerServerId, String aRelayerServerName);

}
