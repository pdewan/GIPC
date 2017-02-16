package extraip;

import inputport.rpc.RemoteCall;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.AnRPCReturnValueQueue;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;
import inputport.rpc.duplex.RPCReturnValueQueue;

import java.lang.reflect.Method;

public class ADuplexUniImplicitRPCFunctionHandler 
	implements ReturnerOfFunctionCall{
	RPCReturnValueQueue rpcReturnValueReceiver; 
	public ADuplexUniImplicitRPCFunctionHandler(LocalRemoteReferenceTranslator aRemotehandler) {
		rpcReturnValueReceiver =  createRPCReturnValueReceiver(aRemotehandler); 
	}
	protected RPCReturnValueQueue createRPCReturnValueReceiver(LocalRemoteReferenceTranslator aRemoteHandler) {
		return  new AnRPCReturnValueQueue(aRemoteHandler, null, null);
	}
	
	@Override
	public Object returnValueOfRemoteMethodCall(String aRemoteEndPoint, RemoteCall call) {
		Method method = ((SerializableCall)call).getSerializableMethod().getMethod();
		if (method.getReturnType() == Void.TYPE) {
			return null;
		}
		try {
			return rpcReturnValueReceiver.takeReturnValue();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
	}

	@Override
	public boolean maybeProcessReturnValue(String source, Object message) {
		if (message instanceof RPCReturnValue) {
			rpcReturnValueReceiver.putReturnValue((RPCReturnValue) message);
			return true;
		} else {
			return false;
		}
	}

}
