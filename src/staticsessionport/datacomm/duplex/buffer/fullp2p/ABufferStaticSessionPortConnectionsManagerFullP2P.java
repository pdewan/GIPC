package staticsessionport.datacomm.duplex.buffer.fullp2p;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import multiserverport.datacomm.duplex.buffer.ABufferDuplexMultiServerConnectionsManager;
import port.ParticipantChoice;
import port.sessionserver.ServerPortDescription;
import util.trace.Tracer;
import variableserverport.datacomm.duplex.DuplexVariableServerClientPort;


public class ABufferStaticSessionPortConnectionsManagerFullP2P  
//	extends AnAbstractDuplexBufferBroadcastConnectionsManager  
    extends ABufferDuplexMultiServerConnectionsManager  

	implements BufferStaticSessionPortConnectionManagerFullP2P{
	protected ServerPortDescription serverPortDescription;
	DuplexServerInputPort<ByteBuffer> serverInputPort;
	Map<String, Integer> remoteEndToNumConnects = new HashMap();
//	ParticipantChoice joinChoice;


	
	public ABufferStaticSessionPortConnectionsManagerFullP2P(
			DuplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice
										 ) {
		super(aDuplexBufferSessionPort, aParticipantChoice);
//		createServerInputPort(aServerPortDescription);
	}
//	@Override
//	public void connect(ServerPortDescription[] serverPortDescriptions) {
//		
//		for (int i = 0; i < serverPortDescriptions.length; i++) {
//			joined(serverPortDescriptions[i]);
//
//		}
//	}
	
	protected boolean doublyConnected(String remoteEnd) {
		return 
				!sessionServerNames.contains(remoteEnd) &&  // server does not open connection to us
				serverInputPort != null && getClientInputPort(remoteEnd) != null;
	}
	@Override
	public Set<String> getRemoteEndPoints() {
		if (joinChoice != ParticipantChoice.SERVER_ONLY) {
			Set<String> retVal = super.getRemoteEndPoints();
			if (serverInputPort != null)
				retVal.addAll(serverInputPort.getConnections());
			return retVal;
		} 	else
			return serverInputPort.getConnections();
	}
//		
	@Override
	public void createServerInputPort (ServerPortDescription aServerPortDescription) {
		
		if (aServerPortDescription == null || joinChoice == ParticipantChoice.CLIENT_ONLY ) return;
		serverPortDescription = aServerPortDescription;
		if  (aServerPortDescription.getID() == null)
			return;

//		SessionClientDescription sessionClientDescription = new ASessionClientDescription(sessionsServerHost, sessionsServerId, duplexBufferSessionPort.getLocalName());		
		createServerInputPort();
//		serverInputPort = BufferSimplexInputPortSelector.createServerSimplexInputPort(
//					aServerPortDescription.getID(), 
//					aServerPortDescription.getName());	
		setListeners();
//		serverInputPort.addReceiveListener(this);
//		serverInputPort.addConnectionListener(this);
		serverInputPort.connect();	// no notification needed for server port creation
//		maybeNotifyOnServerPortCreation();

		
	}
	@Override
	protected void addListenersToClientPort(DuplexClientInputPort<ByteBuffer> aPort) {
		super.addListenersToClientPort(aPort);
		aPort.addReceiveListener(this);	
		
	}
	@Override
	public void send(String remoteName, ByteBuffer message) {
		if (joinChoice == ParticipantChoice.SERVER_ONLY || getClientInputPort(remoteName) == null) {
			serverInputPort.send(remoteName, message);
		} else {
			super.send(remoteName, message); // use client ports instead. could use server ports for JOIN and SYMMETRIC_JOIN
		}
		
		
	}
	
//	void createServerInputPort() {		
//		serverInputPort = BufferSimplexInputPortSelector.createServerSimplexInputPort(
//				serverPortDescription.getID(), 
//				serverPortDescription.getName());
//		Tracer.info(this, "Created server input port " + serverInputPort + " for " + serverPortDescription);
//	}
	// create duplex rather than simplex because some joiners are client only
	void createServerInputPort() {
		if (joinChoice !=  ParticipantChoice.CLIENT_ONLY) {
		// create a group port in case the joiner is SERVER_ONLY
		// but duplex is enough as we need to only send to individual but different clients
//		serverInputPort = BufferGroupInputPortSelector.createGroupServerInputPort(
		serverInputPort = DuplexBufferInputPortSelector.createDuplexServerInputPort(

				serverPortDescription.getID(), 
				serverPortDescription.getName());
		Tracer.info(this, "Created server input port " + serverInputPort + " for " + serverPortDescription);
		}
	}
//	protected void maybeNotifyOnServerPortCreation() {
//		variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName());
//
//	}
//	@Override
//	public void joined(ServerPortDescription sessionClientDescription) {
//
//		remoteEndToNumConnects.put(sessionClientDescription.getName(), 0); 
//		super.joined(sessionClientDescription);
//		
//	}
	// since this is full p2p so we must handle this differently
	// will get two connects for each remote end, so should wait for each before
	// sending notification
	// actually the client notification goes directly through the port, so maybe should
	// just let that be the only notification and forget about a two-way connect
	// at that time a message can be sent to thatport
	// it is possible that a message will be received before the connect notification for the remote end is
	// this seems wrong, it seems both messges come from here
	
	// need to change this in ASessionBasedF2PBufferConnectionManager
	protected ConnectionType toConnectionType (String aRemoteEnd) {
		if (initSessionServerNames.contains(aRemoteEnd) || sessionServerNames.contains(aRemoteEnd)) {
			return ConnectionType.SERVER_TO_SESSION;
		} else if (initSessionClientNames.contains(aRemoteEnd) || sessionClientNames.contains(aRemoteEnd)) {
			return ConnectionType.CLIENT_TO_SESSION;
		} else if (initSessionMemberNames.contains(aRemoteEnd) || sessionMemberNames.contains(aRemoteEnd)) {
			return ConnectionType.MEMBER_TO_SESSION;
		}
		return null;
		
	}
	protected void notifyPortAboutConnect(String aRemoteEnd) {
		Integer curCount = remoteEndToNumConnects.get(aRemoteEnd);
		if (curCount == null) {
			curCount = 0;
		}
		curCount++;
		remoteEndToNumConnects.put(aRemoteEnd, curCount);
		if (doublyConnected(aRemoteEnd) && curCount < 2)
			return;
		doNotifyConnect (aRemoteEnd, toConnectionType(aRemoteEnd));

//		doNotifyConnect (aRemoteEnd, ConnectionType.TO_PEER);
//
//			
//		switch (joinChoice) {
//		case SYMMETRIC_JOIN:
//		case MEMBER:
//			if (curCount == 2) 
//				doNotifyConnect (aRemoteEnd, ConnectionType.TO_PEER);
//			break;
//		
//		case SERVER_ONLY:
//		case CLIENT_ONLY:
//			doNotifyConnect (aRemoteEnd, ConnectionType.TO_PEER);
//		}


		
	}
	
	protected void doNotifyConnect(String aRemoteEnd, ConnectionType aConnectionType) {
		variableServerClientPort.notifyConnect(aRemoteEnd, aConnectionType);
	}
	
	void setListeners() {
		serverInputPort.addReceiveListener(this);
//		DistEventsBus.newEvent(new AConnectionEvent(serverInputPort, this, false));
		serverInputPort.addConnectionListener(this);
//		DistEventsBus.newEvent(new AConnectionEvent(serverInputPort, this, false));
		Tracer.info(this, "Added server input port " + serverInputPort + " as my listener");

	}
	
	@Override
	protected DuplexClientInputPort<ByteBuffer>  createClientInputPort(String aHost, String aServerId, String aServerName, String aClientName){
		return DuplexBufferInputPortSelector.createDuplexClientInputPort
				(aHost, 
				aServerId, aServerName, aClientName);
	}
	

}
