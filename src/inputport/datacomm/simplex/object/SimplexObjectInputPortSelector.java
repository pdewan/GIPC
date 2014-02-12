package inputport.datacomm.simplex.object;


import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;


public class SimplexObjectInputPortSelector  {
	static SimplexInputPortFactory<Object> inputPortFactory = new AnObjectInputPortFactory();
	public static void setInputPortFactory(SimplexInputPortFactory<Object> theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static SimplexServerInputPort<Object> createSimplexServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createSimplexServerInputPort(theServerId, theServerName);
	}
	public static SimplexClientInputPort<Object> createSimplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createSimplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
}
