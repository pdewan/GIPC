package util.trace.port;

import util.trace.TraceableInfo;

public class SendOnlySource extends TraceableInfo{

	public SendOnlySource(String aMessage, Object aSource) {
		super("Object " + aSource + " is send only", aSource);
	}
	
	public static SendOnlySource newCase(Object aSource) {
    	String aMessage = "Object " + aSource + " is send only";
    	SendOnlySource retVal = new SendOnlySource(aMessage, aSource);
    	retVal.announce();
    	return retVal;
	}

}
