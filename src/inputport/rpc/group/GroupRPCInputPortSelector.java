package inputport.rpc.group;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

public class GroupRPCInputPortSelector {
	static GroupRPCInputPortFactory inputPortFactory = new AGroupRPCInputPortFactory();
	public static void setInputPortFactory(GroupRPCInputPortFactory theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static GroupRPCInputPortFactory getInputPortFactory() {
		return inputPortFactory;
	}	
	public static GroupRPCServerInputPort createGroupRPCServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createGroupRPCServerInputPort(theServerId, theServerName);
	}
	public static DuplexRPCClientInputPort createDuplexRPCClientInputPort(String theHost, String theServerId, String theServerName, String theClientName){
		return inputPortFactory.createDuplexRPCClientInputPort(theHost, theServerId, theServerName, theClientName);
	}
}
