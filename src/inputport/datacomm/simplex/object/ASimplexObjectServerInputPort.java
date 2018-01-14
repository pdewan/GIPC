package inputport.datacomm.simplex.object;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.simplex.SimplexServerInputPort;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;


public class ASimplexObjectServerInputPort implements SimplexServerInputPort<Object>, ReceiveListener<ByteBuffer>{
	SimplexServerInputPort<ByteBuffer> bbServerInputPort;
//	ReceiveRegistrarAndNotifier<Object> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier<Object>();
	ReceiveRegistrarAndNotifier<Object> receiveRegistrarAndNotifier = createReceiveRegistrarAndNotifier();

	ReceiveTrapper<ByteBuffer, Object> deserializer;
	ReceiveTrapper<Object, Object> deserializedObjectForwarder;


	public ASimplexObjectServerInputPort(SimplexServerInputPort<ByteBuffer> aBBServerInputPort) {
		bbServerInputPort = aBBServerInputPort;
		setupReceiveTrapperChain();
//		bbServerInputPort.addReceiveListener(this);
//		// forwarded object goes through notifier to object port receivers
//		deserializedObjectForwarder = GlobalState.getClientObjectForwardingIPTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
//		// deserialized object goes to object forwarder
//		deserializer = GlobalState.getObjectTranslatingIPTrapperSelector().createReceiveTrapper(this, deserializedObjectForwarder);
//		
	}
	protected ReceiveRegistrarAndNotifier<Object> createReceiveRegistrarAndNotifier() {
		return new AReceiveRegistrarAndNotifier<Object>();
	}
	void setupReceiveTrapperChain() {
		bbServerInputPort.addReceiveListener(this);
		// send deserialized object through notifier to object port receivers
//		deserializedObjectForwarder = GlobalState.getClientObjectForwardingIPTrapperSelector().
//		deserializedObjectForwarder = ClientObjectForwardingIPTrapperSelector.getTrapperSelector().
//				createReceiveTrapper(this, receiveRegistrarAndNotifier);
		deserializedObjectForwarder = ServerObjectTrapperSelector.getTrapperSelector().
				createReceiveTrapper(this, receiveRegistrarAndNotifier);
		// deserialize bytebuffer and send deserialized object to forwarder
//		deserializer = GlobalState.getObjectTranslatingIPTrapperSelector().
		deserializer = ObjectTranslatingIPTrapperSelector.getTrapperSelector().
				createReceiveTrapper(this, deserializedObjectForwarder);
		setReceiveTrapper(deserializedObjectForwarder);
	}
	// send byte buffer message to deserializer
	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message) {
		deserializer.notifyPortReceive(remoteClientName, message);
	}
//	BufferSerializationSupport createBufferSerializationSupport(){
//		return  BufferSerializationSupportPoolSelector.createBufferSerializationSupportPool(null);
//
//	}
	public void connect() {
		bbServerInputPort.connect();
	}
	public void disconnect() {
		bbServerInputPort.disconnect();
	}
	@Override
	public String getLocalName() {
		return bbServerInputPort.getLocalName();
	}
	public void notifyPortReceive(String remoteEnd, Object message) {
		deserializedObjectForwarder.notifyPortReceive(remoteEnd,
				message);

	}
	public void addReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receiveRegistrarAndNotifier
				.addReceiveListener(portReceiveListener);
	}
	public void removeReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receiveRegistrarAndNotifier
				.removeReceiveListener(portReceiveListener);
	}
	
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		bbServerInputPort.notifyConnect(remoteEnd, aConnectionType);
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		bbServerInputPort.addConnectionListener(portConnectListener);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		bbServerInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		bbServerInputPort.removeConnectionListener(portConnectListener);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		bbServerInputPort.notifyDisconnect(remoteEnd, eof,
				closeReason, null);
	}
//	public void addDisconnectListener(DisconnectListener portCloseListener) {
//		bbServerInputPort.addDisconnectListener(portCloseListener);
//	}
//	public void removeDisconnectListener(DisconnectListener portCloseListener) {
//		bbServerInputPort
//				.removeDisconnectListener(portCloseListener);
//	}
	
//	@Override
	public SimplexServerInputPort<ByteBuffer> getUntypedServerInputPort() {
		return bbServerInputPort;
	}	
	
//	@Override
	public ReceiveTrapper<Object, Object> getReceiveTrapper() {
		return deserializedObjectForwarder;
	}
//	@Override
	public void setReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		
		deserializedObjectForwarder = newVal;
		if (deserializer.getDestination() == null) {
			deserializer.setDestination(deserializedObjectForwarder);
			Tracer.error("deserializer destination is null!");
		}
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, deserializer, false));
//		deserializer.setDestination(deserializedObjectForwarder);
		
	}
	static {
//		GlobalState.getObjectTranslatingIPTrapperSelector().
//			setReceiveTrapperFactory(new ADeserializingForwarderFactory());
		
	}
	@Override
	public String getServerId() {
		return bbServerInputPort.getServerId();
	}
//	
//	public static void main (String[] args) {
//		Tracer.showInfo(true);
////		TypedGroupServerInputPort serverInputPort = new AnInheritingTypedGroupServerInputPort("9090", "test server");
//		SimplexServerInputPort<Object> serverInputPort = ObjectSimplexInputPortSelector.createServerSimplexInputPort("9090", "test server");
//		serverInputPort.connect();
//		PrintingTypedReceiveListener printingReplyngTypedReceiver = new PrintingTypedReceiveListener(serverInputPort);
//		serverInputPort.addConnectionListener(printingReplyngTypedReceiver);
////		serverInputPort.addDisconnectListener(printingReplyngTypedReceiver);
//		serverInputPort.addReceiveListener(printingReplyngTypedReceiver);	
////		serverInputPort.addSendListener(printingReplyngTypedReceiver);
//	}
	@Override
	public Set<String> getConnections() {
		return bbServerInputPort.getConnections();
	}
	@Override
	public String getSender() {
		return bbServerInputPort.getSender();
	}
	@Override
	public void setSender(String newVal) {
		bbServerInputPort.setSender(newVal);
		
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return bbServerInputPort.isConnected(remoteName);
	}
	@Override
	public List<ReceiveListener<Object>> getReceiveListeners() {
		return receiveRegistrarAndNotifier.getReceiveListeners();
	}
	public String getLogicalRemoteEndPoint() {
		return bbServerInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return bbServerInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		bbServerInputPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		bbServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}
}
