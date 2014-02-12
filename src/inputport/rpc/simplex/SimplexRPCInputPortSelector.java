package inputport.rpc.simplex;


import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;

public class SimplexRPCInputPortSelector {
	static SimplexRPCInputPortFactory inputPortFactory = new ASimplexRPCInputPortFactory();
	public static void setInputPortFactory(SimplexRPCInputPortFactory theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}	
	public static SimplexRPCServerInputPort createSimplexRPCServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createRPCServerInputPort(theServerId, theServerName);
	}
	public static SimplexRPCClientInputPort createSimplexRPCClientInputPort(String theHost, String theServerId, String theServerName, String theClientName){
		return inputPortFactory.createRPCClientInputPort(theHost, theServerId, theServerName, theClientName);
	}
	public static SimplexRPCClientInputPort createRPCClientInputPort(
			SimplexClientInputPort<Object> typedClientInputPort) {
		return inputPortFactory.createRPCClientInputPort(typedClientInputPort);
	}
	public static SimplexRPCServerInputPort createRPCServerInputPort(
			SimplexServerInputPort<Object> objectServerInputPort) {
		return inputPortFactory.createRPCServerInputPort(objectServerInputPort);
	}
}
