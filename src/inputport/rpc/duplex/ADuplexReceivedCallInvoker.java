package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.simplex.ASimplexReceivedCallInvoker;

import java.io.Serializable;
import java.lang.reflect.Method;

import trace.port.rpc.ReturnMessageCreated;
import trace.port.rpc.SentObjectTransformed;
import util.trace.Tracer;

public class ADuplexReceivedCallInvoker extends ASimplexReceivedCallInvoker
		implements DuplexReceivedCallInvoker {
	protected DuplexInputPort<Object> replier;
	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;

	public ADuplexReceivedCallInvoker(
			LocalRemoteReferenceTranslator aLocalRemoteReferenceTranslator,
			DuplexInputPort<Object> aReplier, RPCRegistry theRPCRegistry) {
		super(theRPCRegistry);
		replier = aReplier;
		localRemoteReferenceTranslator = aLocalRemoteReferenceTranslator;
	}

	@Override
	protected Object invokeMethod(Method method, Object targetObject,
			Object[] args) throws Exception {
		// System.out.println("about to call remote translator");
		localRemoteReferenceTranslator.transformReceivedReferences(args);
		// System.out.println("about to invoke methodr");

		return super.invokeMethod(method, targetObject, args);

	}

	protected void replyPossiblyTransformedMethodReturnValue(String aSource,
			Object retVal, Class aRetType, Exception e) {
		if (e != null) {
			replier.reply(aSource, createRPCReturnValue(e, true));
			return;
		}
		Object possiblyTransformedRetVal = retVal;
		if (retVal != null && aRetType != null) {
			possiblyTransformedRetVal = localRemoteReferenceTranslator
					.transformSentReference(retVal, aRetType); // need to get
																// the method
																// and its
																// return type
			SentObjectTransformed.newCase(this, retVal,
					possiblyTransformedRetVal, aRetType);
		}
		replier.reply(
				aSource,
				createRPCReturnValue((Serializable) possiblyTransformedRetVal,
						false)); // need a special reply call for the case when
									// we have a replicated port
	}

	@Override
	protected void handleFunctionReturn(String aSource, Object retVal,
			Class aRetType, Exception e) {
		replyPossiblyTransformedMethodReturnValue(aSource, retVal, aRetType, e);
	}

	protected RPCReturnValue createRPCReturnValue(Object retVal) {
		return createRPCReturnValue(retVal, false);
	}

	protected RPCReturnValue createRPCReturnValue(Object retVal,
			Boolean anIsException) {
		RPCReturnValue rpcReturnValue = new AnRPCReturnValue(retVal,
				anIsException);
		ReturnMessageCreated.newCase(this, retVal, rpcReturnValue);
		Tracer.info(this, "Composed return value:" + retVal);
		return rpcReturnValue;
	}

	public DuplexInputPort<Object> getReplier() {
		return replier;
	}

}
