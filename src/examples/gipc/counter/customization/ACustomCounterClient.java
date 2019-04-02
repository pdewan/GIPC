package examples.gipc.counter.customization;

import examples.gipc.counter.layers.AMultiLayerCounterClient;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
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
	 * Ask the factory setter to set factories. Look at ATracingFactorySetter
	 * to see how the factories are set
	 */
	public static void setFactories() {
		if (FactorySetterFactory.getSingleton() != null) {
			FactorySetterFactory.getSingleton().setFactories();
		}
	}
	
	
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

	

}
