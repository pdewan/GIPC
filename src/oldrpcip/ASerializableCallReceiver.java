package oldrpcip;


import java.io.Serializable;

import oldtypedip.TypedUniSend;


public class ASerializableCallReceiver implements SerializableCallReceiver  {
	RPCRegistry rpcRegistry;
	TypedUniSend portSender;
	public ASerializableCallReceiver(RPCRegistry theRPCRegistry, TypedUniSend thePortSender) {
		rpcRegistry = theRPCRegistry;
		portSender = thePortSender;
	}
	
	@Override
	public void messageReceived(String remoteClientName, SerializableCall message) {
		try {
			Object targetObject = rpcRegistry.getServer(message.getName());
			if (targetObject == null) {
				throw new RPCOfUnregisteredMethodException(message.getName());
			}
			Object newVal = message.getSerializableMethod().getMethod().invoke(targetObject, message.getArgs());
			if (newVal != null) {
				portSender.send (new AnRPCReturnValue((Serializable) newVal));				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
