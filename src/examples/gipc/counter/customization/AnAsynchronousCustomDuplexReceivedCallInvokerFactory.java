package examples.gipc.counter.customization;

import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.DuplexReceivedCallInvokerFactory;
import inputport.rpc.duplex.AnAsynchronousSingleThreadDuplexReceivedCallInvoker;
import inputport.rpc.duplex.DuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
/**
 * This class shows you how to invoke rpc calls in a separate thread (not the
 * selector thread).
 * 
 * Make this class a subclass of the regular received call invoker factory and
 * return an instance of the predefined class AnAsynchronousSingleThreadDuplexReceivedCallInvoker
 * that wraps your regulalar class invoker returned by the superclass.
 *
 */
public class AnAsynchronousCustomDuplexReceivedCallInvokerFactory 
	extends ACustomDuplexReceivedCallInvokerFactory{
	@Override
	public DuplexReceivedCallInvoker createDuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aRemoteHandler,
			DuplexInputPort<Object> aReplier, RPCRegistry anRPCRegistry) {
		return new 
				AnAsynchronousSingleThreadDuplexReceivedCallInvoker(super.createDuplexReceivedCallInvoker(aRemoteHandler, aReplier, anRPCRegistry));
	}
}
