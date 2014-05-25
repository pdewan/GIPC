package inputport.rpc.duplex;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import port.trace.BlockedForReturnValue;
import util.trace.Tracer;

public class AnRPCReturnValueQueue implements RPCReturnValueQueue {
	LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	public static int MAX_OUTSTANDING_CALLS = 10;
	BlockingQueue<RPCReturnValue> returnValueQueue = new ArrayBlockingQueue(10);
	protected String remoteEnd; // mainly for debugging purposes

	public AnRPCReturnValueQueue(LocalRemoteReferenceTranslator aRemoteHandler, String aRemoteEnd) {
		localRemoteReferenceTranslator = aRemoteHandler;
		remoteEnd = aRemoteEnd;
	}

	public void putReturnValue(RPCReturnValue message) {
		try {
//			Tracer.setKeywordPrintStatus(this, true);
			Tracer.info (this, "putting return value " + message.getReturnValue() + " in " + this);
			returnValueQueue.put(message);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public Object takeReturnValue() {
		try {
			// why do the transformation now rather than at time of putting
			Tracer.info (this, "waiting for return value of " + this);
			boolean willBlock = returnValueQueue.isEmpty();
			if (willBlock) {
				BlockedForReturnValue.newCase(this);
			}

			RPCReturnValue message = returnValueQueue.take();
			Tracer.info (this, "got return value " + message + "from " + this);

			Object possiblyRemoteRetVal = message.getReturnValue();
			Object returnValue = localRemoteReferenceTranslator
					.transformReceivedReference(possiblyRemoteRetVal);
			return returnValue;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public String getRemoteEnd() {
		return remoteEnd;
	}
	public BlockingQueue<RPCReturnValue> returnValueQueue() {
		return returnValueQueue;
	}
}
