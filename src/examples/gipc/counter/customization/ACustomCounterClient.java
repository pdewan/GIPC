package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.SynchronousDuplexReceivedCallInvokerSelector;
import serialization.SerializerSelector;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;
/**
 * A class that uses factories to change the components of the GIPC RPC
 * implementation.
 * 
 * It also calls theexplicit receive operation implemented currently in 
 * GIPC by a stub method.
 *
 */
public class ACustomCounterClient extends AMultiLayerCounterClient{
	/**
	 * Method called by the server and client to change the custom components
	 */
	public static void setFactories() {
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
		 * Determines the ports  for sending and
		 * receiving objects
		 */
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(
				new ACustomDuplexObjectInputPortFactory());
		/*
		 * This is for the serializer assignment, determines the serializer
		 */
		SerializerSelector.setSerializerFactory(new ACustomSerializerFactory());	
	}
	/**
	 * Call the superclass methods.
	 * 
	 * Use the main thread to receive and print messages explicitly. USe this
	 * to test your implementation of receive.
	 * 
	 */
	public static void main (String[] args) {
//		BufferTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();
		setFactories();
		init("Client 1");
		setPort();
		sendByteBuffers();
		sendObjects();
		doOperations();	
		while (true) {
			ReceiveReturnMessage aReceivedMessage = gipcRegistry.getRPCClientPort().receive();
			if (aReceivedMessage == null) {
				break;
			}
			System.out.println("Received message:" + aReceivedMessage );
		}
	}
	

}
