package variableserverport.datacomm.simplex.buffer;

import inputport.AConnectRegistrarAndNotifier;
import inputport.ConnectionListener;
import inputport.ConnectionRegistrarAndNotifier;
import inputport.ConnectionType;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.simplex.buffer.ASendRegistrarAndNotifier;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.AReplaceConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import variableserverport.SimplexVariableServerClientPort;

// should this also be called variable server
public  abstract class AnAbstractSimplexBufferMultiServerPort 
	implements SimplexVariableServerClientPort<ByteBuffer> 
{

	protected String name;	
	protected ConnectionRegistrarAndNotifier connectRegistrarAndNotifier = new AConnectRegistrarAndNotifier();
	protected ReceiveRegistrarAndNotifier<ByteBuffer> receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();
	protected SendRegistrarAndNotifier sendRegistrarAndNotifier = new ASendRegistrarAndNotifier();
	protected SimplexBufferVariableServerConnectionsManager connectionsManager; // this has a reference to the server input port

	protected SendTrapper<ByteBuffer, ByteBuffer> sendTrapper;


	protected ReceiveTrapper<ByteBuffer, ByteBuffer> receiveTrapper;
	
	protected ParticipantChoice participantChoice;
	
	public AnAbstractSimplexBufferMultiServerPort (
			 String aName, ParticipantChoice aParticipantChoice) {
		Tracer.info(this, "Creating P2P Buffer Broadcast Port");
		participantChoice = aParticipantChoice;
		name = aName;
		connectionsManager = createFP2PBufferConnectionsManager(this, aParticipantChoice);	
		Tracer.info(this, "Created connection manager for :" + connectionsManager);

		setSendTrapper(createSendTrapper());


		setReceiveTrapper(createReceiveTrapper());


	}
	@Override
	public ParticipantChoice getParticipantChoice() {
		return participantChoice;
	}
	
	protected SendTrapper<ByteBuffer, ByteBuffer> createSendTrapper() {
		return BufferDuplexSPFP2PTrapperSelector.getTrapperSelector().createSendTrapper(this, connectionsManager);

	}
	// why receive trapper in simplex>
	protected ReceiveTrapper<ByteBuffer, ByteBuffer>  createReceiveTrapper() {
		return BufferDuplexSPFP2PTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);

	}
	protected abstract SimplexBufferVariableServerConnectionsManager createFP2PBufferConnectionsManager(SimplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice);
	public void addConnectionListener(ConnectionListener portConnectListener) {
		connectRegistrarAndNotifier.addConnectionListener(portConnectListener);
	}
	public void notifyConnect(String remoteEnd, ConnectionType aConnectionType) {
		connectRegistrarAndNotifier.notifyConnect(remoteEnd, aConnectionType);
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
			ReceiveListener<ByteBuffer> portReceiveListener) {
		receiveRegistrarAndNotifier.addReceiveListener(portReceiveListener);
	}
//	@Override
//	public List<ReceiveListener<ByteBuffer>> getReceiveListeners() {
//		return receiveRegistrarAndNotifier.getReceiveListeners();
//	}

	public void notifyPortReceive(String remoteEnd, java.nio.ByteBuffer message) {
//		lastSender = remoteEnd;
		receiveTrapper.notifyPortReceive(remoteEnd, message);
	}
	
	public void removeReceiveListener(
			ReceiveListener<ByteBuffer> portReceiveListener) {
		receiveRegistrarAndNotifier.removeReceiveListener(portReceiveListener);
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
	public void disconnect() {
		connectionsManager.closeAllConnections();
	}

	
	@Override
	public void send(String remoteName, ByteBuffer message) {
		sendTrapper.send(remoteName, message);
	}
	
		

	@Override
	public Set<String> getConnections() {
		return connectionsManager.getRemoteEndPoints();
	}
//	@Override
//	public Set<String> getClientConnections() {
//		return connectionsManager.getRemoteClients();
//	}

	public SendTrapper<ByteBuffer, ByteBuffer> getSendTrapper() {
		return sendTrapper;
	}
	public void setSendTrapper(SendTrapper<ByteBuffer, ByteBuffer> newVal) {
		if (newVal.getDestination() == null) {
			newVal.setDestination(sendTrapper.getDestination());
			Tracer.warning("send trapper == mull!");
		} else if (newVal.getDestination() == sendTrapper) { // adding a new one in front of old one
			ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, sendTrapper, newVal, true, false));

		} else {
			ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
		}
//		DistEventsBus.newEvent(new AConnectionEvent(this, serializableCallSendTrapper, true));
		
		this.sendTrapper = newVal;
//		DistEventsBus.newEvent(
//				new AConnectionEvent(this, newVal, true));
//		sendTrapper.setDestination(connectionsManager);
	}
	public ReceiveTrapper<ByteBuffer, ByteBuffer> getReceiveTrapper() {
		return receiveTrapper;
	}

	public void setReceiveTrapper(ReceiveTrapper<ByteBuffer, ByteBuffer> newVal) {
		
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
	
	protected String logicalRemoteEndPoint;
	protected String physicalRemoteEndPoint;
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
	public Set<String> getClientConnections() {
		return connectionsManager.getClientConnections();
	}

	
	public Set<String> getServerConnections() {
		return connectionsManager.getServerConnections();
	}

	
	public Set<String> getMemberConnections() {
		return connectionsManager.getMemberConnections();
	}

	
	public List<SessionParticipantDescription> getServers() {
		return connectionsManager.getServers();
	}

	
	public List<SessionParticipantDescription> getMembers() {
		return connectionsManager.getMembers();
	}

	
	public List<SessionParticipantDescription> getClients() {
		
		return  connectionsManager.getClients();
	}

	
	public SessionParticipantDescription getServer(String aName) {
		return connectionsManager.getServer(aName);
	}

	
	public SessionParticipantDescription getClient(String aName) {
		return  connectionsManager.getClient(aName);
	}

	
	public SessionParticipantDescription getMember(String aName) {
		return connectionsManager.getMember(aName);
	}

	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}

}
