package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapperFactory;
import inputport.datacomm.group.GroupToUniSendTrapperSelector;


public class AGroupToUniSendBufferSelector implements GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> {
	GroupToUniSendTrapperFactory<ByteBuffer, ByteBuffer> sendTrapperFactory = new AGroupToUniSendBufferForwarderFactory();
	@Override
	public GroupToUniSendTrapper<ByteBuffer, ByteBuffer> createGroupToUniSendTrapper(
			InputPort anInputPort, NamingSender<ByteBuffer> aDestination) {
		return sendTrapperFactory.createGroupToUniSendTrapper(anInputPort, aDestination);
		
	}

	@Override
	public GroupToUniSendTrapperFactory<ByteBuffer, ByteBuffer> getGroupToUniSendTrapperFactory() {
		return sendTrapperFactory;
	}

	@Override
	public void setGroupToUniSendTrapperFactory(
			GroupToUniSendTrapperFactory<ByteBuffer, ByteBuffer> aSendTrapperFactory) {
		sendTrapperFactory = aSendTrapperFactory;
	}

}
