package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

import oldtypedip.AnInheritingTypedClientInputPort;


public class AnRPCClientInputPort extends AnInheritingTypedClientInputPort implements RPCClientInputPort {
	RPCReturnValueReceiver rpcReturnValueReceiver =  new AnRPCReturnValueReceiver(); 
	SerializableCallReceiver serializableCallReceiver;
	RPCRegistry rpcRegistry = new AnRPCRegistery();
//	TypedReceiptNotifier typedReceiptNotifier = new ATypedReceiptNotifier();
	public AnRPCClientInputPort(String theHost, String theServerId,
			String theClientName) {
		super(theHost, theServerId, theClientName);
		//addTypedReceiveListener(rpcReturnValueReceiver);
		//addTypedReceiveListener(this);
		serializableCallReceiver = new ASerializableCallReceiver(rpcRegistry, this);
	}

	
	@Override
	public void register(Class type, Object server) {
		rpcRegistry.register(type, server);
		
	}

	@Override
	public Object getServer(String name) {
		return rpcRegistry.getServer(name);
	}
	
	@Override
	public synchronized Serializable call(String objectName, Method method, Object[] args) {			
		SerializableCall serializableCall = new ASerializableCall(objectName, method, args);
		send(serializableCall);
		if (!method.getReturnType().equals(Void.TYPE)) {
			try {
//			rpcReturnValueReceiver.wait();
			return rpcReturnValueReceiver.getReturnValue();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

	@Override
	public Serializable call(Method method, Object[] args) {
		return call (method.getDeclaringClass().getName(), method, args);
	}

	@Override
	public Serializable call(Class type, Method method, Object[] args) {
		return call (type.getName(), method, args );
	}

	@Override
	public void register(String name, Object server) {
		rpcRegistry.register(name, server);
		
	}

	@Override
	public void register(Object server) {
		rpcRegistry.register(server);
		
	}
//	public void addReceiveListener(TypedReceiveListener portReceiveListener) {
//		typedReceiptNotifier.addReceiveListener(portReceiveListener);
//	}

	@Override
	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		if (message instanceof SerializableCall) {
			serializableCallReceiver.messageReceived(remoteEnd, (SerializableCall) message);
		} else if (message instanceof RPCReturnValue) {
			rpcReturnValueReceiver.messageReceived(remoteEnd, (RPCReturnValue) message);
		} else
			super.notifyTypedPortReceive(remoteEnd, message);
	}


	@Override
	public Serializable reply(String name, Method method, Object[] args) {
		return call(name, method, args);
	}


	@Override
	public Serializable reply(Method method, Object[] args) {
		return call(method, args);
	}


	@Override
	public Serializable reply(Class type, Method method, Object[] args) {
		return call (type, method, args);
	}


	@Override
	public Serializable call(String clientName, String objectName,
			Method method, Object[] args) {
		return call(objectName, method, args);
	}


	@Override
	public Serializable call(String clientName, Class interfaceName,
			Method method, Object[] args) {
		return call(interfaceName, method, args);
	}

}
