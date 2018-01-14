package inputport.rpc.duplex;

import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.simplex.ASimplexRPCServerInputPort;

import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.Set;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import util.trace.port.rpc.RemoteCallInitiated;



public  class ADuplexRPCServerInputPort extends ASimplexRPCServerInputPort implements DuplexRPCServerInputPort {

	String lastSenderOfNonReplyMessage;
	DuplexServerInputPort<Object> objectDuplexServerInputPort;
	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	protected DuplexSentCallCompleter uniNamingFunctionHandler;
	protected SendTrapper<Object, Object> callSendTrapper;
	protected RPCProxyGenerator rpcProxyGenerator;

//	Marshaller marshaller;
	public ADuplexRPCServerInputPort(DuplexServerInputPort<Object> aTypedServerInputPort) {
		super(aTypedServerInputPort);
		objectDuplexServerInputPort = aTypedServerInputPort;

		initSendTrapper();
		rpcProxyGenerator = new ADuplexRPCProxyGenerator(this, localRemoteReferenceTranslator);

	}
	@Override
	protected void initReceiveTrapper() {
		localRemoteReferenceTranslator = createLocalRemoteReferenceTranslator(this);
		serializableCallReceiveTrapper = DuplexServerSerializableCallTrapperSelector.getTrapperSelector().				
				createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
		setReceiveTrapper(serializableCallReceiveTrapper);
	}
	
	protected void initSendTrapper() {
//		callSendTrapper =  DuplexSerializableCallTrapperSelector.getTrapperSelector().
//				createSendTrapper(this, objectDuplexServerInputPort);
//		setSendTrapper(callSendTrapper);
		setSendTrapper(createSendTrapper());
	}
	
	protected SendTrapper<Object, Object> createSendTrapper() {
		return DuplexServerSerializableCallTrapperSelector.getTrapperSelector().
				createSendTrapper(this, objectDuplexServerInputPort);
//		setSendTrapper(callSendTrapper);
	}

	protected LocalRemoteReferenceTranslator createLocalRemoteReferenceTranslator(DuplexRPCInputPort aDuplexRPCPort) {
		return LocalRemoteReferenceTranslatorSelector.createLocalRemoteReferenceTranslator(aDuplexRPCPort);

	}
//	protected DuplexSentCallCompleter createReturningUniNamingRPCFunctionHandler(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new ADuplexSentCallCompleter(this);
//	}


	protected Object marshallCall (String objectName, Method method,
			Object[] args) {
		return marshaller.marshallCall(objectName, method, args);
	}
	// this is relevant even it  is subclassed and it goes through callSendTrapper
	@Override
	public Object call(String destination, String objectName, Method method,
			Object[] args) {
		Tracer.info(this, "Sending call :" + destination + " Method:" + method);

		Object serializableCall = marshallCall(objectName, method, args);
		callSendTrapper.send(destination, serializableCall);
		return callSendTrapper.returnValue(destination, serializableCall);
	}


//	@Override
//	public Object call(String name, Method method, Object[] args) {
//		if (getLastSender() == null) return null;
//		return call(getLastSender(), name, method, args);
//	}
	
	@Override
	public Object call(String destination, Method method, Object[] args) {
		return call(destination, method.getDeclaringClass(), method, args);
	}
	
	
	
	@Override
	public Object call(String clientName, Class type,
			Method method, Object[] args) {
		return call (clientName, type.getName(), method, args);
	}
	@Override
	public Object call(Class type, Method method, Object[] args) {
		if (getSender() == null) return null;
		return call (getSender(), type, method, args);
	}
	@Override
	public Object call(Method method, Object[] args) {
		if (getSender() == null) return null;		
		return call(getSender(), method, args);
	}

	
	@Override
	public Object reply(String name, Method method, Object[] args) {
		return call(name, method, args);
	}
	@Override
	public Object reply(Method method, Object[] args) {
		return call(method, args);
	}
	@Override
	public Object reply(Class type, Method method, Object[] args) {
		return call(type, method, args);
	}

@Override
public void send(Object message) {
	objectDuplexServerInputPort.send(message);	
}
@Override
public void send(String remoteName, Object message) {
	objectDuplexServerInputPort.send(remoteName, message);	
}
@Override
public void reply(String aRemoteEnd, Object aMessage) {
	send(aRemoteEnd, aMessage);
}

@Override
public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
	objectDuplexServerInputPort.notifyPortSend(aRemoteEnd, message, sendId);
}
@Override
public void addSendListener(ByteBufferSendListener portSendListener) {
	objectDuplexServerInputPort.addSendListener(portSendListener);	
}
@Override
public void removeSendListener(ByteBufferSendListener portSendListener) {
	objectDuplexServerInputPort.removeSendListener(portSendListener);
}
@Override
public String getSender() {
	return lastSenderOfNonReplyMessage;
	// TODO Auto-generated method stub
//	return objectDuplexServerInputPort.getLastSender();
}
@Override
public Set<String> getConnections() {
	return objectDuplexServerInputPort.getConnections();
}
@Override
public void setSender(String newVal) {
	lastSenderOfNonReplyMessage = newVal;
//	objectDuplexServerInputPort.setLastSender(newVal);	
}


@Override
public void reply(Object message) {
	objectDuplexServerInputPort.reply(message);	
}

@Override
public SendTrapper<Object, Object> getSendTrapper() {
	return callSendTrapper;
}


@Override
public void setSendTrapper(SendTrapper<Object, Object> newVal) {
	if (newVal.getDestination() == null) {
		newVal.setDestination(callSendTrapper.getDestination());
		Tracer.warning("send trapper == null!");
	} else if (newVal.getDestination() == callSendTrapper) { // adding a new one in front of old one
		ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, callSendTrapper, newVal, true, false));

	} else {
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
	}
	
	
//	if (newVal.getDestination() == null) {
//		newVal.setDestination(callSendTrapper.getDestination());
//	}
	callSendTrapper = newVal;
//	DistEventsBus.newEvent(new AConnectionEvent(this, callSendTrapper, true));
}
@Override
public LocalRemoteReferenceTranslator getLocalRemoteReferenceTranslator() {
	return localRemoteReferenceTranslator;
}
@Override
public DuplexInputPort<Object> getDuplexInputPort() {
	return objectDuplexServerInputPort;
}
@Override
public void setDuplexInputPort(DuplexInputPort<Object> newVal) {
	objectDuplexServerInputPort = (DuplexServerInputPort<Object>) newVal;
}

@Override
public RPCProxyGenerator getRPCProxyGenerator() {
	return rpcProxyGenerator;
}

@Override
public void setRPCProxyGenerator(RPCProxyGenerator newVal) {
	rpcProxyGenerator = newVal;
}

public ReceiveReturnMessage<Object> receive() {
	return objectDuplexServerInputPort.receive();		

}
@Override
public ReceiveReturnMessage<Object> receive(String aSource) {
	return objectDuplexServerInputPort.receive(aSource);
}

//@Override
//public String getLastSenderOfNonReplyMessage() {
//	return lastSenderOfNonReplyMessage;
//}
//
//@Override
//public void setLastSenderOfNonReplyMesasage(String newVal) {
//	lastSenderOfNonReplyMessage = newVal;
//}





	
}
