package inputport.datacomm.group.buffer;

import inputport.datacomm.group.GroupServerInputPort;

import java.nio.ByteBuffer;




public interface BufferGroupServerInputPortFactory {
	public GroupServerInputPort<ByteBuffer> createBufferServerInputPort(String theServerId, String theServerName);
	
}
