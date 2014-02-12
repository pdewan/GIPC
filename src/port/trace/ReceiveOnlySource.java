package port.trace;

import util.trace.TraceableInfo;

public class ReceiveOnlySource extends TraceableInfo{

	public ReceiveOnlySource( Object aSource) {
		super("Object " + aSource + " is receive only", aSource);
	}

	public static ReceiveOnlySource newCase(Object aSource) {
    	String aMessage = "Object " + aSource + " is receive only";
    	ReceiveOnlySource retVal = new ReceiveOnlySource(aSource);
    	retVal.announce();
    	return retVal;
	}

}
