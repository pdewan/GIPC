package inputport.datacomm.duplex.object;


import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;


public class DuplexObjectInputPortSelector  {
	static DuplexInputPortFactory<Object> inputPortFactory = new ADuplexObjectInputPortFactory();
	public static void setDuplexInputPortFactory(DuplexInputPortFactory<Object> theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static DuplexServerInputPort<Object> createDuplexServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createDuplexServerInputPort(theServerId, theServerName);
	}
	public static DuplexClientInputPort<Object> createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
}
