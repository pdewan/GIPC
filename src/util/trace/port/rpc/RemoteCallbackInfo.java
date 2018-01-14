package util.trace.port.rpc;

import util.trace.TraceableInfo;
import util.trace.port.MessageReceiveInfo;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.AnAsynchronousSingleThreadDuplexReceivedCallInvoker;

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
