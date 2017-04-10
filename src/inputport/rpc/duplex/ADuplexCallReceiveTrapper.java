package inputport.rpc.duplex;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.simplex.ASimplexCallReceiveTrapper;




public class ADuplexCallReceiveTrapper extends ASimplexCallReceiveTrapper {
	protected DuplexRPCInputPort duplexRPCInputPort;

	public ADuplexCallReceiveTrapper(InputPort anInputPort, ReceiveNotifier aReceiveNotifier) {
		super (anInputPort, aReceiveNotifier);
		duplexRPCInputPort = (DuplexRPCInputPort)anInputPort;

		
	}
	@Override
	public void notifyPortReceive(String aSource, Object aMessage) {
//		SerializableCallReceiveInfo.newCase(this, destination, duplexRPCInputPort.getLocalName(), (SerializableCall) 
//		Tracer.info(this, " Processing serialized call:" + aMessage + " from:" + aSource);
		DuplexSentCallCompleter callCompleter = ((DuplexCallTrapperSharedState)  duplexRPCInputPort.getSendTrapper().getSharedSenderReceiverState()).duplexSentCallCompleter;
		if (!(callCompleter.maybeProcessReturnValue(aSource, aMessage))) {
			
			if (receivedCallInvoker != null &&   receivedCallInvoker instanceof AsynchronousDuplexReceivedCallInvoker) {
				
//				((CallInvoker) receivedCallInvoker).setReplier(duplexRPCInputPort);
			} else {
			
				duplexRPCInputPort.setSender(aSource); // do not want this set if a return value
			
			}	
				super.notifyPortReceive(aSource, aMessage);
		}		
	}
	@Override
	protected  ReceivedCallInvoker createReceivedCallInvoker() {  	    
		LocalRemoteReferenceTranslator localRemoteReferenceTranslator = ((DuplexCallTrapperSharedState) duplexRPCInputPort.getSendTrapper().getSharedSenderReceiverState()).localRemoteReferenceTranslator;
		ReceivedCallInvoker newReceivedCallInvoker = DuplexReceivedCallInvokerSelector.createDuplexReceivedCallInvoker(localRemoteReferenceTranslator, duplexRPCInputPort, rpcRegistry);
//		DistEventsBus.newEvent(new AConnectionEvent(this, receivedCallInvoker, false));
		sendDistEvent(newReceivedCallInvoker);
		receivedCallInvoker = newReceivedCallInvoker;
		return newReceivedCallInvoker;
	}

	

}
