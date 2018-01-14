package variableserverport.datacomm.simplex.buffer;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.SimplexClientInputPort;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.ASession;
import port.sessionserver.SessionParticipantDescription;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectiontEventBus;
import variableserverport.SimplexVariableServerClientPort;

// basically creates an individual connection to a client port
// some subclass actually created the client port
// send sends a message to the expilicitly named destination
// but receive makes it look as if the message came from the logical remote end point
// actually no interpretation of what receive is

// asssumes a join
public abstract class AnAbstractSimplexBufferVariableServerConnectionsManager implements SimplexBufferVariableServerConnectionsManager {
	protected Map<String, SimplexClientInputPort<ByteBuffer>> nameToClientInputPort = new HashMap();
	
//	Set<String> serversToBeHeardFrom = new HashSet();
	
//	int numExpectedServers;
	
	
	protected ParticipantChoice joinChoice;

	protected SimplexVariableServerClientPort<ByteBuffer> variableServerClientPort;
	protected List<SessionParticipantDescription> servers = new ArrayList();
	protected List<SessionParticipantDescription> clients = new ArrayList();
	protected List<SessionParticipantDescription> members = new ArrayList();
	protected List<SessionParticipantDescription> participants;
	protected Set<String> sessionParticipantNames = new HashSet();
	protected Set<String> sessionClientNames = new HashSet();
	protected Set<String> sessionServerNames = new HashSet();
	protected Set<String> sessionMemberNames = new HashSet();
	
	//  these are filled before connect
	protected Set<String> initSessionClientNames = new HashSet();
	protected Set<String> initSessionServerNames = new HashSet();
	protected Set<String> initSessionMemberNames = new HashSet();

	
	public AnAbstractSimplexBufferVariableServerConnectionsManager(
			SimplexVariableServerClientPort<ByteBuffer> aVariableServerPort,
			ParticipantChoice aParticipantChoice
			/*,
										 ServerPortDescription aServerPortDescription*/) {
		variableServerClientPort = aVariableServerPort;
		joinChoice = aParticipantChoice;

	}
	

	protected SimplexClientInputPort  getClientInputPort(String name) {
		return nameToClientInputPort.get(name);
	}
	//called by both session manager and multiserver port
	// session manager when it joins the session
	// a multiserver port when it does a group connect,
	// which calls individual connects through joined
	protected void fillInPeerInfo(SessionParticipantDescription sessionClientDescription) {
		if (sessionClientDescription.getName().equals(variableServerClientPort.getLocalName()))
			return;
	switch (sessionClientDescription.getParticipantChoice()) {
	case MEMBER:
	case SYMMETRIC_JOIN:
		
		initSessionMemberNames.add(sessionClientDescription.getName());
		
		break;
	case SERVER_ONLY:
//		if (!servers.contains(sessionClientDescription))
//		servers.add(sessionClientDescription);
		initSessionServerNames.add(sessionClientDescription.getName());
		break;
	case CLIENT_ONLY:
//		if (!clients.contains(sessionClientDescription))
//		clients.add(sessionClientDescription);
		initSessionClientNames.add(sessionClientDescription.getName());

		break;
	}
	}
	/*
	 * This is a heavily overloaded method, called bi
	 *called by both session manager and multiserver port
	 *session manager when it joins the session
	 * a multiserver port when it does a group connect,
	 * which calls individual connects through joined
	 * very confusing as a result
	 * The other party may be a late comer server, which would not be  a CLIENT
	 * so that is how we get round to pverloading ParticipantChoice SERVER_ONLy
	 */
	@Override
	public void joined(SessionParticipantDescription sessionClientDescription) {
		// in static port this would be done twice
//		fillInPeerInfo(sessionClientDescription);
		if (sessionClientDescription.getName().equals(variableServerClientPort.getLocalName()))
				return;
		switch (sessionClientDescription.getParticipantChoice()) {
		case MEMBER:
		case SYMMETRIC_JOIN:
			if (!members.contains(sessionClientDescription)) {
			members.add(sessionClientDescription);
			}
			sessionMemberNames.add(sessionClientDescription.getName());
			
			break;
		case SERVER_ONLY:
			if (!servers.contains(sessionClientDescription))
			servers.add(sessionClientDescription);
			sessionServerNames.add(sessionClientDescription.getName());
			break;
		case CLIENT_ONLY:
			if (!clients.contains(sessionClientDescription))
			clients.add(sessionClientDescription);
			sessionClientNames.add(sessionClientDescription.getName());

			break;
		}
		sessionParticipantNames.add(sessionClientDescription.getName());
		// this creates problem with
//		if (joinChoice == ParticipantChoice.SERVER_ONLY) // do not open connection if server
//			return;
		// do not open connection if you are server and the other party is client
		if (joinChoice == ParticipantChoice.SERVER_ONLY && sessionClientDescription.getParticipantChoice() == ParticipantChoice.CLIENT_ONLY) 
			return;
		Tracer.info(this, "Creating client input port for:" + sessionClientDescription);
		if (!sessionClientDescription.getName().equals(variableServerClientPort.getLocalName()) &&
				sessionClientDescription.getID() != null // this is a client, we cannot open connection to it
				// what does it mean to be client, not have a server port?
//				&& joinChoice != ParticipantChoice.SERVER_ONLY) { // what is going on here, do we not want to be client here

		) {

//		SimplexClientInputPort clientInputPort = createClientInputPort(sessionClientDescription.getHost(), 
//				sessionClientDescription.getID(), sessionClientDescription.getName(), variableServerClientPort.getLocalName());
			// need duplex for the case when this is client-only
		DuplexClientInputPort<ByteBuffer> clientInputPort = createClientInputPort(sessionClientDescription.getHost(), 
				sessionClientDescription.getID(), sessionClientDescription.getName(), variableServerClientPort.getLocalName());
		// fund of sync programming this must go in before connect exception ie caught so that not connected can delete server
		nameToClientInputPort.put(sessionClientDescription.getName(), clientInputPort);		
//		clientInputPort.addConnectionListener(this);
//		clientInputPort.addSendListener(this);	
		addListenersToClientPort(clientInputPort);
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, clientInputPort, true));
		
		clientInputPort.connect();
		}
//		else {
//		bufferBroadcastPort.notifyConnect("session server");
//		}

	}
	protected void addListenersToClientPort(DuplexClientInputPort<ByteBuffer> aPort) {
		aPort.addConnectionListener(this);
		aPort.addSendListener(this);	
		
	}
	@Override
	public void left(SessionParticipantDescription sessionClientDescription) {
		Tracer.info("Received left message : " + sessionClientDescription );
		String aLeaver = sessionClientDescription.getName();
		SimplexClientInputPort clientInputPort =
//		nameToClientInputPort.remove(sessionClientDescription.getName());
		nameToClientInputPort.remove(aLeaver);
		sessionMemberNames.remove(sessionClientDescription.getName());

		if (clientInputPort != null) {			
			clientInputPort.disconnect();
			Tracer.info("Sending notification message to listeners");
//			variableServerClientPort.notifyDisconnect(sessionClientDescription.getName(), true, "Received Left Message from Session Server", null);
			variableServerClientPort.notifyDisconnect(aLeaver, true, "Received Left Message from Session Server", null);

		}		
	}
   
	protected abstract DuplexClientInputPort<ByteBuffer>  createClientInputPort(String aHost, String aServerId, String aServerName, String aClientName);
	

	@Override
	public void send(String remoteName, ByteBuffer message) {
		SimplexClientInputPort clientInputPort = getClientInputPort(remoteName);
		if (clientInputPort != null) {
			clientInputPort.send(message);
			Tracer.info(this, "Send message:" + message + " to " + remoteName + " using client port:" + clientInputPort);

		} else {
			Tracer.error("No client input port for" + remoteName);
		}
		
	}
	

	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sendId) {
		variableServerClientPort.notifyPortSend(aRemoteEnd, message, sendId);
		
	}
	// problem is that this methid iscalled wither when a connection initiated by this computer completes
	// or when one initiated by another computer completes
	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		SimplexClientInputPort<ByteBuffer> port = nameToClientInputPort.get(remoteEnd);
		if (port != null) {
			Tracer.info(this, "This computer has connected to:" + remoteEnd);
//			serversToBeHeardFrom.remove(remoteEnd);


		}	else {
			Tracer.info(this, "Remote end:" + remoteEnd + " has connected to this computer");
			return;
		}
		
		Tracer.info(this, "Connected to server " + remoteEnd);
		variableServerClientPort.notifyConnect(remoteEnd, null);	
	}

	
	

	@Override
	public void disconnected(String remoteEnd,
			boolean explicitDisconnection, String systemMessage, ConnectionType aConnectionType) {
		Tracer.info(this, "Received disconnect  message from " + remoteEnd + 
				" explicit " + explicitDisconnection + " message " +  systemMessage);

		SimplexClientInputPort clientPort = nameToClientInputPort.remove(remoteEnd);
//		nameToNotifiedClientInputPort.remove(remoteEnd);
//		nameToNotifiedClientInputPort.put(remoteEnd, clientPort);

		Tracer.info(this, remoteEnd + " removed from  brodcasting port");
		if (clientPort != null) { // maybe left message arrived first
//			clientPort.disconnect();
			Tracer.info(this, "Sending notification message to listeners");
			variableServerClientPort.notifyDisconnect(remoteEnd, explicitDisconnection, systemMessage, null);
		}
		
		
		
	}

	
	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		nameToClientInputPort.remove(remoteEnd);
//		serversToBeHeardFrom.remove(remoteEnd);
		Tracer.info(this, remoteEnd + " removed from  brodcasting port");
		variableServerClientPort.notifyConnectFailure(remoteEnd, message, null);
//		maybeNotifyAggregateConnectOrDisconnect();

		
	}


	@Override
	public void closeAllConnections() {
//		sessionServerProxy.leave(sessionName, sessionClientDescription);
//		Collection<SimplexClientInputPort<ByteBuffer>> clientInputPorts = nameToNotifiedClientInputPort.values();
		Collection<SimplexClientInputPort<ByteBuffer>> clientInputPorts = nameToClientInputPort.values();

		for (SimplexClientInputPort<ByteBuffer> clientInputPort:clientInputPorts) {
			clientInputPort.disconnect();
		}		
	}

	@Override
	public Set<String> getRemoteEndPoints() {
//		Set<String> keySet = nameToNotifiedClientInputPort.keySet();
		Set<String> keySet = nameToClientInputPort.keySet();

		Set<String> retVal = new HashSet();
		for (String name: keySet) {
			SimplexClientInputPort port = nameToClientInputPort.get(name);
			if (port.isConnected(name))
			retVal.add(name);			
		}
		
		return retVal;		
	}	
	Set<String> allButMe(Set<String> aSet) {
		String myName = variableServerClientPort.getLocalName();
		if (!aSet.contains(myName))
			return aSet;
		Set<String> retVal = new HashSet();
		for (String name : aSet) {
		
			if (!name.equals(myName)) 

				retVal.add(name);
		}
		return retVal;
	}

	public List<SessionParticipantDescription> getServers() {
		return servers;
	}
	public List<SessionParticipantDescription> getMembers() {
		return members;
	}
	public List<SessionParticipantDescription> getClients() {
		return clients;
	}
	public SessionParticipantDescription getServer(String aName) {
		return ASession.getParticipant(servers, aName);
	}
	public SessionParticipantDescription getClient(String aName) {
		return ASession.getParticipant(clients, aName);
	}
	public SessionParticipantDescription getMember(String aName) {
		return ASession.getParticipant(members, aName);
	}
	public Set<String> getClientConnections() {
		return allButMe(sessionClientNames);
	}
//	@Override
	public Set<String> getServerConnections() {
		return allButMe(sessionServerNames);
	}
//	@Override
	public Set<String> getMemberConnections() {
		return allButMe(sessionMemberNames);
	}
	

}
