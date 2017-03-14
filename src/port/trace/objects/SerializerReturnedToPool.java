package port.trace.objects;

import util.trace.TraceableInfo;
import inputport.datacomm.NamingSender;

public class SerializerReturnedToPool extends TraceableInfo {
	
	
	public SerializerReturnedToPool(String aMessage, Object aFinder, Object aPool, Object aSerializer) {
		super(aMessage, aFinder);;
	}
	
	
	public static SerializerReturnedToPool newCase(Object aFinder,  Object aPool, Object aSerializer) {
		String aMessage = aPool + "<--" + aSerializer;
		SerializerReturnedToPool retVal = new SerializerReturnedToPool(aMessage, aFinder, aPool,  aSerializer);
		retVal.announce();
		return retVal;

	}

}
