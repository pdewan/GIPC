package port.sessionserver;

import inputport.rpc.duplex.DuplexRPCServerInputPort;

public interface SessionServerFactory {
	public DuplexRPCServerInputPort createSessionServerPort(String aServerId,
			String aServerName);
	public DuplexRPCServerInputPort createSessionServerPort (String aServerId, String aServerName, String aLogicalServerName);

}
