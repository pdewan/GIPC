package inputport.rpc.simplex;

import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.rpc.RPCRegistry;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.AnAsynchronousSingleThreadDuplexReceivedCallInvoker;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import util.trace.port.rpc.CallInitiated;
import util.trace.port.rpc.CallReceived;




public class ASimplexCallReceiveTrapper extends AnAbstractReceiveTrapper<Object, Object> {
	protected RPCRegistry rpcRegistry;
	protected ReceivedCallInvoker serializableCallHandler;
	protected ReceivedCallInvoker receivedCallInvoker;
	public ASimplexCallReceiveTrapper(InputPort anInputPort, ReceiveNotifier aReceiveNotifier) {
		super (anInputPort, aReceiveNotifier);
		rpcRegistry = (RPCRegistry) anInputPort;
//		createReceivedCallInvoker();
//		DistEventsBus.newEvent(new AConnectionEvent(rpcRegistry, this, false, false));

	}
	protected boolean setLastSender() {
		return ! (receivedCallInvoker() instanceof AnAsynchronousSingleThreadDuplexReceivedCallInvoker);
	}
	@Override
	public void notifyPortReceive(String remoteEnd, Object message) {
//		Tracer.info(this, " Processing serialized call:" + message + " from:" + remoteEnd);
		if (message instanceof RemoteCall) {
			Tracer.info(this, " Processing serialized call:" + message + " from:" + remoteEnd);

			RemoteCall aCall = (RemoteCall) message;
			CallReceived.newCase(this, destination, inputPort.getLocalName(), aCall); 
			CallInitiated.newCase(this, destination, inputPort.getLocalName(), aCall); 
			receivedCallInvoker().messageReceived(remoteEnd,  aCall);

		} 
		else
			destination.notifyPortReceive(remoteEnd, message);	 	
		
	}
	protected void sendDistEvent(ReceivedCallInvoker newReceivedCallInvoker) {
		if (receivedCallInvoker == null) {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newReceivedCallInvoker, false));
		} else {
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, receivedCallInvoker, newReceivedCallInvoker, false, true));
		}
	}
	protected  ReceivedCallInvoker createReceivedCallInvoker() {		
		ReceivedCallInvoker newReceivedCallInvoker = new ASimplexReceivedCallInvoker(rpcRegistry);
		sendDistEvent(newReceivedCallInvoker);
//		DistEventsBus.newEvent(new AConnectionEvent(this, newReceivedCallInvoker, false));
		receivedCallInvoker = newReceivedCallInvoker;
		return newReceivedCallInvoker;
	}
	protected  ReceivedCallInvoker receivedCallInvoker() {
		if (serializableCallHandler != null)
			return serializableCallHandler;		
		return createReceivedCallInvoker();
	}
	

}
