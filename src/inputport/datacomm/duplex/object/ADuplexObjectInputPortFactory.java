package inputport.datacomm.duplex.object;


import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;



public class ADuplexObjectInputPortFactory implements DuplexInputPortFactory<Object> {

	public DuplexClientInputPort<Object> createDuplexClientInputPort(DuplexClientInputPort<ByteBuffer> bbClientInputPort) {
		return new ADuplexObjectClientInputPort(bbClientInputPort);
	}
	public DuplexServerInputPort<Object> createDuplexServerInputPort(DuplexServerInputPort<ByteBuffer> bbServerInputPort) {
		return new ADuplexObjectServerInputPort(bbServerInputPort);
	}
//	@Override
	public DuplexClientInputPort<Object> createDuplexClientInputPort(
			String theServerHost, String theServerId, String aServerName, String aClientName) {
		DuplexClientInputPort<ByteBuffer> bbClientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, aServerName, aClientName);
//		return new ADuplexObjectClientInputPort(bbClientInputPort);
		return createDuplexClientInputPort(bbClientInputPort);
		
	}
//	@Override
	public DuplexServerInputPort<Object> createDuplexServerInputPort(
			String theServerId, String theServerName) {
		DuplexServerInputPort<ByteBuffer> bbServerInputPort = DuplexBufferInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
//		DuplexServerInputPort<Serializable> retVal = 
//		return new ADuplexObjectServerInputPort(bbServerInputPort);
		return createDuplexServerInputPort(bbServerInputPort);
	}
}
