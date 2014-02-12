package oldtypedip;


import inputport.datacomm.duplex.NoMessageReceivedByResponderException;

import java.io.Serializable;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.Set;

import oldgroupip.AGroupServerInputPort;
import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;





public class AnInheritingTypedGroupServerInputPort extends AGroupServerInputPort implements TypedGroupServerInputPort {
//	Map<String, BufferSerializationSupport> remoteEndToBufferSerializationSupport = new HashMap();
//	BufferSerializationSupport bufferSerializationSupport = new ABufferSerializationSupport();
	Serializer bufferSerializationSupport;
//	MultiStreamBufferSerializationSupport multiStreamBufferSerializationSupport = new AMultiStreamBufferSerializationSupport();
	TypedReceiptNotifier delegateTypedListenablePort = new ATypedReceiptNotifier();

	public AnInheritingTypedGroupServerInputPort(String theServerId, String theServerName) {
		super(theServerId, theServerName);
		bufferSerializationSupport = SerializerPoolSelector.createSerializerPool(this);
		super.addReceiveListener(this);
		//delegateTypedListenablePort.addReceiveListener(this);
	}
	
	Serializer bufferSerializationSupport(String remoteEndName) {
//		return multiStreamBufferSerializationSupport.bufferSerializationSupport(remoteEndName);
		return bufferSerializationSupport;
	}
	

	@Override
	public synchronized void send(String remoteName, Serializable message) {
		try {

		send (remoteName, bufferSerializationSupport.outputBufferFromObject(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void reply(Serializable message) {
		try {
			reply (bufferSerializationSupport(getLastSender()).outputBufferFromObject(message));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public synchronized void send(Serializable message) {
		reply(message);
	}
	@Override
//	public synchronized void send(List<String> clientNames, Serializable message) {
//		//String[] peerNames = getConnectionEndPoints();
////		bufferSerializationSupport.putSerializableInOutputBuffer(message);
////		ByteBuffer untypedMessage = bufferSerializationSupport.outputBufferFromSerializable(message);
//		ByteBuffer untypedMessage = null;
//		int position = 0;
//		int limit = 0;
//		for (String clientName:clientNames) {
//			if (untypedMessage == null) {
//				untypedMessage = bufferSerializationSupport(clientName).outputBufferFromSerializable(message);
//				position = untypedMessage.position();
//				limit = untypedMessage.limit();
//			} 
//			else
//				untypedMessage = ByteBuffer.wrap(untypedMessage.array(), position, limit);
//			send(clientName,  untypedMessage);
//		}		
//	}
	public synchronized void send(Set<String> clientNames, Serializable message) {
		//String[] peerNames = getConnectionEndPoints();
//		bufferSerializationSupport.putSerializableInOutputBuffer(message);
//		ByteBuffer untypedMessage = bufferSerializationSupport.outputBufferFromSerializable(message);
		if (clientNames.size() == 0) return;
		try {
			ByteBuffer untypedMessage = bufferSerializationSupport.outputBufferFromObject(message);;
		
		int position = untypedMessage.position();;
		int limit = untypedMessage.limit();
		for (String clientName:clientNames) {
			Tracer.info(this, "Sending serializable:" + message + " to " + clientName);
			send(clientName,  untypedMessage);
			Tracer.info(this, "Sending buffer:" + untypedMessage + " to " + clientName);
			untypedMessage = ByteBuffer.wrap(untypedMessage.array(), position, limit);
		}	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public synchronized void sendAll(Serializable message) {
		send(getConnections(), message);
//		String[] peerNames = getConnectionEndPoints();
////		bufferSerializationSupport.putSerializableInOutputBuffer(message);
////		ByteBuffer untypedMessage = bufferSerializationSupport.outputBufferFromSerializable(message);
//		ByteBuffer untypedMessage = null;
//		int position = 0;
//		int limit = 0;
//		for (int i = 0;i < peerNames.length;  i++) {
//			if (untypedMessage == null) {
//				untypedMessage = bufferSerializationSupport(peerNames[i]).outputBufferFromSerializable(message);
//				position = untypedMessage.position();
//				limit = untypedMessage.limit();
//			} 
//			else
//				untypedMessage = ByteBuffer.wrap(untypedMessage.array(), position, limit);
//			send(peerNames[i],  untypedMessage);
//		}		
	}

	@Override
	public synchronized void sendOthers(Serializable message) {
		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
		Set<String> clientNames = getConnections();
		clientNames.remove(getLastSender());
		send(clientNames, message);
//		// wasted serialization if no others, but usually there will be
//		ByteBuffer untypedMessage = null;
//		int position = 0; 
//		int limit = 0;
//		for (int i = 0;i < peerNames.length;  i++) {			
//			if (peerNames[i].equals(lastSender))
//				continue;
//			if (untypedMessage == null) {
//				untypedMessage = bufferSerializationSupport(peerNames[i]).outputBufferFromSerializable(message);
//				position = untypedMessage.position(); // these will be changed by send
//				limit = untypedMessage.limit();
//			} else
//				untypedMessage = ByteBuffer.wrap(untypedMessage.array(), position, limit);
////			if (untypedMessage == null)
////				untypedMessage = bufferSerializationSupport.outputBufferFromSerializable(message);
//			send(peerNames[i],  untypedMessage);
//		}		
	}
	
	public void addTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		delegateTypedListenablePort.addTypedReceiveListener(portReceiveListener);
	}

	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		delegateTypedListenablePort.notifyTypedPortReceive(remoteEnd, message);
	}

	public void removeTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		delegateTypedListenablePort.removeTypedReceiveListener(portReceiveListener);
	}
	
	@Override
	public void notifyPortReceive(String remoteEnd, ByteBuffer message, int length) {
//		bufferSerializationSupport.setInputBuffer(message);
		try {
			notifyTypedPortReceive(remoteEnd, (Serializable) bufferSerializationSupport(remoteEnd).objectFromInputBuffer(message));
		} catch (StreamCorruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//
//		List<Object> serializables = bufferSerializationSupport(remoteEnd).objectsFromInputBuffer(message);
//		if (serializables.size() == 0)
////			delegateTypedListenablePort.notifyPortReceive(remoteEnd, message, length);
//			super.notifyPortReceive(remoteEnd, message, length);
//		else {
//			setLastSender(remoteEnd);
//			notifyTypedPortReceive(remoteEnd, (Serializable) serializables.get(0));			
//		}
		
//			delegateTypedListenablePort.notifyTypedPortReceive(remoteEnd, serializables.get(0));
		//notifyPortReceive(remoteEnd, message);
	}
	@Override
	public void messageReceived(String remoteClientName, ByteBuffer message, int length) {
//		super.messageReceived(remoteClientName, message, length);
		notifyPortReceive(remoteClientName, message, length);
	}

	public static void main (String[] args) {
		Tracer.showInfo(true);
		TypedGroupServerInputPort serverInputPort = new AnInheritingTypedGroupServerInputPort("9090", "test server");
		serverInputPort.connect();
		PrintingReplyingTypedReceiver echoingReceiveListener = new PrintingReplyingTypedReceiver(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addDisconnectListener(echoingReceiveListener);
		serverInputPort.addTypedReceiveListener(echoingReceiveListener);	
		serverInputPort.addSendListener(echoingReceiveListener);
	}

}
