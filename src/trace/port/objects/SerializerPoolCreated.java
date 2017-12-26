package trace.port.objects;

import util.trace.TraceableInfo;
import inputport.datacomm.NamingSender;

public class SerializerPoolCreated extends TraceableInfo {
	Object object;
	
	
	public SerializerPoolCreated(String aMessage, Object aFinder, Object aPool, int aSize) {
		super(aMessage, aFinder);;
		object = aPool;
	}
	
	public Object getPool() {
		return object;
	}
	public static SerializerPoolCreated newCase(Object aFinder,  Object aPool, int aSize) {
		String aMessage = aPool + " [" + aSize + "]" ;
		SerializerPoolCreated retVal = new SerializerPoolCreated(aMessage, aFinder, aPool,  aSize);
		retVal.announce();
		return retVal;

	}

}
