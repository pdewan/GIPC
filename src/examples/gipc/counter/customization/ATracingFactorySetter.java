package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import serialization.SerializerSelector;

/**
 * Class that shows how you can override the following kinds of GIPC objects:
 *   a) a duplex object client and server port - needed to implement
 *   explicit receive
 *   b) the call completer in the process that invokes a remote call. In the assignment
 *   this is used to implement your own scheme for blocking a function or procedure call.
 *   b) the call invoker in the process in which a remote call is invoked.
 *   Used in the assignment to send a return message to unblock a procedure call.
 *
 */
public class ATracingFactorySetter implements FactorySetter{

	@Override
	public void setFactories() {
		/*
		 * Determines the ports  for sending and
		 * receiving objects. Needed for sync receive, as you will
		 * define a new port that overrides the receive stub inherited
		 * from the regular GIPC port. Look at the set factory, 
		 * ACustomDuplexObjectInputPortFactory
		 */
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(
				new ACustomDuplexObjectInputPortFactory());
		
		
		/*
		 * Two alternatives for received call invoker factory, with one
		 * commented out. This factory determines the object that 
		 * actually calls a method of a remote object in response to
		 * a received message. You will need the commented out asynchronous 
		 * version when you implement blocking procedure calls. Look at
		 * each of the set factories, ACustomDuplexReceivedCallInvokerFactory and
		 * AnAsynchronousCustomDuplexReceivedCallInvokerFactory
		 */
		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
				new ACustomDuplexReceivedCallInvokerFactory());		
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
//				new AnAsynchronousCustomDuplexReceivedCallInvokerFactory());
		
		/*
		 * Determines the object that processes the return value, if any, of
		 * a remote call
		 */
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
				new ACustomSentCallCompleterFactory());
		
		
		/*
		 * This is for the serializer assignment, determines the serializer
		 */
		SerializerSelector.setSerializerFactory(new ACustomSerializerFactory());	
	}

}
