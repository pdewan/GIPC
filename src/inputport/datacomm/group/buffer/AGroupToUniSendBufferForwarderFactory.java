package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapperFactory;


public class AGroupToUniSendBufferForwarderFactory implements GroupToUniSendTrapperFactory<ByteBuffer, ByteBuffer> {

	@Override
	public GroupToUniSendTrapper<ByteBuffer, ByteBuffer> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<ByteBuffer> destination) {
		return new AGroupToUniSendBufferForwarder(destination);
		
	}

}
