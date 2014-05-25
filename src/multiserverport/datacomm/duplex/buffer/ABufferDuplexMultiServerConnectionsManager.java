package multiserverport.datacomm.duplex.buffer;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.simplex.SimplexClientInputPort;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import util.trace.Tracer;
import variableserverport.datacomm.duplex.DuplexVariableServerClientPort;
import variableserverport.datacomm.duplex.buffer.AnAbstractDuplexBufferVariableServerConnectionsManager;

// implements create client port
// this should be part of static
// sp
public class ABufferDuplexMultiServerConnectionsManager extends 
//ABufferStaticSessionPortConnectionsManagerFullP2P 
	AnAbstractDuplexBufferVariableServerConnectionsManager
	implements  BufferDuplexMultiServerConnectionsManager {
//	ReceiveListener<ByteBuffer> {
//	Set<String> serversToBeHeardFrom = new HashSet();

	protected Set<String> serversToBeHeardFrom = new HashSet();
	
	int numExpectedServers;
	
	public ABufferDuplexMultiServerConnectionsManager(DuplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aChoice/*,
										 ServerPortDescription aServerPortDescription*/) {
		super(aDuplexBufferSessionPort, aChoice);
	
	}
	public void initPeers(SessionParticipantDescription[] serverPortDescriptions) {
		for (int i = 0; i < serverPortDescriptions.length; i++) {
			fillInPeerInfo(serverPortDescriptions[i]);
		}
	}
	
	@Override
	public void connect(SessionParticipantDescription[] serverPortDescriptions) {
		// not sure if this stuff is useful for non fault tolerant apps
		numExpectedServers = serverPortDescriptions.length;
		for (int i = 0; i < serverPortDescriptions.length; i++) {
			serversToBeHeardFrom.add(serverPortDescriptions[i].getName());

		}
		for (int i = 0; i < serverPortDescriptions.length; i++) {
//			servers.add(serverPortDescriptions[i]);
//			sessionServerNames
			joined(serverPortDescriptions[i]);

		}
	}

	// problem is that this methid iscalled wither when a connection initiated by this computer completes
	// or when one initiated by another computer completes
	// the client port notification comes directly to it from lower level  port
	// so does the server port notification
	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		SimplexClientInputPort<ByteBuffer> port = nameToClientInputPort.get(remoteEnd);
		if (port != null) {
			Tracer.info(this, "This computer has connected to:" + remoteEnd);
			serversToBeHeardFrom.remove(remoteEnd);


		}	else {
			Tracer.info(this, "Remote end:" + remoteEnd + " has connected to this computer");
			// this case does not occur with replicated port, only with full p2p session
			// let them handle this case and let us not return
//			return;
		}
		
		Tracer.info(this, "Connected to server " + remoteEnd);
		notifyPortAboutConnect(remoteEnd);
//		bufferBroadcastPort.notifyConnect(remoteEnd);	

//		maybeNotifyAggregateConnectOrDisconnect();
	}
	protected void notifyPortAboutConnect(String remoteEnd) {
		maybeNotifyAggregateConnectOrDisconnect();
	}
	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		super.notConnected(remoteEnd, message, aConnectionType);
//		nameToClientInputPort.remove(remoteEnd);
		serversToBeHeardFrom.remove(remoteEnd);
//		nameToNotifiedClientInputPort.remove(remoteEnd);
		Tracer.info(this, remoteEnd + " removed from  brodcasting port");
//		bufferBroadcastPort.notifyConnectFailure(remoteEnd, message);
		maybeNotifyAggregateConnectOrDisconnect();

		
	}
	
	// really meant for replicated fault tolerant
	// no point aggregating the connects
	void maybeNotifyAggregateConnectOrDisconnect() {
		if (!serversToBeHeardFrom.isEmpty()) return;
		if (nameToClientInputPort.size() > 0) {			
			Tracer.info(this, "Able to connect to " + nameToClientInputPort.size() + " out of " + numExpectedServers + " servers ");
			
//			bufferBroadcastPort.notifyConnect(bufferBroadcastPort.getLogicalRemoteEndPoint());
			// we want to postpone individual connect until rpc return value is created for logical server
			// but that leads to thrasing of current server and some bug I need to trace
			for (String server:nameToClientInputPort.keySet()) {
				Tracer.info(this, "Connected to:" + server);
				variableServerClientPort.notifyConnect(server, ConnectionType.TO_PHYSICAL_SERVER); // this will trigger a join

			}
			if (numExpectedServers > 0)
			variableServerClientPort.notifyConnect(variableServerClientPort.getLogicalRemoteEndPoint(), ConnectionType.TO_LOGICAL_SERVER);

		} else {
			Tracer.info(this, "Not able to connect to  any of the " + numExpectedServers + " servers");

			variableServerClientPort.notifyConnectFailure(null, "Connect failure from all servers", null);
		}
	}
	
	@Override
	protected DuplexClientInputPort<ByteBuffer>  createClientInputPort(String aHost, String aServerId, String aServerName, String aClientName){
		Tracer.info (this, "Creatining client port for " + aServerName);
		
		DuplexClientInputPort<ByteBuffer> clientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort(aHost, 
				aServerId, aServerName, aClientName);
		Tracer.info (this, "Adding  receive listener for " + clientInputPort);

		clientInputPort.addReceiveListener(this); // ouch, side effect
		return clientInputPort;
	}

	@Override
	public void newState(Serializable newState) {
		// TODO Auto-generated method stub
		
	}




}
