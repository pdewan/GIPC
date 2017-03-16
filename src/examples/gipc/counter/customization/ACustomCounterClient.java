package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.duplex.DuplexReceivedCallInvokerSelector;
import inputport.rpc.duplex.DuplexSentCallCompleterSelector;
import inputport.rpc.duplex.SynchronousDuplexReceivedCallInvokerSelector;
import port.trace.rpc.RPCTraceUtility;
import examples.gipc.counter.layers.AMultiLayerCounterClient;

public class ACustomCounterClient extends AMultiLayerCounterClient{
	public static void setFactories() {
		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(
				new ACustomDuplexReceivedCallInvokerFactory());
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(
				new ACustomSentCallCompleterFactory());
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(
				new ACustomDuplexObjectInputPortFactory());
	;
	}
	public static void main (String[] args) {	
//		RPCTraceUtility.setTracing();
		setFactories();
//		BufferTraceUtility.setTracing();
		init("Client 1");
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
