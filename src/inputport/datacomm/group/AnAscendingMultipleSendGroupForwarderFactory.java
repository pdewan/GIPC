package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.object.MultipleSendGroupForwarderFactory;


public class AnAscendingMultipleSendGroupForwarderFactory  implements MultipleSendGroupForwarderFactory {


	@Override
	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return new AnAscendingMultipleSendGroupForwarder(anInputPort, aDestination);
	}

}
