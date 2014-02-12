package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;


public class MultipleSendGroupForwarderSelector  {
	static MultipleSendGroupForwarderFactory factory = new AMultipleSendGroupForwarderFactory();
	
	public static void setMultipleSendGroupForwarderFactory(MultipleSendGroupForwarderFactory aFactory) {
		factory = aFactory;
	}

	public static GroupNamingSender<Object> createGroupSendTrapper(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return factory.createMultipleSendGroupForwarder(anInputPort, aDestination);
	}

}
