package util.trace.port.rpc;

import inputport.rpc.duplex.AnAsynchronousSingleThreadDuplexReceivedCallInvoker;
import util.trace.TraceableInfo;

public class RemoteCallbackInfo extends TraceableInfo {

	public RemoteCallbackInfo(String aMessage, Object aFinder) {
		super(aMessage, aFinder);
	}
	
	static public String callerPrefix() {
		String aCaller = AnAsynchronousSingleThreadDuplexReceivedCallInvoker.getRemoteCaller();
		if (aCaller != null) {
			return aCaller + "--> ";
		}
		return "";
	}

	

}
