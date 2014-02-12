package extraip;


import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;


public class AnRPCReturnValueReceiverBuggy implements BuggyRPCReturnValueReceiver {
	Object returnValue;
	boolean shouldWait  = true; // one should wait for multiple waiters
	LocalRemoteReferenceTranslator remoteHandler;
	public AnRPCReturnValueReceiverBuggy(LocalRemoteReferenceTranslator aRemoteHandler) {
		remoteHandler = aRemoteHandler;		
	}
	public  synchronized void  messageReceived(String remoteClientName, RPCReturnValue message) {
//		if (!(message instanceof RPCReturnValue)) return;
		Object possiblyRemoteRetVal = (message).getReturnValue();
//		returnValue = ((RPCReturnValue) message).getReturnValue();
		returnValue = remoteHandler.transformReceivedReference(possiblyRemoteRetVal);
		shouldWait = false;
		this.notify();		
	}
	public synchronized Object getReturnValue() {
		try {
		if (shouldWait)
		wait();
		shouldWait = true;
		return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
