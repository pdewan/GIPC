package inputport.rpc.duplex;


import inputport.datacomm.NamingSender;
import inputport.datacomm.SendToUnconnectedPortException;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.buffer.nio.AScatterGatherSelectionManager;
import inputport.rpc.RPCRegistry;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;



public class CopyOfAnAsynchronousDuplexReceivedCallInvoker 
		// should probably use delegation here as there is little use of inheritance
		// after creating a single consumer thread
		// or at least the thread should be in a separate class
		extends ADuplexReceivedCallInvoker 
		implements Runnable {
	// there is a separate instance of it for each  call
	// each instance should not create a separate thread
	// otherwise the relayer will not serialize matters
	// so let us create a staic thread
	static BlockingQueue<MessageWithSourceAndReceiver> callQueue = 
			new LinkedBlockingQueue(AScatterGatherSelectionManager.getMAX_OUTSTANDING_WRITES());
	static Thread callQueueConsumer;
	
	public CopyOfAnAsynchronousDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aLocalRemoteReferenceTranslator,
			DuplexInputPort<Object> aReplier, 
			RPCRegistry theRPCRegistry) {
		super(aLocalRemoteReferenceTranslator, aReplier, theRPCRegistry);
		if (callQueueConsumer == null) {
		callQueueConsumer = new Thread(this);
		callQueueConsumer.setName("Asynchronous Received Call Invoker");
		callQueueConsumer.start();
		}
	}
	@Override
	public void messageReceived(String aSender, Object aMessage) {
		if (callQueue.size() == AScatterGatherSelectionManager.getMAX_OUTSTANDING_WRITES() ) {
			Tracer.error("max outstanding method calls exceed limit of" + AScatterGatherSelectionManager.getMAX_OUTSTANDING_WRITES());
		} else {
			callQueue.add(new AMessageWithSourceAndReceiver(aSender, aMessage, this));
		}
	}

	@Override
	public void run() {
		try {
			MessageWithSourceAndReceiver message = callQueue.take();
			super.messageReceived(message.getSource(), message.getMessage());
		} catch (SendToUnconnectedPortException connecte) {
			throw connecte;
		}
		catch (Exception e) {
			e.printStackTrace();			
		}
		
	}
	
}
