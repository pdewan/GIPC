package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;

public interface DuplexRPCInputPortFactory {
	public DuplexRPCServerInputPort createDuplexRPCServerInputPort(String theServerId, String theServerName);
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(String theServerHost, String theServerId, String theServerName, String theClientName);
	DuplexRPCClientInputPort createDuplexRPCClientInputPort(
			DuplexClientInputPort<Object> typedClientInputPort);
	DuplexRPCServerInputPort createDuplexRPCServerInputPort(
			DuplexServerInputPort<Object> typedServerInputPort);

}
