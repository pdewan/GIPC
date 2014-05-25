package port.sessionserver.relay;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.sessionserver.SessionServerFactory;

public interface RelayerAndSessionServerFactory extends SessionServerFactory  {
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			DuplexRPCClientInputPort aSessionServerPort);


}
