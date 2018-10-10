package oldtypedip;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Set;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.DisconnectListener;
import inputport.datacomm.simplex.buffer.ASendRegistrarAndNotifier;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;
import port.old.AConnectionSendReceiptNotifier;
import port.old.ByteBufferReceiveListener;
import port.old.MonolithicDuplexClientInputPort;
import port.old.MonolithicReceiptRegistrarAndNotifier;
import serialization.Serializer;
import serialization.SerializerPoolSelector;

public class AnInheritingTypedClientInputPort extends AConnectionSendReceiptNotifier implements TypedClientInputPort, ByteBufferReceiveListener, ByteBufferSendListener{	
//	ConnectRegistrarAndNotifier connectNotifier = new AConnectRegistrarAndNotifier();
	MonolithicReceiptRegistrarAndNotifier receiptNotifier;
	TypedReceiptNotifier typedReceiptNotifier = new ATypedReceiptNotifier();
	SendRegistrarAndNotifier sendNotifiier = new ASendRegistrarAndNotifier();
	MonolithicDuplexClientInputPort delegateDuplexClientInputPort;
//	BufferSerializationSupport bufferSerializationSupport = new ABufferSerializationSupport();
//	BufferSerializationSupport inputBufferSerializationSupport;
//	NonCopyingBufferSerializationSupportPool bufferSerializationSupportPool;
	Serializer bufferSerializationSupportPool;
	
	public AnInheritingTypedClientInputPort(String theHost, String theServerId, String theClientName) {
//		bufferSerializationSupport =  BufferSerializationSupportSelector.createBufferSerializationSupport();
//		delegateDuplexClientInputPort = MonolithicD.createDuplexClientInputPort(theHost, theServerId, theClientName);	
		delegateDuplexClientInputPort.addReceiveListener(this); // to receive messages from delegate
		delegateDuplexClientInputPort.addSendListener(this); // to receive messages from delegate
//		bufferSerializationSupportPool = new ABufferSerialiationSupportPool(this);
		bufferSerializationSupportPool = SerializerPoolSelector.createSerializerPool(this);
	}
	@Override
	public void addReceiveListener(ByteBufferReceiveListener portReceiveListener) {
		receiptNotifier.addReceiveListener(portReceiveListener); // so that typed messages are not sent to the listener
		//delegateDuplexClientInputPort.addReceiveListener(this); // so that it can be forwarded to typed listeners
		//delegateDuplexClientInputPort.addReceiveListener(portReceiveListener);
	}

	@Override
	public void notifyPortReceive(String remoteEnd, ByteBuffer message, int length) {
		// this should not be called
		receiptNotifier.notifyPortReceive(remoteEnd, message, length);
		//delegateDuplexClientInputPort.notifyPortReceive(remoteEnd, message);
	}

	@Override
	public void removeReceiveListener(ByteBufferReceiveListener portReceiveListener) {
		receiptNotifier.removeReceiveListener(portReceiveListener);
		//delegateDuplexClientInputPort.removeReceiveListener(portReceiveListener);
	}


//	public void addConnectionListener(ConnectionListener portCloseListener) {
//		delegateDuplexClientInputPort.addConnectionListener(portCloseListener);
//	}

	public void addTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		typedReceiptNotifier.addTypedReceiveListener(portReceiveListener);
	}
	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		typedReceiptNotifier.notifyTypedPortReceive(remoteEnd, message);
	}
	public void removeTypedReceiveListener(
			TypedReceiveListener portReceiveListener) {
		typedReceiptNotifier.removeTypedReceiveListener(portReceiveListener);
	}
	@Override
	public void addConnectionListener(ConnectionListener portConnectListener) {
		delegateDuplexClientInputPort.addConnectionListener(portConnectListener);
	}

	@Override
	public void addDisconnectListener(DisconnectListener portCloseListener) {
		delegateDuplexClientInputPort.addDisconnectListener(portCloseListener);
	}

	public void connect() {
		delegateDuplexClientInputPort.connect();
	}

	public void disconnect() {
		delegateDuplexClientInputPort.disconnect();
	}

	@Override
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		assert false;
		//delegateDuplexClientInputPort.notifyConnect(remoteEnd);
	}

	@Override
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		assert false;
		//delegateDuplexClientInputPort.notifyDisconnect(remoteEnd, eof, closeReason);
	}
//
//	public void removeConnectionListener(ConnectionListener portCloseListener) {
//		delegateDuplexClientInputPort.removeConnectionListener(portCloseListener);
//	}

	@Override
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		delegateDuplexClientInputPort.removeConnectionListener(portConnectListener);
	}

	@Override
	public void removeDisconnectListener(DisconnectListener portCloseListener) {
		delegateDuplexClientInputPort.removeDisconnectListener(portCloseListener);
	}

	public void send(ByteBuffer message) {
		delegateDuplexClientInputPort.send(message);
	}
//	@Override
//	public void reply(ByteBuffer message) {
//		send(message);
//	}
	

@Override
public void addSendListener(ByteBufferSendListener portSendListener) {
		sendNotifiier.addSendListener(portSendListener);
	}
	@Override
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		sendNotifiier.notifyPortSend(null, message, sendId);
	}
	@Override
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		sendNotifiier.removeSendListener(portSendListener);
	}
//	@Override
//	public void addReceiveListener(TypedReceiveListener portReceiveListener) {
//		if (typedPortReceiveListeners.contains(portReceiveListener))
//			return;
//		typedPortReceiveListeners.add(portReceiveListener);	
//		
//	}
//
//	@Override
//	public void notifyPortReceive(String remoteEnd, Serializable message) {
//		for (TypedReceiveListener portReceiveListener:typedPortReceiveListeners)
//			portReceiveListener.messageReceived(remoteEnd, message);
//		
//	}
//
//	@Override
//	public void removeReceiveListener(
//			TypedReceiveListener portReceiveListener) {
//		typedPortReceiveListeners.remove(portReceiveListener);	
//	}
	

	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message, int length) {
		try {
			notifyTypedPortReceive(remoteClientName, (Serializable) bufferSerializationSupportPool.objectFromInputBuffer(message));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
	}
	@Override
	public void send(Serializable message) {
		try {
			send (bufferSerializationSupportPool.outputBufferFromObject(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sendId) {
		notifyPortSend(null, message, sendId);
		
	}
	@Override
	public void reply(Serializable message) {
		send(message);
		
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
		send(message);
		
	}
	@Override
	public void send(String remoteName, Serializable message) {
		send(message);
		
	}
	@Override
	public String getLogicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return delegateDuplexClientInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return delegateDuplexClientInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		delegateDuplexClientInputPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		delegateDuplexClientInputPort.setLogicalRemoteEndPoint(newVal);

		
	}

	@Override
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		delegateDuplexClientInputPort.notifyConnectFailure(remoteEnd, message, null);
		
	}
	@Override
	public void reply(ByteBuffer message) {
		delegateDuplexClientInputPort.reply(message);		
	}
	
	public String getLocalName() {
		// TODO Auto-generated method stub
		return delegateDuplexClientInputPort.getLocalName();
	}
	public Set<String> getConnections() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return false;
	}
	

}
