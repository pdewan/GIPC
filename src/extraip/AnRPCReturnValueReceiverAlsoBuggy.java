package extraip;


import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;


public class AnRPCReturnValueReceiverAlsoBuggy implements BuggyRPCReturnValueReceiver {
//	Object returnValue;
	boolean shouldWait  = true;
	LocalRemoteReferenceTranslator remoteHandler;
//	public static int MAX_RETURN_VALUES = 100;
	BlockingQueue returnValueQueue = new LinkedBlockingQueue();
	public AnRPCReturnValueReceiverAlsoBuggy(LocalRemoteReferenceTranslator aRemoteHandler) {
		remoteHandler = aRemoteHandler;		
	}
	public  synchronized void  messageReceived(String remoteClientName, RPCReturnValue message) {
//		if (!(message instanceof RPCReturnValue)) return;
		Object possiblyRemoteRetVal = (message).getReturnValue();
		Object returnValue = (message).getReturnValue();
		try {
			returnValueQueue.put(returnValue);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		shouldWait = false;
		// the problem is that many people may be waiting and there is only one should wait!
		this.notify();		
	}
	public synchronized Object getReturnValue() {
		try {
		return returnValueQueue.take();
//		if (shouldWait)
//		wait();
//		shouldWait = true;
//		return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
