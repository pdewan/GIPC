package inputport.rpc.duplex;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.rpc.RPCRegistry;
import inputport.rpc.RPCRegistrySelector;
import inputport.rpc.ReceivedCallInvoker;
import inputport.rpc.simplex.ASimplexRPCClientInputPort;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;





public class ADuplexRPCClientInputPort extends ASimplexRPCClientInputPort implements DuplexRPCClientInputPort, ReceiveListener<Object> {
//	String lastSenderOfNonReplyMessage;
	ReceivedCallInvoker serializableCallReceiver;
	protected RPCRegistry rpcRegistry ;
//	DuplexClientInputPort<Object> objectDuplexClientInputPort;
	protected LocalRemoteReferenceTranslator remoteHandler;
	protected ReceiveRegistrarAndNotifier<Object> receiveReceiptRegistrarAndNotifier;
	//= new AReceiveRegistrarAndNotifier<Object>();
	
	protected ReceiveTrapper<Object, Object> serializableCallReceiveTrapper;

	public ADuplexRPCClientInputPort(DuplexClientInputPort<Object> anObjectClientInputPort) {
		super(anObjectClientInputPort);
//		rpcRegistry = RPCRegistrySelector.createRPCRegistry();
////		objectDuplexClientInputPort = anObjectClientInputPort;
//		anObjectClientInputPort.addReceiveListener(this);		
//		remoteHandler = LocalRemoteReferenceTranslatorSelector.createLocalRemoteReferenceTranslator(this);
//
//		serializableCallSendTrapper = DuplexSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, anObjectClientInputPort);
//		serializableCallReceiveTrapper = DuplexSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
//		
////		serializableCallSendTrapper = GlobalState.getDuplexSerializableCallTrapper().createSendTrapper(this, anObjectClientInputPort);
////		serializableCallReceiveTrapper = GlobalState.getDuplexSerializableCallTrapper().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
//		
//		rpcProxyGenerator = new ADuplexRPCProxyGenerator(this, remoteHandler);
	}
	protected void initRPCSupport() {
		rpcRegistry = RPCRegistrySelector.createRPCRegistry(this);
//		objectDuplexClientInputPort = anObjectClientInputPort;
		objectDuplexClientInputPort().addReceiveListener(this);		
		remoteHandler = LocalRemoteReferenceTranslatorSelector.createLocalRemoteReferenceTranslator(this);
		receiveReceiptRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<Object>();
//		serializableCallSendTrapper = DuplexClientSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, objectClientInputPort);
		serializableCallSendTrapper = getSerializableCallSendTrapper();

		setSendTrapper(serializableCallSendTrapper);
//		serializableCallReceiveTrapper = DuplexClientSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
		serializableCallReceiveTrapper = getSerializableCallReceiveTrapper();

		setReceiveTrapper(serializableCallReceiveTrapper);
//		serializableCallSendTrapper = GlobalState.getDuplexSerializableCallTrapper().createSendTrapper(this, anObjectClientInputPort);
//		serializableCallReceiveTrapper = GlobalState.getDuplexSerializableCallTrapper().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
		
		rpcProxyGenerator = new ADuplexRPCProxyGenerator(this, remoteHandler);
		
	}
	protected ReceiveTrapper<Object, Object> getSerializableCallReceiveTrapper () {
		return DuplexClientSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
	}
	protected SendTrapper<Object, Object> getSerializableCallSendTrapper () {
		return DuplexClientSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, objectClientInputPort);
	}

	protected DuplexClientInputPort objectDuplexClientInputPort() {
		return (DuplexClientInputPort) objectClientInputPort;
	}
	
	protected LocalRemoteReferenceTranslator createRemoteHandler(DuplexRPCInputPort aDuplexRPCPort) {
		return LocalRemoteReferenceTranslatorSelector.createLocalRemoteReferenceTranslator(aDuplexRPCPort);
	}
	@Override
	public LocalRemoteReferenceTranslator getLocalRemoteReferenceTranslator() {
		return remoteHandler;
	}
	



	@Override
	public void register(Class type, Object server) {
		rpcRegistry.register(type, server);
		
	}

	@Override
	public Object getServerObject(String name) {
		return rpcRegistry.getServerObject(name);
	}




	
	@Override
	public void register(String name, Object server) {
		rpcRegistry.register(name, server);
		
	}

	@Override
	public void register(Object server) {
		rpcRegistry.register(server);
		
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
		return call (type, method, args);
	}


	@Override
	public Object call(String clientName, String objectName,
			Method method, Object[] args) {
		return call(objectName, method, args);
	}


	@Override
	public Object call(String clientName, Class interfaceName,
			Method method, Object[] args) {
		return call(interfaceName, method, args);
	}

	@Override
	public void reply(String aRemoteEnd, Object aMessage) {
		objectDuplexClientInputPort().reply(aRemoteEnd, aMessage);
	}

//	@Override
//	public void messageReceived(String aRemoteEnd, Object aMessage) {
//		notifyPortReceive(aRemoteEnd, aMessage);
//	}

	@Override
	public void messageReceived(String aRemoteEnd, Object aMessage) {
		getReceiveTrapper().notifyPortReceive(aRemoteEnd, aMessage);
	}


	@Override
	public void reply(Object message) {
		objectDuplexClientInputPort().reply(message);
	}


	@Override
	public void addReceiveListener(ReceiveListener<Object> portReceiveListener) {
		receiveReceiptRegistrarAndNotifier.addReceiveListener(portReceiveListener);
		
	}

	@Override
	public void removeReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receiveReceiptRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
		
	}


	@Override
	public void notifyPortReceive(String aRemoteEnd, Object aMessage) {
		receiveReceiptRegistrarAndNotifier.notifyPortReceive(aRemoteEnd, aMessage);
//		getReceiveTrapper().notifyPortReceive(aRemoteEnd, aMessage);
		
	}

	@Override
	public ReceiveTrapper<Object, Object> getReceiveTrapper() {
		return serializableCallReceiveTrapper;
	}

	@Override
	public void setReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null)
			newVal.setDestination(serializableCallReceiveTrapper.getDestination());
		else if (newVal.getDestination() == serializableCallReceiveTrapper) { // adding  in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, serializableCallReceiveTrapper, newVal, false, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));

		}
		serializableCallReceiveTrapper = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, serializableCallReceiveTrapper, false));
	}
	@Override
	public Set<String> getConnections() {
		return objectDuplexClientInputPort().getConnections();
	}
	@Override
	public String getSender() {
		return objectDuplexClientInputPort().getSender();
	}
	@Override
	public void setSender(String newVal) {
		objectDuplexClientInputPort().setSender(newVal);
	}

	@Override
	public DuplexInputPort<Object> getDuplexInputPort() {
		return objectDuplexClientInputPort();
	}
	@Override
	public void setDuplexInputPort(DuplexInputPort<Object> newVal) {
		System.out.println("This is strange, why is the duplex input port this being chnaged dynamically");
		objectClientInputPort = (DuplexClientInputPort<Object>) newVal;
	}
//	@Override
//	public String getLastSenderOfNonReplyMessage() {
//		return lastSenderOfNonReplyMessage;
//	}
//
//	@Override
//	public void setLastSenderOfNonReplyMesasage(String newVal) {
//		lastSenderOfNonReplyMessage = newVal;
//	}


	@Override
	public List<ReceiveListener<Object>> getReceiveListeners() {
		return receiveReceiptRegistrarAndNotifier.getReceiveListeners();
	}


	@Override
	public Set<String> registeredMethodNames() {
		return rpcRegistry.registeredMethodNames();
	}
	public ReceiveReturnMessage<Object> receive() {
		return objectDuplexClientInputPort().receive();		

	}
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		return objectDuplexClientInputPort().receive(aSource);
	}


}
