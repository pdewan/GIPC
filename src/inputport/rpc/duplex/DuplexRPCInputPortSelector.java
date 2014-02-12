package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;

public class DuplexRPCInputPortSelector {
	static DuplexRPCInputPortFactory inputPortFactory ;
	public static void setInputPortFactory(DuplexRPCInputPortFactory theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}		
	public static DuplexRPCServerInputPort createDuplexRPCServerInputPort(String theServerId, String theServerName) {
		maybeInitializefactories();
		return inputPortFactory.createDuplexRPCServerInputPort(theServerId, theServerName);
	}
	public static DuplexRPCClientInputPort createDuplexRPCClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		maybeInitializefactories();
		return inputPortFactory.createDuplexRPCClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
	public static DuplexRPCClientInputPort createDuplexRPCClientInputPort(
			DuplexClientInputPort<Object> typedClientInputPort) {
		maybeInitializefactories();
		return inputPortFactory.createDuplexRPCClientInputPort(typedClientInputPort);
	}
	public static DuplexRPCServerInputPort createDuplexRPCServerInputPort(
			DuplexServerInputPort<Object> typedServerInputPort) {
		maybeInitializefactories();
		return inputPortFactory.createDuplexRPCServerInputPort(typedServerInputPort);
	}
	
	static void maybeInitializefactories() {
		if (inputPortFactory == null)
			(new ADuplexRPCInputPortLauncherSupport()).init();
	}
	
}
