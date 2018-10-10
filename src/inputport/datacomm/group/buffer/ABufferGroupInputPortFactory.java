package inputport.datacomm.group.buffer;


import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.GroupServerInputPort;



public class ABufferGroupInputPortFactory implements GroupInputPortFactory<ByteBuffer> {
	@Override
	public GroupServerInputPort<ByteBuffer> createGroupServerInputPort(String theServerId,
			String theServerName) {
		DuplexServerInputPort<ByteBuffer> duplexPort = DuplexBufferInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
//		GroupNamingSender<ByteBuffer> namingSender = new AnUntypedGroupNamingSender(duplexPort);
		return new ABufferGroupServerInputPort (duplexPort);	
	}
	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		return DuplexBufferInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		
	}
}
