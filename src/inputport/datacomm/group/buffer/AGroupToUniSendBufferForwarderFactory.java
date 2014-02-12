package inputport.datacomm.group.buffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapperFactory;

import java.nio.ByteBuffer;


public class AGroupToUniSendBufferForwarderFactory implements GroupToUniSendTrapperFactory<ByteBuffer, ByteBuffer> {

	@Override
	public GroupToUniSendTrapper<ByteBuffer, ByteBuffer> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<ByteBuffer> destination) {
		return new AGroupToUniSendBufferForwarder(destination);
		
	}

}
