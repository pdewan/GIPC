package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import oldtypedip.AnInheritingTypedGroupServerInputPort;
import oldtypedip.PrintingReplyingTypedReceiver;
import oldtypedip.TypedReceiveListener;
import util.trace.Tracer;




public class OldAGroupRPCServerInputPort extends AnInheritingTypedGroupServerInputPort implements GroupRPCServerInputPort {
	Map<String, RPCReturnValueReceiver> nameToRPCReturnValueReceiver = new HashMap();
	SerializableCallReceiver serializableCallReceiver;
	RPCRegistry rpcManager = new AnRPCRegistery();
	public OldAGroupRPCServerInputPort(String theServerId, String theServerName) {
		super(theServerId, theServerName);
		serializableCallReceiver = new ASerializableCallReceiver(rpcManager, this);
		//delegateTypedListenablePort.addReceiveListener(this);
	}
	RPCReturnValueReceiver getAndMaybeCreateRPCReturnValueReceiver(String clientName) {
		RPCReturnValueReceiver returnValueReceiver = nameToRPCReturnValueReceiver.get(clientName);
		if (returnValueReceiver == null) {
			returnValueReceiver = new AnRPCReturnValueReceiver();
			nameToRPCReturnValueReceiver.put(clientName, returnValueReceiver);
			//addTypedReceiveListener(returnValue);
		}
		return returnValueReceiver;
	}
	@Override
	public void register(Class type, Object server) {
		rpcManager.register(type, server);		
	}

	@Override
	public Object getServer(String name) {
		return rpcManager.getServer(name);
	}

	@Override
	public Serializable call(String clientName, String objectName,  Method method,
			Object[] args) {
		Set<String> clientNames = new HashSet();
		clientNames.add(clientName);
		Object[] retVals = call(clientNames, method, args);
		return (Serializable) retVals[0];		
		
	}
//	public Serializable call(String clientName, SerializableCall serializableCall,
//			boolean isFunction) {
//		RPCReturnValueReceiver rpcReturnValueReceiver = null;
//		if (isFunction) {
//			rpcReturnValueReceiver = getAndMaybeRegisterRPCReturnValueReceiver(clientName);
//		}
//		this.send(clientName, serializableCall);
//		try {
//		if (rpcReturnValueReceiver != null) {
//			rpcReturnValueReceiver.wait();
//			return rpcReturnValueReceiver.getReturnValue();
//		}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		return null;
//	}
	@Override
	public Object[] call(Set<String> clientNames, String objectName, Method method,
			Object[] args) {
		Object[] retVal = new Serializable[clientNames.size()];
		if (clientNames.size() == 0) return  retVal;
		RPCReturnValueReceiver[] rpcReturnValueReceivers = null;
		boolean isFunction = !method.getReturnType().equals(Void.TYPE);
		if (isFunction)  {
			rpcReturnValueReceivers = new RPCReturnValueReceiver[clientNames.size()];
			Iterator<String> clientNameIterator = clientNames.iterator();
			for (int i = 0; i < clientNames.size(); i++) {
//				rpcReturnValueReceivers[i] = getAndMaybeCreateRPCReturnValueReceiver(clientNames.get(i));
				rpcReturnValueReceivers[i] = getAndMaybeCreateRPCReturnValueReceiver(
						clientNameIterator.next());

			}
		}
		SerializableCall serializableCall = new ASerializableCall(objectName, method, args);
		send(clientNames, serializableCall);
		if (isFunction) {
			for (int i = 0; i < clientNames.size(); i++) {
				try {
//					rpcReturnValueReceivers[i].wait();
					retVal[i] = rpcReturnValueReceivers[i].getReturnValue();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return retVal;
	}

	@Override
	public Object[] callAll(Method method, Object[] args) {
		return call(getConnections(), method, args);
	}

	@Override
	public Object[] callOthers(Method method, Object[] args) {
		if (getLastSender() == null) return null;
		Set<String> clientNames = getConnections();
		clientNames.remove(getLastSender());
		call(clientNames, method, args);
		return null;
	}
	
	@Override
	public void register(String name, Object server) {
		rpcManager.register(name, server);
	}
	@Override
	public void register(Object server) {
		rpcManager.register(server);		
	}
	@Override
	public Object[] callAll(String objectName, Method method,
			Object[] args) {
		return call(getConnections(), objectName, method, args);
	}
	@Override
	public Object[] callOthers(String objectName, Method method,
			Object[] args) {
		if (getLastSender() == null) return null;
		Set<String> clientNames = getConnections();
		clientNames.remove(getLastSender());
		return call(clientNames, objectName, method, args);
	}
	@Override
	public Object[] call(Set<String> clientNames, Method method,
			Object[] args) {
		return call(clientNames, method.getDeclaringClass().getName(), method, args);
	}
	@Override
	public Object[] callAll(Class type, Method method, Object[] args) {
		// TODO Auto-generated method stub
		return callAll(type.getName(), method, args);
	}
	@Override
	public Object[] callOthers(Class type, Method method,
			Object[] args) {
		return callOthers(type.getName(), method, args);
	}
	@Override
	public Object[] call(Class type, Set<String> clientNames,
			Method method, Object[] args) {
		return call(clientNames, type.getName(), method, args);
	}
	@Override
	public Serializable call(String name, Method method, Object[] args) {
		if (getLastSender() == null) return null;
		return call(getLastSender(), name, method, args);
	}
	@Override
	public Serializable call(Method method, Object[] args) {
		if (getLastSender() == null) return null;		
		return call(getLastSender(), method, args);
	}
	@Override
	public Serializable call(String clientName, Class interfaceName,
			Method method, Object[] args) {
		return call (clientName, interfaceName.getName(), method, args);
	}
	@Override
	public Serializable call(Class type, Method method, Object[] args) {
		if (getLastSender() == null) return null;
		return call (getLastSender(), type, method, args);
	}
	
	

	@Override
	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		if (message instanceof SerializableCall) {
			setLastSender(remoteEnd);
			serializableCallReceiver.messageReceived(remoteEnd, (SerializableCall) message);
		} else if (message instanceof RPCReturnValue) {
			RPCReturnValueReceiver rpcReturnValueReceiver = getAndMaybeCreateRPCReturnValueReceiver(remoteEnd);
			rpcReturnValueReceiver.messageReceived(remoteEnd, (RPCReturnValue) message);
			setLastSender(remoteEnd);
		} else
			super.notifyTypedPortReceive(remoteEnd, message);
	}
	
	public static void main (String[] args) {
		Tracer.showInfo(true);
		GroupRPCServerInputPort serverInputPort = new OldAGroupRPCServerInputPort("9090", "test server");
		serverInputPort.connect();
		PrintingReplyingTypedReceiver messageReceiver = new PrintingReplyingTypedReceiver(serverInputPort);
		RPCReceiver rpcServer = new RPCReceiver(serverInputPort);
		serverInputPort.addConnectionListener(messageReceiver);
		serverInputPort.addDisconnectListener(messageReceiver);

		serverInputPort.register(TypedReceiveListener.class, messageReceiver);
		serverInputPort.register(RPCReceiver.class, rpcServer);
		OldAdder adder = new OldAnAdder();
		serverInputPort.register(OldAdder.class, adder);
		OldAGroupAdder groupAdder = new OldAGroupAdder(serverInputPort);
		serverInputPort.register(GroupAdder.class, groupAdder);
		//serverInputPort.addReceiveListener(echoingReceiveListener);	
		serverInputPort.addSendListener(messageReceiver);
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
		return call(type, method, args);
	}
}
