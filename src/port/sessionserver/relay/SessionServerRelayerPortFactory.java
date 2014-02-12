package port.sessionserver.relay;

import port.relay.RelayerPortFactory;
import inputport.rpc.group.GroupRPCServerInputPort;

public interface SessionServerRelayerPortFactory extends RelayerPortFactory{
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			String sessionsServerHost, String sessionsServerId, String sessionsServerName, String aSessionName);
//	public  GroupRPCServerInputPort createRelayerPort(
//			String aRelayerServerId, String aRelayerServerName);

}
