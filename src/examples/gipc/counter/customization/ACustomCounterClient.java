package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.SynchronousDuplexReceivedCallInvokerSelector;
import serialization.SerializerSelector;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;
/**
 * A class that uses factories to change the components of the GIPC RPC
 * implementation.
 * 
 * It also calls the explicit receive operation implemented currently in 
 * GIPC by a stub method.
 *
 */
public class ACustomCounterClient extends AMultiLayerCounterClient{
	public static final int NUM_RECEIVES = 4;

	/*
	 * Ask the factory setter to set factories
	 */
	public static void setFactories() {
		if (FactorySetterFactory.getSingleton() != null) {
			FactorySetterFactory.getSingleton().setFactories();
		}
	}
	
//	/**
//	 * Method called by the server and client to change the custom components
//	 */
//	public static void setFactories() {
//		/*
//		 * Two alternatives for received call invoker factory, with one
//		 * commented out. This factory determines the object that 
//		 * actually calls a method of a remote object in response to
//		 * a received message
//		 */
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
//				new ACustomDuplexReceivedCallInvokerFactory());		
////		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
////				new AnAsynchronousCustomDuplexReceivedCallInvokerFactory());
//		
//		/*
//		 * Determines the object that processes return value, if any, of
//		 * a remote call
//		 */
//		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
//				new ACustomSentCallCompleterFactory());
//		
//		/*
//		 * Determines the ports  for sending and
//		 * receiving objects
//		 */
//		DuplexObjectInputPortSelector.setDuplexInputPortFactory(
//				new ACustomDuplexObjectInputPortFactory());
//		/*
//		 * This is for the serializer assignment, determines the serializer
//		 */
//		SerializerSelector.setSerializerFactory(new ACustomSerializerFactory());	
//	}
	
	/**
	 * Code for making explicit receive calls to receive messages
	 * It does not work in the default implementation. Your assignment
	 * should make it work.
	 */
	public static void doReceive() {		
		
		for (int i = 0; i < NUM_RECEIVES; i++) {
			ReceiveReturnMessage aReceivedMessage = duplexRPCClientInputPort.receive();
			if (aReceivedMessage == null) {
				break;
			}
			System.out.println("Received message:" + aReceivedMessage );
		}
	}
	/**
	 * Call the superclass methods.
	 * 
	 * Use the main thread to receive and print messages explicitly. USe this
	 * to test your implementation of receive.
	 * 
	 */
	public static void launch(String aClientName) {
		setFactories();
		AMultiLayerCounterClient.launchClient(aClientName);
		doReceive();
	}
//	public static void main (String[] args) {
//		ObjectTraceUtility.setTracing();
//		RPCTraceUtility.setTracing();		
//		setFactories();
//		init("Client 1");
//		setPort();
//		sendByteBuffers();
//		sendObjects();
//		doOperations();	
//		doReceive();
//	}
	

}
