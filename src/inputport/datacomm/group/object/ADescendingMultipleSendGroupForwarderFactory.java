package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;


public class ADescendingMultipleSendGroupForwarderFactory  implements MultipleSendGroupForwarderFactory {


	@Override
	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return new ADescendingMultipleSendGroupForwarder(anInputPort, aDestination);
	}

}
