package inputport.rpc.simplex;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;



public interface SimplexRPCInputPortFactory {
	public SimplexRPCServerInputPort createRPCServerInputPort(String theServerId, String theServerName);
	public SimplexRPCClientInputPort createRPCClientInputPort(String theServerHost, String theServerId, String theServerName, String theClientName);
	SimplexRPCClientInputPort createRPCClientInputPort(
			SimplexClientInputPort<Object> typedClientInputPort);
	SimplexRPCServerInputPort createRPCServerInputPort(
			SimplexServerInputPort<Object> objectServerInputPort);

}
