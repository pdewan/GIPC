package sessionport.datacomm.duplex.object.relayed;

import inputport.AConnectRegistrarAndNotifier;
import inputport.ConnectionListener;
import inputport.ConnectionRegistrarAndNotifier;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.simplex.buffer.ASendRegistrarAndNotifier;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import trace.port.AConnectionEvent;
import trace.port.AReplaceConnectionEvent;
import trace.port.ConnectiontEventBus;
import util.trace.Tracer;


public class ARelayingObjectDuplexSessionPort implements DuplexSessionPort<Object>, ConnectionListener {
//	String sessionServerHost; 
//	String sessionServerId;
	String sessionName;
//	String sessionsServerName;
	String id;
	String name;
	DuplexRPCClientInputPort sessionsServerClientPort;
//	RelayingSessionsServer sessionsServer;
	ConnectionRegistrarAndNotifier connectRegistrarAndNotifier = new AConnectRegistrarAndNotifier();
	protected ReceiveRegistrarAndNotifier<Object> receiptRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();
	SendRegistrarAndNotifier sendRegistrarAndNotifier = new ASendRegistrarAndNotifier();
	String lastSender;	
	ServerPortDescription sessionClientDescription;
	RelayingDuplexConnectionsManager connectionsManager;	
	SendTrapper<Object, Object> sendTrapper;
	ReceiveTrapper<Object, Object> receiveTrapper;	
	protected ParticipantChoice participantChoice;

	public ARelayingObjectDuplexSessionPort (DuplexRPCClientInputPort aSessionsServerClientPort, String aSessionName, String anId, String aName, ParticipantChoice aJoinChoice) {

		sessionsServerClientPort = aSessionsServerClientPort;
		id = anId;
		name = aName;
		sessionName = aSessionName;
		participantChoice = aJoinChoice;

		connectionsManager = createConnectionsManager();


//		sendSendTrapper( createSendTrapper());
		setSendTrapper();
		setReceiveTrapper(createReceiveTrapper());
		if (participantChoice != ParticipantChoice.CLIENT_ONLY) { 
		try {
			sessionClientDescription =  new AServerPortDescription(InetAddress.getLocalHost().getHostName(), anId, aName);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		}
		else {
			// informs other members about memmber
			// with host and name, back channel can be used to talk to client
			sessionClientDescription =  new AServerPortDescription(null, null, aName); 
		}
	}
	// overridden in subclass to set group trapper
	protected void setSendTrapper() {
		sendSendTrapper( createSendTrapper());

	}
	protected void sendSendTrapper(SendTrapper<Object, Object> newVal) {
		if (newVal == null) return;
		if (newVal.getDestination() == null) {
			newVal.setDestination(sendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == sendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, sendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
		sendTrapper = newVal;
	}
	protected void sendReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		receiveTrapper = newVal;
	}
	// this one should have single response
	protected SendTrapper<Object, Object> createSendTrapper() {
		return ObjectDuplexSPRelayedTrapperSelector.getTrapperSelector().createSendTrapper(this, connectionsManager);
	}
	// this also should have single response
	protected ReceiveTrapper<Object, Object> createReceiveTrapper() {
		return ObjectDuplexSPRelayedTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiptRegistrarAndNotifier);

	}
	protected RelayingDuplexConnectionsManager createConnectionsManager() {
		return new ARelayingDuplexConnectionsManager(this, participantChoice);
	}
	public void addConnectionListener(ConnectionListener portConnectListener) {
		connectRegistrarAndNotifier.addConnectionListener(portConnectListener);
	}
	boolean connected;
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		connectRegistrarAndNotifier.notifyConnect(remoteEnd, aConnectionType);
		connected = true;
	}
	public void notifyConnectFailure(String remoteEnd, String message, ConnectionType aConnectionType) {
		connectRegistrarAndNotifier.notifyConnectFailure(remoteEnd, message, null);
	}
	public void notifyDisconnect(String remoteEnd, boolean eof,
			String closeReason, ConnectionType aConnectionType) {
		connectRegistrarAndNotifier.notifyDisconnect(remoteEnd, eof,
				closeReason, null);
	}
	public void removeConnectionListener(ConnectionListener portConnectListener) {
		connectRegistrarAndNotifier.removeConnectionListener(portConnectListener);
	}
	public void addReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receiptRegistrarAndNotifier.addReceiveListener(portReceiveListener);
	}

	public void notifyPortReceive(String remoteEnd, Object message) {
		lastSender = remoteEnd;
		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}
	public void removeReceiveListener(
			ReceiveListener<Object> portReceiveListener) {
		receiptRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
	}
	public void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId) {
		sendRegistrarAndNotifier.notifyPortSend(aRemoteEnd, message, sendId);
	}
	public void addSendListener(ByteBufferSendListener portSendListener) {
		sendRegistrarAndNotifier.addSendListener(portSendListener);
	}
	public void removeSendListener(ByteBufferSendListener portSendListener) {
		sendRegistrarAndNotifier.removeSendListener(portSendListener);
	}
	@Override
	public String getLocalName() {
		return name;
	}
	@Override
	public void connect() {
//		sessionsServerClientPort.addConnectionListener(connectionsManager);
		sessionsServerClientPort.addConnectionListener(this);

		sessionsServerClientPort.connect();
//		connectionsManager.joinSessionsServer(sessionServerHost, sessionServerId, sessionsServerName, sessionName, sessionClientDescription);
//		connectionsManager.joinSessionsServer(sessionsServerClientPort, sessionClientDescription, sessionName);

	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		if (sessionsServerClientPort.getLogicalRemoteEndPoint().equals(aRemoteEndName)) // avoids multiple join calls
		connectionsManager.joinSessionsServer(sessionsServerClientPort, sessionClientDescription, sessionName);
		connectRegistrarAndNotifier.notifyConnect(aRemoteEndName, aConnectionType);
		
	}
	@Override
	public void disconnect() {
		connectionsManager.closeAllConnections();
	}
	@Override
	public void send(Object message) {
		send(getSender(), message);
	}

	@Override
	public void send(String remoteName, Object message) {
		sendTrapper.send(remoteName, message);
	}
	@Override
	public void reply(Object message) {
		send(message);
	}
	@Override
	public void reply(String aRemoteEnd, Object aMessage) {
		send(aRemoteEnd, aMessage);
	}
	@Override
	public String getSender() {
		return lastSender;
	}
	// returns all servers to which client is connected
	// should it also return all clients to which server is connected
	@Override
	public Set<String> getConnections() {
		return connectionsManager.getRemoteEndPoints();
	}
////	@Override
//	public Set<String> getClientConnections() {
//		return connectionsManager.getClientConnections();
//	}
////	@Override
//	public Set<String> getServerConnections() {
//		return connectionsManager.getRemoteServers();
//	}
////	@Override
//	public Set<String> getMemberConnections() {
//		return connectionsManager.getRemoteMembers();
//	}
	@Override
	public void setSender(String newVal) {
		lastSender = newVal;
	}
	public SendTrapper<Object, Object> getSendTrapper() {
		return sendTrapper;
	}
	public void setSendTrapper(SendTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(sendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == sendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent(new AReplaceConnectionEvent(this, sendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, sendTrapper, true));
		}
		this.sendTrapper = newVal;
//		sendTrapper.setDestination(connectionsManager);
	}
	public ReceiveTrapper<Object, Object> getReceiveTrapper() {
		return receiveTrapper;
	}

	public void setReceiveTrapper(ReceiveTrapper<Object, Object> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(receiveTrapper.getDestination());
			Tracer.warning("receive trapper == mull!");
		} else if (newVal.getDestination() == receiveTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent(new AReplaceConnectionEvent(this, receiveTrapper, newVal, false, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, false));
		}
		this.receiveTrapper = newVal;
	}
	
	@Override
	public boolean isConnected(String remoteName) {
		// TODO Auto-generated method stub
		return connected;
	}

	@Override
	public String getServerId() {
		// TODO Auto-generated method stub
		return id;
	}
	

	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}
	
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<ReceiveListener<Object>> getReceiveListeners() {
		return receiptRegistrarAndNotifier.getReceiveListeners();
	}
	String physicalRemoteEndPoint;
	String logicalRemoteEndPoint;
	@Override
	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return physicalRemoteEndPoint;
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		physicalRemoteEndPoint = newVal;
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;
	}
	
	@Override
	public Set<String> getClientConnections() {
		return connectionsManager.getClientConnections();
	}

	@Override
	public Set<String> getServerConnections() {
		return connectionsManager.getServerConnections();
	}

	@Override
	public Set<String> getMemberConnections() {
		return connectionsManager.getMemberConnections();
	}

	@Override
	public List<SessionParticipantDescription> getServers() {
		return connectionsManager.getServers();
	}

	@Override
	public List<SessionParticipantDescription> getMembers() {
		return connectionsManager.getMembers();
	}

	@Override
	public List<SessionParticipantDescription> getClients() {
		
		return  connectionsManager.getClients();
	}

	@Override
	public SessionParticipantDescription getServer(String aName) {
		return connectionsManager.getServer(aName);
	}

	@Override
	public SessionParticipantDescription getClient(String aName) {
		return  connectionsManager.getClient(aName);
	}

	@Override
	public SessionParticipantDescription getMember(String aName) {
		return connectionsManager.getMember(aName);
	}
	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return participantChoice;
	}
	@Override
	public ReceiveReturnMessage<Object> receive() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ReceiveReturnMessage<Object> receive(String aSource) {
		// TODO Auto-generated method stub
		return null;
	}

}
