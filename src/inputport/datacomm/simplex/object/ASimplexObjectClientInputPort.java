package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;
import java.util.Set;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import util.trace.port.objects.ObjectSendInitiated;



public class ASimplexObjectClientInputPort implements SimplexClientInputPort<Object>{

	SimplexClientInputPort<ByteBuffer> bbClientInputPort;
	protected SendTrapper<Object, ByteBuffer> serializer;
	protected SendTrapper<Object, Object> sendObjectForwarder;
	public ASimplexObjectClientInputPort(SimplexClientInputPort<ByteBuffer> aBBClientInputPort) {
		bbClientInputPort = aBBClientInputPort;	
		setupSendTrapperChain();
//		serializer = GlobalState.getObjectTranslatingIPTrapperSelector().createSendTrapper(this, bbClientInputPort);
//		sendObjectForwarder = GlobalState.getClientObjectForwardingIPTrapperSelector().createSendTrapper(this, serializer);
	}	
	void setupSendTrapperChain() {
		//serialize input and send  serialized object to byte buffer port
//		serializer = GlobalState.getObjectTranslatingIPTrapperSelector().createSendTrapper(this, bbClientInputPort);
		serializer = ObjectTranslatingIPTrapperSelector.getTrapperSelector().createSendTrapper(this, bbClientInputPort);

		//forward input object to serializer
//		sendObjectForwarder = ObjectForwardingIPTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer);		
		setSendTrapper (ClientObjectTrapperSelector.getTrapperSelector().createSendTrapper(this, serializer));		

//		sendObjectForwarder = GlobalState.getClientObjectForwardingIPTrapperSelector().createSendTrapper(this, serializer);		
	}
	//send application object to forwarder
	@Override
	public void send(String remoteName, Object message) {
		ObjectSendInitiated.newCase(this, this.getLocalName(), remoteName, message);
		sendObjectForwarder.send(remoteName, message);		
	}
//	@Override
//	public void send(String remoteName, Object message) {
//		if (message instanceof  ByteBuffer) {
//			Tracer.error("Cannot send byte buffer on object port");
//		} else if (message instanceof Serializable) {	
//			sendObjectForwarder.send(remoteName, message);
//		} else {
//			Tracer.error("Sent message is not serializable");
//		}		
//	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		bbClientInputPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		bbClientInputPort.addConnectionListener(portConnectListener);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		bbClientInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		bbClientInputPort.removeConnectionListener(portConnectListener);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		bbClientInputPort.notifyDisconnect(remoteEnd, eof,
				closeReason, null);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		bbClientInputPort.notifyPortSend(null, message, sendId);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		bbClientInputPort.addSendListener(portSendListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		bbClientInputPort.removeSendListener(portSendListener);
	}
	@Override
	public void connect() {
		bbClientInputPort.connect();
	}
	@Override
	public void disconnect() {
		bbClientInputPort.disconnect();
	}


	@Override
	public void send(Object message) {
		send (getLogicalRemoteEndPoint(), message);
	}

	@Override
	public String getLogicalRemoteEndPoint() {
		return bbClientInputPort.getLogicalRemoteEndPoint();
	}
	
	@Override
	public String getLocalName() {
		return bbClientInputPort.getLocalName();
	}



	@Override
	public SendTrapper<Object, Object> getSendTrapper() {
		return sendObjectForwarder;
	}

	@Override
	public void setSendTrapper(SendTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(sendObjectForwarder.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == sendObjectForwarder) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, sendObjectForwarder, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
		
		sendObjectForwarder = newVal;
//		DistEventsBus.newEvent(new AConnectionEvent(this, sendObjectForwarder, true));
	}
	
	

	@Override
	public Set<String> getConnections() {
		return bbClientInputPort.getConnections();
	}
	
//	static {
//		GlobalState.getObjectTranslatingIPTrapperSelector().
//			setSendTrapperFactory(new ASerializingForwarderFactory());
//		ObjectTranslatingIPTrapperSelector.getTrapperSelector().setSendTrapperFactory(new ASerializingForwarderFactory());
//		
//	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return bbClientInputPort.getPhysicalRemoteEndPoint();

	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		bbClientInputPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		bbClientInputPort.setLogicalRemoteEndPoint(newVal);

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return bbClientInputPort.isConnected(remoteName);
	}

}
