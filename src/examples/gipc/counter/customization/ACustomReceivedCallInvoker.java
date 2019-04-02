package examples.gipc.counter.customization;


import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.ADuplexReceivedCallInvoker;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

/**
 * Simply traces relevant calls in its GIPC superclass. See the assignment
 * to determine which of these methods you need to trap in your implementation
 */
public class ACustomReceivedCallInvoker extends ADuplexReceivedCallInvoker {
	
	public ACustomReceivedCallInvoker(LocalRemoteReferenceTranslator aRemoteHandler, DuplexInputPort<Object> aReplier, RPCRegistry theRPCRegistry) {
		super(aRemoteHandler, aReplier, theRPCRegistry);
	}
	protected void handleProcedureReturn(String aSender, Exception e) {
		System.out.println("Procedure call returning from:" + aSender + " with exception:" + e);
		super.handleProcedureReturn(aSender, e);
	}
	@Override
	protected void handleFunctionReturn(String sender, Object retVal, Class aRetType, Exception e) {
		System.out.println("Function call returning from:" + sender + " with " + " result " + retVal + "or exception:" + e);
		super.handleFunctionReturn(sender, retVal, aRetType, e);
	}
	
	
}
