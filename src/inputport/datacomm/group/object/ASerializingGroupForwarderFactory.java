package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupSendTrapperFactory;

import java.nio.ByteBuffer;


public class ASerializingGroupForwarderFactory implements GroupSendTrapperFactory<Object, ByteBuffer> {


	@Override
	public GroupSendTrapper<Object, ByteBuffer> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<ByteBuffer> aDestination) {
		return new ASerializingGroupForwarder(anInputPort, aDestination);
	}

}
