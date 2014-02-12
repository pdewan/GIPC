package inputport.rpc.duplex;


import inputport.datacomm.NamingSender;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.simplex.ASimplexReceivedCallInvoker;

import java.io.Serializable;
import java.lang.reflect.Method;

import util.trace.Tracer;



public class ADuplexReceivedCallInvoker extends ASimplexReceivedCallInvoker implements DuplexReceivedCallInvoker {
	protected DuplexInputPort<Object> replier;
	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	public ADuplexReceivedCallInvoker(LocalRemoteReferenceTranslator aLocalRemoteReferenceTranslator, DuplexInputPort<Object> aReplier, RPCRegistry theRPCRegistry) {
		super(theRPCRegistry);
		replier = aReplier;
		localRemoteReferenceTranslator = aLocalRemoteReferenceTranslator;
	}
	@Override
	protected Object invokeMethod (Method method, Object targetObject, Object[] args) {
//		System.out.println("about to call remote translator");
		localRemoteReferenceTranslator.transformReceivedReferences(args);
//		System.out.println("about to invoke methodr");

		return super.invokeMethod(method, targetObject, args);

	}	
	@Override
	protected void handleFunctionReturn(String aSource, Object retVal) {
			Object possiblyTransformedRetVal = localRemoteReferenceTranslator.transformSentReference(retVal);
			replier.reply (aSource, createRPCReturnValue((Serializable) possiblyTransformedRetVal));	 // need a special reply call for the case when we have a replicated port
	}	
	protected RPCReturnValue createRPCReturnValue(Object retVal) {
		RPCReturnValue rpcReturnValue = new AnRPCReturnValue(retVal);
		Tracer.info(this, "Composed return value:" + retVal);
		return rpcReturnValue;
	}
	public  DuplexInputPort<Object> getReplier() {
		return replier;
	}

}
