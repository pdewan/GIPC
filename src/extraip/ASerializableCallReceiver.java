package extraip;


import inputport.rpc.RPCOnUnregisteredObjectException;
import inputport.rpc.RPCRegistry;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.AnRPCReturnValue;
import inputport.rpc.duplex.RPCReturnValue;

import java.io.Serializable;

import oldtypedip.TypedUniSend;


public class ASerializableCallReceiver implements ReceivedCallInvoker  {
	RPCRegistry rpcRegistry;
	TypedUniSend portSender;
	public ASerializableCallReceiver(RPCRegistry theRPCRegistry, TypedUniSend thePortSender) {
		rpcRegistry = theRPCRegistry;
		portSender = thePortSender;
	}
	protected RPCReturnValue createRPCReturnValue(Serializable retVal) {
		return new AnRPCReturnValue(retVal);
	}
	protected RPCReturnValue createRPCReturnValue(Serializable retVal, Boolean isAnException) {
		return new AnRPCReturnValue(retVal, isAnException);
	}
	void handleReturnValue(Object retVal, Boolean anIsException) {
		if (retVal != null) {
//			portSender.send (new AnRPCReturnValue((Serializable) retVal));		
			portSender.send (createRPCReturnValue((Serializable) retVal, anIsException));
		}
	}
	
	@Override
	public void messageReceived(String remoteClientName, Object aMessage) {
		SerializableCall message = (SerializableCall) aMessage;
		try {
			Object targetObject = rpcRegistry.getServerObject(message.getTargetObject());
			if (targetObject == null) {
				throw new RPCOnUnregisteredObjectException(message.getTargetObject());
			}
			Object newVal = message.getSerializableMethod().getMethod().invoke(targetObject, message.getArgs());
			handleReturnValue(newVal, null);
//			if (newVal != null) {
//				portSender.send (new AnRPCReturnValue((Serializable) newVal));				
//			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
