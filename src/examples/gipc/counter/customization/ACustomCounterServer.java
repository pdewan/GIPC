package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import util.misc.ThreadSupport;
import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;
import examples.gipc.counter.layers.AMultiLayerCounterServer;

public class ACustomCounterServer extends AMultiLayerCounterServer{
	public static long POLLING_TIME = 1000;
	/*
	 * Simply call the client factory method
	 */
	public static void setFactories() {
		ACustomCounterClient.setFactories();

	}
	/**
	 * Ugly polling, but trying to make the implementation of parameterless
	 * recive easier.
	 */
	public static void waitForFirstMessage() {
		while (duplexRPCServerInputPort.receive() == null) {
			ThreadSupport.sleep(POLLING_TIME);
		}
		
	}
	/**
	 * Code for making explicit receive calls to receive messages
	 * It does not work in the default implementation. Your assignment
	 * should make it work.
	 */
	public static void doReceive() {
		waitForFirstMessage();		
		while (true) {
			ReceiveReturnMessage aReceivedMessage = duplexRPCServerInputPort.receive();
			if (aReceivedMessage == null) {
				break;
			}
			System.out.println("Received message:" + aReceivedMessage );
		}
	}
	public static void main (String[] args) {	
		ObjectTraceUtility.setTracing();
		RPCTraceUtility.setTracing();
		setFactories();
		init();
		setPort();
		addListeners(); // for receiving bytebuffer and object messages implicitly		
		doReceive(); // infinite loop for explicit receives
		
	}
	

}
