package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.nio.AnNIODuplexBufferInputPortFactory;


public class DuplexBufferInputPortSelector  {
	static DuplexInputPortFactory<ByteBuffer> factory = new AnNIODuplexBufferInputPortFactory();
//	static DuplexInputPortFactory<ByteBuffer> factory = new ASocketDuplexInputPortFactory();
	public static void setDuplexBufferInputPortFactory(DuplexInputPortFactory<ByteBuffer> aFactory) {
		factory = aFactory;
	}
	public static DuplexInputPortFactory<ByteBuffer> getDuplexBufferInputPortFactory() {
		return factory;
	}
	public static DuplexServerInputPort<ByteBuffer> createDuplexServerInputPort(String theServerId, String theServerName) {		
		return factory.createDuplexServerInputPort(theServerId, theServerName);
	}
	public static DuplexClientInputPort<ByteBuffer> createDuplexClientInputPort(String theHost, String theServerId, String aServerName, String theClientName){
		return factory.createDuplexClientInputPort(theHost, theServerId, aServerName, theClientName);
	}
}
