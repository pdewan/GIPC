package syncipc;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.DisconnectListener;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.Set;

import oldtypedip.AnInheritingTypedClientInputPort;
import oldtypedip.TypedClientInputPort;
import oldtypedip.TypedReceiveListener;
import port.old.ByteBufferReceiveListener;

public class ASyncClientInputPort implements TypedClientInputPort, TypedReceiveListener {
	TypedClientInputPort clientInputPort;
	
	Object ackReceivedSemaphore = new Object();
	public ASyncClientInputPort(String theHost, String theServerId, String theClientName) {
		clientInputPort = new AnInheritingTypedClientInputPort(theHost, theServerId, theClientName);
		clientInputPort.addTypedReceiveListener(this);
	}
	public synchronized void send(ByteBuffer message) {
		clientInputPort.send(message);
		try {
			ackReceivedSemaphore.wait();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	public void connect() {
		clientInputPort.connect();
	}
	public void disconnect() {
		clientInputPort.disconnect();
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		clientInputPort.addConnectionListener(portConnectListener);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		clientInputPort.removeConnectionListener(portConnectListener);
	}
	public void addDisconnectListener(DisconnectListener portCloseListener) {
		clientInputPort.addDisconnectListener(portCloseListener);
	}
	public void removeDisconnectListener(DisconnectListener portCloseListener) {
		clientInputPort.removeDisconnectListener(portCloseListener);
	}
//	public void addConnectionListener(ConnectionListener portCloseListener) {
//		clientInputPort.addConnectionListener(portCloseListener);
//	}
//	public void removeConnectionListener(ConnectionListener portCloseListener) {
//		clientInputPort.removeConnectionListener(portCloseListener);
//	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		clientInputPort.notifyConnect(remoteEnd, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		clientInputPort.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		clientInputPort.notifyConnectFailure(remoteEnd, message, null);
	}
	public void send(Serializable message) {
		clientInputPort.send(message);
	}
	public void addTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		clientInputPort.addTypedReceiveListener(portReceiveListener);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		clientInputPort.addSendListener(portSendListener);
	}
//	public void addReceiveListener(ByteBufferReceiveListener portReceiveListener) {
//		clientInputPort.addReceiveListener(portReceiveListener);
//	}
	public void removeTypedReceiveListener(TypedReceiveListener portReceiveListener) {
		clientInputPort.removeTypedReceiveListener(portReceiveListener);
	}
	public void notifyTypedPortReceive(String remoteEnd, Serializable message) {
		clientInputPort.notifyTypedPortReceive(remoteEnd, message);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		clientInputPort.removeSendListener(portSendListener);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		clientInputPort.notifyPortSend(aRemoteEnd, message, sendId);
	}
//	public void removeReceiveListener(ByteBufferReceiveListener portReceiveListener) {
//		clientInputPort.removeReceiveListener(portReceiveListener);
//	}
	public void notifyPortReceive(String remoteEnd, ByteBuffer message,
			int length) {
		clientInputPort.notifyPortReceive(remoteEnd, message, length);
	}
	@Override
	public void messageReceived(String remoteClientName, Serializable message) {
		if (message instanceof AnAck)
			ackReceivedSemaphore.notify();	
		Serializable s = 3;
		Integer[] integerList = { 3, 3};
//		int[] intList = {3, 3};
//		Serializable[] slist = { 3, 3};
//		Object[] olist = intList;
//		slist = integerList;
//		slist = intList;
		
	}
//	@Override
//	public void reply(ByteBuffer message) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void send(String remoteName, ByteBuffer message) {
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public void reply(Serializable message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void send(String remoteName, Serializable message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public String getLogicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {

		
	}
	@Override
	public boolean isConnected(String remoteName) {
		return false;
	}

//	@Override
//	public void send(String remoteName, Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void send(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void reply(Object message) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void addReceiveListener(ReceiveListener portReceiveListener) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void removeReceiveListener(ReceiveListener portReceiveListener) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void notifyPortReceive(String remoteEnd, Object message, int length) {
//		// TODO Auto-generated method stub
//		
//	}
	@Override
	public void reply(ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void addReceiveListener(ByteBufferReceiveListener portReceiveListener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeReceiveListener(
			ByteBufferReceiveListener portReceiveListener) {
		// TODO Auto-generated method stub
		
	}
	
	public String getLocalName() {
		return clientInputPort.getLocalName();
	}
	public Set<String> getConnections() {
		return clientInputPort.getConnections();
	}
	

}
