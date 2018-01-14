package examples.gipc.counter.customization;


import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.ADuplexReceivedCallInvoker;
import inputport.rpc.duplex.AnRPCReturnValue;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;

import java.io.Serializable;

import util.trace.Tracer;
import util.trace.port.rpc.ReturnMessageCreated;


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
