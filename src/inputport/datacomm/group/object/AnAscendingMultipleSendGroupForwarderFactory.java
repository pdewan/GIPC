package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;


public class AnAscendingMultipleSendGroupForwarderFactory  implements MultipleSendGroupForwarderFactory {


	@Override
	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return new AnAscendingMultipleSendGroupForwarder(anInputPort, aDestination);
	}

}
