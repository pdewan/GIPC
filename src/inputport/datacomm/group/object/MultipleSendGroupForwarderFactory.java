package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;


public interface MultipleSendGroupForwarderFactory  {


	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination);

}
