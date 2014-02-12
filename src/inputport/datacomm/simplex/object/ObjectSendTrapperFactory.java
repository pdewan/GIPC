package inputport.datacomm.simplex.object;

import inputport.datacomm.NamingSender;

public interface ObjectSendTrapperFactory {
	NamingSender<Object> createObjectSendTrapper(NamingSender<Object>  aDestination);
}
