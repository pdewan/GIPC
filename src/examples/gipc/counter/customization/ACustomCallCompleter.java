package examples.gipc.counter.customization;

import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.duplex.object.explicitreceive.ExplicitSourceReceive;
import inputport.rpc.duplex.ADuplexSentCallCompleter;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexSentCallCompleter;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;

public class ACustomCallCompleter 
	extends ADuplexSentCallCompleter	{
	protected ExplicitSourceReceive<Object>  receiver;
//	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	
	public ACustomCallCompleter(DuplexRPCInputPort aPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		super(aPort, aRemoteHandler);
		
	}	
	@Override
	protected void processReturnValue(String source, Object message) {
		System.out.println ("Processing return value of call:" + source + "." + message);
		super.processReturnValue(source, message);
		
	}
	@Override
	public Object returnValueOfRemoteFunctionCall(String aRemoteEndPoint, Object aMessage) {
		Object retVal = super.returnValueOfRemoteFunctionCall(aRemoteEndPoint, aMessage);
		
		System.out.println (aRemoteEndPoint + "." + aMessage + "-->" + retVal);
		return retVal;

	}

}
