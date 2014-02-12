package port.sessionserver.relay;

import port.sessionserver.SessionServerFactory;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;

public interface RelayerAndSessionServerFactory extends SessionServerFactory  {
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			DuplexRPCClientInputPort aSessionServerPort);


}
