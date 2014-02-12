package inputport.datacomm.group.object;


import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectServerInputPort;
import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.buffer.BufferGroupInputPortSelector;

import java.nio.ByteBuffer;






public class AGroupObjectInputPortFactory implements GroupInputPortFactory<Object> {
//
//	@Override
//	public GroupServerInputPort<Object> createGroupServerInputPort(String theServerId,
//			String theServerName) {
//		return createTypedGroupServerInputPort(theServerId, theServerName);
//
//	
//	}
	@Override
	public GroupServerInputPort<Object> createGroupServerInputPort(String theServerId,
			String theServerName) {
		GroupServerInputPort<ByteBuffer> bbServerInputPort = BufferGroupInputPortSelector.createGroupServerInputPort(theServerId, theServerName);
		DuplexServerInputPort<Object> typedDuplexInputPort = new ADuplexObjectServerInputPort(bbServerInputPort);
//		DuplexServerInputPort<Serializable> retVal = 
		// need typedDuplexInpuutPort for receiving if not for sending
		return new AGroupObjectServerInputPort(typedDuplexInputPort, bbServerInputPort);
//		return new AnObjectGroupServerInputPort(null, bbServerInputPort);

	}
//	@Override
//	public DuplexClientInputPort<Object> createDuplexClientInputPort(String theServerHost,
//			String theServerId, String theClientName) {
//		return createTypedDuplexClientInputPort(theServerHost, theServerId,  theClientName);
//	}
	@Override
	public DuplexClientInputPort<Object> createDuplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		DuplexClientInputPort<ByteBuffer> bbClientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		return new ADuplexObjectClientInputPort(bbClientInputPort);
		
	}
	
}
