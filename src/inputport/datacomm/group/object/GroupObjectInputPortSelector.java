package inputport.datacomm.group.object;


import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.GroupServerInputPort;


public class GroupObjectInputPortSelector  {
	static GroupInputPortFactory<Object> inputPortFactory = new AGroupObjectInputPortFactory();
	public static void setGroupInputPortFactory(GroupInputPortFactory<Object> theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static GroupServerInputPort<Object> createGroupServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createGroupServerInputPort(theServerId, theServerName);
	}
	public static DuplexClientInputPort<Object> createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
}
