package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.group.GroupServerInputPort;




public interface BufferGroupServerInputPortFactory {
	public GroupServerInputPort<ByteBuffer> createBufferServerInputPort(String theServerId, String theServerName);
	
}
