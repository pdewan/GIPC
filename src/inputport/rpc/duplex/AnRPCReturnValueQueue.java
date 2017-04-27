package inputport.rpc.duplex;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import port.trace.rpc.ReceivedObjectTransformed;
import port.trace.rpc.RemoteCallUnblockingWithReturnValue;
import port.trace.rpc.ReceivedReturnValueQueued;
import port.trace.rpc.RemoteCallBlockedForReturnValue;
import port.trace.rpc.RemoteCallReturnValueDetermined;
import util.trace.Tracer;

public class AnRPCReturnValueQueue implements RPCReturnValueQueue, ConnectionListener {
	LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	public static int MAX_OUTSTANDING_CALLS = 10;
	BlockingQueue<RPCReturnValue> returnValueQueue = new ArrayBlockingQueue(MAX_OUTSTANDING_CALLS);
	protected String remoteEnd; // mainly for debugging purposes
	int numOutStandingCalls;
	boolean isConnected;
	protected InputPort inputPort;

	public AnRPCReturnValueQueue(LocalRemoteReferenceTranslator aRemoteHandler, String aRemoteEnd, InputPort anInputPort) {
		localRemoteReferenceTranslator = aRemoteHandler;
		remoteEnd = aRemoteEnd;
		inputPort = anInputPort;
	}

	public void putReturnValue(RPCReturnValue message) {
		try {
//			Tracer.setKeywordPrintStatus(this, true);
			Tracer.info (this, "putting return value " + message.getReturnValue() + " in " + this);
			returnValueQueue.put(message);
			ReceivedReturnValueQueued.newCase(this, returnValueQueue, message);
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
				if (!isConnected) {
					inputPort.addConnectionListener(this);
					isConnected = true;
				}
				RemoteCallBlockedForReturnValue.newCase(this, this);
			}
			numOutStandingCalls++;
			RPCReturnValue message = returnValueQueue.take();
			numOutStandingCalls--;
			Tracer.info (this, "got return value " + message + "from " + this);
			if (message instanceof ARemoteEndDisconnected) {
				throw new RemoteEndDisconnectedException();
			}
			
			RemoteCallUnblockingWithReturnValue.newCase(this, returnValueQueue, message);
			if (message.isException()) {
				throw new RemoteInvocationException((Throwable) message.getReturnValue());
			}

			Object possiblyRemoteRetVal = message.getReturnValue();
			
//			Object returnValue = localRemoteReferenceTranslator
//					.transformReceivedReference(possiblyRemoteRetVal);
			Object returnValue = possiblyRemoteRetVal;
//			ReceivedObjectTransformed.newCase(this, possiblyRemoteRetVal, returnValue);
			return returnValue;
		} catch (GIPCRemoteException e) {
			throw e;
		}
		catch (Exception e) {
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

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		for (int i=0; i<numOutStandingCalls; i++) {
			try {
				returnValueQueue.put(new ARemoteEndDisconnected());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
}
