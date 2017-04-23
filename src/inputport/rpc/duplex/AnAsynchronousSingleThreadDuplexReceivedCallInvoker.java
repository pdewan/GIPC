package inputport.rpc.duplex;


import inputport.datacomm.SendToUnconnectedPortException;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.buffer.nio.AScatterGatherSelectionManager;
import inputport.rpc.RemoteCall;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import port.trace.rpc.ReceivedCallDequeued;
import port.trace.rpc.CallInitiated;
import port.trace.rpc.ReceivedCallQueued;
import port.trace.rpc.ReceivedCallEndedOld;
import util.trace.Tracer;



public class AnAsynchronousSingleThreadDuplexReceivedCallInvoker 
		// should probably use delegation here as there is little use of inheritance
		// after creating a single consumer thread
		// or at least the thread should be in a separate class
//		extends ADuplexReceivedCallInvoker 
		implements AsynchronousDuplexReceivedCallInvoker {
	// there is a separate instance of it for each  call
	// each instance should not create a separate thread
	// otherwise the relayer will not serialize matters
	// so let us create a staic thread
	static BlockingQueue<MessageWithSourceAndReceiver> callQueue = 
			new LinkedBlockingQueue(AScatterGatherSelectionManager.getMaxOutstandingWrites());
	static Thread callQueueConsumer;
	DuplexReceivedCallInvoker synchronousReceivedCallInvoker;
	protected DuplexRPCInputPort replier;
	protected static String lastCaller;
	
	public void setReplier(DuplexRPCInputPort replier) {
		this.replier = replier;
	}
	@Override
	public DuplexInputPort<Object> getReplier() {
		return synchronousReceivedCallInvoker.getReplier();
	}
	
	public AnAsynchronousSingleThreadDuplexReceivedCallInvoker(
			DuplexReceivedCallInvoker aSynchronousReceivedCallInvoker) {
		synchronousReceivedCallInvoker = aSynchronousReceivedCallInvoker;
//		super(aLocalRemoteReferenceTranslator, aReplier, theRPCRegistry);
		try {
		synchronized (callQueue) {
		if (callQueueConsumer == null) {
		callQueueConsumer = new Thread(this);
		callQueueConsumer.setName("Asynchronous Received Call Invoker");
		callQueueConsumer.start();
		}
		}
		} catch (IllegalStateException e) {
			System.err.println("Multiple thread starts");
		}
	}
	@Override
	public void messageReceived(String aSender, Object aMessage) {
		if (callQueue.size() == AScatterGatherSelectionManager.getMaxOutstandingWrites() ) {
			Tracer.error("max outstanding method calls exceed limit of" + AScatterGatherSelectionManager.getMaxOutstandingWrites());
		} else {
//			System.out.println ("Call Queue added:" + aMessage);
			MessageWithSourceAndReceiver aMessageWithSourceAndReceiver = new AMessageWithSourceAndReceiver(aSender, aMessage, synchronousReceivedCallInvoker);
			ReceivedCallQueued.newCase(this, callQueue, aMessageWithSourceAndReceiver);
			callQueue.add(aMessageWithSourceAndReceiver);


//			callQueue.add(new AMessageWithSourceAndReceiver(aSender, aMessage, synchronousReceivedCallInvoker));
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
			MessageWithSourceAndReceiver message = callQueue.take();
			ReceivedCallDequeued.newCase(this, callQueue, message);

			String aSource = message.getSource();
//			System.out.println ("Call Queue removed:" + message);
			if (getReplier() == null) {
			synchronousReceivedCallInvoker.getReplier().setSender(aSource);
			} else {
				getReplier().setSender(aSource);
			}
			setRemoteCaller(aSource);
				
//			Tracer.setKeywordPrintStatus(this, true);
			message.getReceiveListener().messageReceived(message.getSource(), message.getMessage());
			setRemoteCaller(null);

			ReceivedCallEndedOld.newCase(this, message.getReceiveListener(), message.getSource(), (RemoteCall) message.getMessage());
			}
		} catch (SendToUnconnectedPortException connecte) {
			throw connecte;
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		
	}
	public static String getRemoteCaller() {
		// TODO Auto-generated method stub
		return lastCaller;
	}
	public static void setRemoteCaller(String newVal) {
		lastCaller = newVal;
		
	}
	
	
}
