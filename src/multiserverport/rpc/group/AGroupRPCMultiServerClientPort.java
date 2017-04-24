package multiserverport.rpc.group;


import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;
import inputport.rpc.group.AGroupSerializableCallTrapperFactory;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.rpc.duplex.ADuplexRPCMultiServerClientPort;
import port.common.GlobalState;



public class AGroupRPCMultiServerClientPort extends ADuplexRPCMultiServerClientPort implements GroupRPCMultiServerClientPort {

	GroupMultiServerClientPort<Object> objectGroupMultiServerClientPort;
//	GroupReturnerOfValueOfRemoteFunctionCall groupFunctionHandler;
	protected GroupSendTrapper<Object, Object> groupSerializableCallSendTrapper;

	public AGroupRPCMultiServerClientPort(GroupMultiServerClientPort<Object> aTypedGroupMultiServerClientPort) {
		super(aTypedGroupMultiServerClientPort);
		objectGroupMultiServerClientPort = aTypedGroupMultiServerClientPort;
 		groupSerializableCallSendTrapper = GlobalState.getGroupSerializableCallTrapper().createGroupSendTrapper(this, objectGroupMultiServerClientPort);

//		groupFunctionHandler = createWaitingGroupRPCFunctionHandler(remoteHandler);

	}
//	protected GroupReturnerOfValueOfRemoteFunctionCall createWaitingGroupRPCFunctionHandler(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new AGroupReturnerOfValueOfRemoteFunctionCall(aRemoteHandler);
//	}


	@Override
	public Object getServerObject(String name) {
		return rpcRegistry.getServerObject(name);
	}



	
//	@Override
//	public Object[] call(Set<String> clientNames, String objectName, Method method,
//			Object[] args) {
//		Object[] retVal = new Serializable[clientNames.size()];
//		RPCReturnValueReceiver[] rpcReturnValueReceivers = null;
//
//		remoteHandler.transformSentRemoteReferences(args);
//		Object serializableCall = createSerializableCall(objectName, method, args);
//
//		send(clientNames, serializableCall);
//		boolean isFunction = !method.getReturnType().equals(Void.TYPE);
//		if (isFunction) {
//			retVal =  groupFunctionHandler.returnValueOfRemoteFunctionCall(clientNames, (SerializableCall) serializableCall);
//		} 
//		return retVal;
//	}
	@Override
	public Object call(Set<String> clientNames, String objectName, Method method,
			Object[] args) {
		Object[] retVal = new Serializable[clientNames.size()];
		Object serializableCall = marshallCall(objectName, method, args);
		groupSerializableCallSendTrapper.send(clientNames, serializableCall);
//		boolean isFunction = !method.getReturnType().equals(Void.TYPE);
//		if (isFunction) {
//			retVal =  groupFunctionHandler.returnValueOfRemoteFunctionCall(clientNames, (SerializableCall) serializableCall);
//		} 
		return groupSerializableCallSendTrapper.getSendReturnValue(clientNames, serializableCall);
//		return retVal;
	}


	@Override
	public Object callAll(Method method, Object[] args) {
		return call(getConnections(), method, args);
	}

	@Override
	public Object[] callOthers(Method method, Object[] args) {
		if (getSender() == null) return null;
		Set<String> clientNames = getConnections();
		clientNames.remove(getSender());
		call(clientNames, method, args);
		return null;
	}
	


	@Override
	public Object callAll(String objectName, Method method,
			Object[] args) {
		return call(getConnections(), objectName, method, args);
	}
	@Override
	public Object callOthers(String objectName, Method method,
			Object[] args) {
		if (getSender() == null) return null;
		Set<String> clientNames = getConnections();
		clientNames.remove(getSender());
		return call(clientNames, objectName, method, args);
	}
	@Override
	public Object call(Set<String> clientNames, Method method,
			Object[] args) {
		return call(clientNames, method.getDeclaringClass().getName(), method, args);
	}
	@Override
	public Object callAll(Class type, Method method, Object[] args) {
		// TODO Auto-generated method stub
		return callAll(type.getName(), method, args);
	}
	@Override
	public Object callOthers(Class type, Method method,
			Object[] args) {
		return callOthers(type.getName(), method, args);
	}
	@Override
	public Object call(Class type, Set<String> clientNames,
			Method method, Object[] args) {
		return call(clientNames, type.getName(), method, args);
	}

	
	@Override
	public void sendAll(Object message) {
		objectGroupMultiServerClientPort.sendAll(message);
		
	}
	@Override
	public void sendOthers(Object message) {
		objectGroupMultiServerClientPort.sendOthers(message);
		
	}
	@Override
	public void send(Collection<String> clientNames, Object message) {
		objectGroupMultiServerClientPort.send(clientNames, message);
	}
	
	@Override
	public GroupSendTrapper<Object, Object> getGroupSendTrapper() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public GroupToUniSendTrapper<Object, Object> getGroupToUniSendTrapper() {
		return objectGroupMultiServerClientPort.getGroupToUniSendTrapper();
	}
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<Object, Object> groupSendTrapper) {
		objectGroupMultiServerClientPort.setGroupSendTrapper(groupSendTrapper);
	}
	@Override
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<Object, Object> groupToUniSendTrapper) {
		objectGroupMultiServerClientPort.setGroupToUniSendTrapper(groupToUniSendTrapper);
	}
	static {
		GroupTrapperFactory<Object, Object> factory = new AGroupSerializableCallTrapperFactory();
		GlobalState.getGroupSerializableCallTrapper().
			setGroupSendTrapperFactory(factory);	
	}
	protected Set<String> getConnectionsAndMe() {
		Set<String> retVal = getConnections();
		retVal.add(getLocalName());
		return retVal;
	}
	@Override
	public Object callAllAndMe(Method method, Object[] args) {
		// TODO Auto-generated method stub
		return call(getConnectionsAndMe(), method, args);
	}
	@Override
	public Object callAllAndMe(String objectName, Method method,
			Object[] args) {
		Set<String> aConnections = getConnectionsAndMe();
		return call (aConnections, objectName, method, args);
//		return call(getConnections(), objectName, method, args);
	
	}

	@Override
	public Object callAllAndMe(Class type, Method method, Object[] args) {
		// TODO Auto-generated method stub
		return callAllAndMe(type.getName(), method, args);
	}
	
}
