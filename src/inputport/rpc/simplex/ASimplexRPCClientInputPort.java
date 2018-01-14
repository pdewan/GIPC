package inputport.rpc.simplex;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.rpc.Marshaller;
import inputport.rpc.MarshallerSelector;
import inputport.rpc.RPCProxyGenerator;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Set;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;





public class ASimplexRPCClientInputPort implements SimplexRPCClientInputPort {

	protected SimplexClientInputPort<Object> objectClientInputPort;
	protected SendTrapper<Object, Object> serializableCallSendTrapper;
	protected Marshaller marshaller;
	protected RPCProxyGenerator rpcProxyGenerator;
	public ASimplexRPCClientInputPort(SimplexClientInputPort<Object> anObjectClientInputPort) {
		
		objectClientInputPort = anObjectClientInputPort;	
		initMarshaller();

//		marshaller = MarshallerSelector.createMarshaller();
		initRPCSupport();
//		serializableCallSendTrapper = GlobalState.getSimplexSerializableCallTrapper().createSendTrapper(this, anObjectClientInputPort);
//		serializableCallSendTrapper = SimplexSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, anObjectClientInputPort);
//		setSendTrapper(serializableCallSendTrapper);
//		marshaller = MarshallerSelector.createMarshaller();
//		rpcProxyGenerator = new ASimplexRPCProxyGenerator(this);
	}	
	protected void initMarshaller() {
		marshaller = MarshallerSelector.createMarshaller();

	}
	protected void initRPCSupport() {
		serializableCallSendTrapper = SimplexSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, objectClientInputPort);
		setSendTrapper(serializableCallSendTrapper);
		marshaller = MarshallerSelector.createMarshaller();
		rpcProxyGenerator = new ASimplexRPCProxyGenerator(this);
	}

	protected Object marshallCall(String objectName, Method method, Object[] args) {
		return marshaller.marshallCall(objectName, method, args);
	}	
	public synchronized Object call(String objectName, Method method, Object[] args) {			
		Object serializableCall = marshallCall(objectName, method, args);
		Tracer.info(this, " Sending serializable call:" + serializableCall);
		// message is sent to all, so end pt does not matter
//		serializableCallSendTrapper.send(objectName, serializableCall);

		serializableCallSendTrapper.send(getLogicalRemoteEndPoint(), serializableCall);
		return serializableCallSendTrapper.returnValue(getLogicalRemoteEndPoint(), serializableCall);
		// what happens if the server crashes before return value gotten
//		return serializableCallSendTrapper.returnValue(getPhysicalRemoteEndPoint(), serializableCall);

	}
//	@Override
//	public Object call(Method method, Object[] args) {
//		return call (method.getDeclaringClass().getName(), method, args);
//	}
	
	@Override
	public Object call(Method method, Object[] args) {
		return call (defaultNameFromMethod(method), method, args);
	}
	
	public static String defaultNameFromClass (Class type) {
		return type.getName();
		
	}
	public static String defaultNameFromMethod (Method method) {
		return method.getDeclaringClass().getName();
		
	}
//	@Override
//	public Object call(Class type, Method method, Object[] args) {
//		return call (type.getName(), method, args );
//	}
	
	@Override
	public Object call(Class type, Method method, Object[] args) {
		return call (defaultNameFromClass(type), method, args );
	}

	@Override
	public Object call(String clientName, String objectName,
			Method method, Object[] args) {
		return call(objectName, method, args);
	}


	@Override
	public Object call(String clientName, Class type,
			Method method, Object[] args) {
		return call(type, method, args);
	}


	public void addConnectionListener(ConnectionListener portConnectListener) {
		objectClientInputPort.addConnectionListener(portConnectListener);
	}



	public void addSendListener(ByteBufferSendListener portSendListener) {
		objectClientInputPort.addSendListener(portSendListener);
	}


	public void connect() {
		objectClientInputPort.connect();
	}


	public void disconnect() {
		objectClientInputPort.disconnect();
	}


	public String getLogicalRemoteEndPoint() {
		return objectClientInputPort.getLogicalRemoteEndPoint();
	}





	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		objectClientInputPort.notifyConnect(remoteEnd, aConnectionType);
	}


	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		objectClientInputPort.notifyConnectFailure(remoteEnd, message, null);
	}


	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		objectClientInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}


	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		objectClientInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}


	public void removeConnectionListener(ConnectionListener portConnectListener) {
		objectClientInputPort.removeConnectionListener(portConnectListener);
	}





	public void removeSendListener(ByteBufferSendListener portSendListener) {
		objectClientInputPort.removeSendListener(portSendListener);
	}


	public void send(Object message) {
		objectClientInputPort.send(message);
	}


	public void send(String remoteName, Object message) {
		objectClientInputPort.send(remoteName, message);
	}
	@Override
	public String getLocalName() {
		return objectClientInputPort.getLocalName();
	}

	@Override
	public SendTrapper<Object, Object> getSendTrapper() {
		return serializableCallSendTrapper;
	}

	@Override
	public void setSendTrapper(SendTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(serializableCallSendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == serializableCallSendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, serializableCallSendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
		serializableCallSendTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, serializableCallSendTrapper, true));
		
	}

	@Override
	public Set<String> getConnections() {
		return objectClientInputPort.getConnections();
	}


	@Override
	public InputPort getDataInputPort() {
		return objectClientInputPort;
	}


	@Override
	public void setDataInputPort(InputPort newVal) {
		objectClientInputPort = (SimplexClientInputPort<Object>) newVal;
		
	}



	@Override
	public Marshaller getMarshaller() {
		return marshaller;
	}


	@Override
	public void setMarshaller(Marshaller newVal) {
		marshaller = newVal;
		
	}


	@Override
	public RPCProxyGenerator getRPCProxyGenerator() {
		return rpcProxyGenerator;
	}


	@Override
	public void setRPCProxyGenerator(RPCProxyGenerator newVal) {
		rpcProxyGenerator = newVal;
		
	}


	@Override
	public String getPhysicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return objectClientInputPort.getPhysicalRemoteEndPoint();

	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		objectClientInputPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		objectClientInputPort.setLogicalRemoteEndPoint(newVal);

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return objectClientInputPort.isConnected(remoteName);
	}




}
