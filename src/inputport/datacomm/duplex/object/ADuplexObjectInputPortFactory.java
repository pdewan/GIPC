package inputport.datacomm.duplex.object;


import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;

import java.nio.ByteBuffer;



public class ADuplexObjectInputPortFactory implements DuplexInputPortFactory<Object> {


//	@Override
	public DuplexClientInputPort<Object> createDuplexClientInputPort(
			String theServerHost, String theServerId, String aServerName, String aClientName) {
		DuplexClientInputPort<ByteBuffer> bbClientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, aServerName, aClientName);
		return new ADuplexObjectClientInputPort(bbClientInputPort);
		
	}
//	@Override
	public DuplexServerInputPort<Object> createDuplexServerInputPort(
			String theServerId, String theServerName) {
		DuplexServerInputPort<ByteBuffer> bbServerInputPort = DuplexBufferInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
//		DuplexServerInputPort<Serializable> retVal = 
		return new ADuplexObjectServerInputPort(bbServerInputPort);
	}
}
