package inputport.rpc.group;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

//import extraip.AGroupAdder;
//import extraip.GroupAdder;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.rpc.duplex.ADuplexRPCServerInputPort;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import util.trace.port.rpc.RemoteCallFinished;
import util.trace.port.rpc.RemoteCallInitiated;




public class AGroupRPCServerInputPort extends ADuplexRPCServerInputPort implements GroupRPCServerInputPort {

	GroupServerInputPort<Object> objectGroupServerInputPort;
//	GroupReturnerOfValueOfRemoteFunctionCall groupFunctionHandler;
	protected GroupSendTrapper<Object, Object> groupSerializableCallSendTrapper;

	public AGroupRPCServerInputPort(GroupServerInputPort<Object> aTypedGroupServerInputPort) {
		super(aTypedGroupServerInputPort);
		objectGroupServerInputPort = aTypedGroupServerInputPort;
//		groupFunctionHandler = createWaitingGroupRPCFunctionHandler(remoteHandler);
//		uniNamingFunctionHandler = groupFunctionHandler;
// 		groupSerializableCallSendTrapper = GlobalState.getGroupSerializableCallTrapper().createGroupSendTrapper(this, aTypedGroupServerInputPort);

// 		groupSerializableCallSendTrapper = GroupSerializableCallTrapperSelector.getTrapperSelector().createGroupSendTrapper(this, aTypedGroupServerInputPort);
 		
 		setGroupSendTrapper(createGroupSerializableCallSendTrapper());
	}
//	protected GroupReturnerOfValueOfRemoteFunctionCall createWaitingGroupRPCFunctionHandler(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new AGroupReturnerOfValueOfRemoteFunctionCall(aRemoteHandler);
//	}

	protected  GroupSendTrapper<Object, Object> createGroupSerializableCallSendTrapper() {
 		return GroupSerializableCallTrapperSelector.getTrapperSelector().createGroupSendTrapper(this, objectGroupServerInputPort);

	}

	@Override
	public Object getServerObject(String name) {
		return rpcRegistry.getServerObject(name);
	}




	

	
//	@Override
//	public Object[] call(Set<String> clientNames, String objectName, Method method,
//			Object[] args) {
//		Object[] retVal = new Serializable[clientNames.size()];
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
//		Object[] retVal = new Serializable[clientNames.size()];
		RemoteCallInitiated.newCase(this, clientNames, objectName, method, args);

		Object serializableCall = marshallCall(objectName, method, args);
		groupSerializableCallSendTrapper.send(clientNames, serializableCall);
//		boolean isFunction = !method.getReturnType().equals(Void.TYPE);
//		if (isFunction) {
//			retVal =  groupFunctionHandler.returnValueOfRemoteFunctionCall(clientNames, (SerializableCall) serializableCall);
//		} 
		Object retVal = groupSerializableCallSendTrapper.getSendReturnValue(clientNames, serializableCall);
		RemoteCallFinished.newCase(this, clientNames, objectName, method, args, retVal);

		
		return retVal;
//		return retVal;
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
//		RemoteCallInitiated.newCase(this, aConnections.toString(), objectName, method, args);

		Object aResult =  call (aConnections, objectName, method, args);
		RemoteCallFinished.newCase(this, aConnections, objectName, method, args, aResult);
		return aResult;

//		return call(getConnections(), objectName, method, args);
	}
	@Override
	public Object callAllAndMe(Class type, Method method, Object[] args) {
		// TODO Auto-generated method stub
		return callAllAndMe(type.getName(), method, args);
	}
	@Override
	public Object callAll(Method method, Object[] args) {
		return call(getConnections(), method, args);
	}

	@Override
	public Object callOthers(Method method, Object[] args) {
		if (getSender() == null) return null;
		Set<String> clientNames = getConnections();
		clientNames.remove(getSender());
		call(clientNames, method, args);
		return null;
	}
	

	@Override
	public Object callAll(String objectName, Method method,
			Object[] args) {
		Set<String> aConnections = getConnections();
//		aConnections.add(getLocalName());
//		RemoteCallInitiated.newCase(this, aConnections, objectName, method, args);
		Object retVal = call (aConnections, objectName, method, args);
		RemoteCallFinished.newCase(this, aConnections, objectName, method, args, retVal);

		return retVal;
//		return call(getConnections(), objectName, method, args);
	}
	// behavior depends on whether it is a buffer or object port, can this be object?
//	String chooseLastSender() {
//		String retVal = getLastSenderOfNonReplyMessage();
//		if (retVal == null)
//			retVal = getLastSender();
//		return retVal;
//	}
	@Override
	public Object callOthers(String objectName, Method method,
			Object[] args) {
		String lastSender = getSender();
		if (lastSender == null) {
			Tracer.error("Others call made before there is a message received");
			return null;
		}
		Set<String> clientNames = getConnections();
		Set<String> copyClientNames = new HashSet(clientNames);
		copyClientNames.remove(lastSender);
		return call (copyClientNames, objectName, method, args);
//		clientNames.remove(lastSender);
//		return call(clientNames, objectName, method, args);
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
		objectGroupServerInputPort.sendAll(message);
		
	}
	@Override
	public void sendOthers(Object message) {
		objectGroupServerInputPort.sendOthers(message);
		
	}
	@Override
	public void send(Collection<String> clientNames, Object message) {
		objectGroupServerInputPort.send(clientNames, message);
	}
	
	@Override
	public GroupSendTrapper<Object, Object> getGroupSendTrapper() {
		return groupSerializableCallSendTrapper;
	}
	@Override
	public GroupToUniSendTrapper<Object, Object> getGroupToUniSendTrapper() {
		return objectGroupServerInputPort.getGroupToUniSendTrapper();
	}
	@Override
	public void setGroupSendTrapper(
			GroupSendTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(groupSerializableCallSendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == groupSerializableCallSendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, groupSerializableCallSendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
		
//		
//		if (newVal.getDestination() == null)
////			newVal.setDestination(groupSerializableCallSendTrapper);
//			newVal.setDestination(objectGroupServerInputPort);

		
		groupSerializableCallSendTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, groupSerializableCallSendTrapper, true));
//		DistEventsBus.newEvent(new AConnectionEvent(groupSerializableCallSendTrapper, groupSerializableCallSendTrapper.getDestination(), true));

	}
	@Override
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<Object, Object> groupToUniSendTrapper) {
		objectGroupServerInputPort.setGroupToUniSendTrapper(groupToUniSendTrapper);
	}
//	static {
//		GroupTrapperFactory<Object, Object> factory = new AGroupSerializableCallTrapperFactory();
//		GlobalState.getGroupSerializableCallTrapper().
//			setGroupSendTrapperFactory(factory);	
//	}
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort("9090", "test server");
//
//		serverInputPort.connect();
//		PrintingReplyingObjectReceiver messageReceiver = new PrintingReplyingObjectReceiver(serverInputPort);
//		serverInputPort.addConnectionListener(messageReceiver);
//
//		Adder adder = new AnAdder();
//		serverInputPort.register(Adder.class, adder);
//		AGroupAdder groupAdder = new AGroupAdder(serverInputPort);
//		serverInputPort.register(GroupAdder.class, groupAdder);
//		serverInputPort.addSendListener(messageReceiver);
//	}

	

	
}
