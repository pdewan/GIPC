package port.trace.objects;

import util.trace.TraceableInfo;
import inputport.datacomm.NamingSender;

public class SerializerTakenFromPool extends TraceableInfo {
	
	
	public SerializerTakenFromPool(String aMessage, Object aFinder, Object aPool, Object aSerializer) {
		super(aMessage, aFinder);;
	}
	
	
	public static SerializerTakenFromPool newCase(Object aFinder,  Object aPool, Object aSerializer) {
		String aMessage = aPool + "-->" + aSerializer;
		SerializerTakenFromPool retVal = new SerializerTakenFromPool(aMessage, aFinder, aPool,  aSerializer);
		retVal.announce();
		return retVal;

	}

}
