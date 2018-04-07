package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import serialization.SerializerSelector;

public class ATracingFactorySetter implements FactorySetter{

	@Override
	public void setFactories() {
		/*
		 * Determines the ports  for sending and
		 * receiving objects. Needed for sync receive.
		 */
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(
				new ACustomDuplexObjectInputPortFactory());
		
		
		/*
		 * Two alternatives for received call invoker factory, with one
		 * commented out. This factory determines the object that 
		 * actually calls a method of a remote object in response to
		 * a received message
		 */
		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
				new ACustomDuplexReceivedCallInvokerFactory());		
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
//				new AnAsynchronousCustomDuplexReceivedCallInvokerFactory());
		
		/*
		 * Determines the object that processes return value, if any, of
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
