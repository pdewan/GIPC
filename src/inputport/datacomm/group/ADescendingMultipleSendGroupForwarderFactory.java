package inputport.datacomm.group;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.object.MultipleSendGroupForwarderFactory;


public class ADescendingMultipleSendGroupForwarderFactory  implements MultipleSendGroupForwarderFactory {


	@Override
	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return new ADescendingMultipleSendGroupForwarder(anInputPort, aDestination);
	}

}
