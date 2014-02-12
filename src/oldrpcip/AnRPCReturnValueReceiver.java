package oldrpcip;

import java.io.Serializable;


public class AnRPCReturnValueReceiver implements RPCReturnValueReceiver {
	Serializable returnValue;
	boolean shouldWait  = true;
	public  synchronized void  messageReceived(String remoteClientName, RPCReturnValue message) {
//		if (!(message instanceof RPCReturnValue)) return;
		returnValue = (message).getReturnValue();
		shouldWait = false;
		this.notify();		
	}
	public synchronized Serializable getReturnValue() {
		try {
		if (shouldWait)
		wait();
		return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
