package inputport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.GroupServerInputPort;


public class BufferGroupInputPortSelector  {
	static GroupInputPortFactory<ByteBuffer> inputPortFactory = new ABufferGroupInputPortFactory();
	public static void setGroupInputPortFactory(GroupInputPortFactory<ByteBuffer> theInputPortFactory) {
		inputPortFactory = theInputPortFactory;
	}
	public static GroupServerInputPort<ByteBuffer> createGroupServerInputPort(String theServerId, String theServerName) {
		return inputPortFactory.createGroupServerInputPort(theServerId, theServerName);
	}
	public static DuplexClientInputPort createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return inputPortFactory.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
}
