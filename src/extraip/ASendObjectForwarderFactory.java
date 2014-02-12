package extraip;

import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.object.ObjectSendTrapperFactory;

public class ASendObjectForwarderFactory implements ObjectSendTrapperFactory {

	@Override
	public NamingSender<Object> createObjectSendTrapper(
			NamingSender<Object> destination) {
		return new ASendObjectForwarder(destination);
	}

}
