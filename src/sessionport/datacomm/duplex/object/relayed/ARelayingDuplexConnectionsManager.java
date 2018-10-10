package sessionport.datacomm.duplex.object.relayed;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import port.ParticipantChoice;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.sessionserver.AJoinInfo;
import port.sessionserver.ASession;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import port.sessionserver.relay.RelayerSupportingSessionServer;
import sessionport.datacomm.duplex.DuplexSessionPort;
import util.trace.Tracer;
import util.trace.port.AConnectionEvent;
import util.trace.port.ConnectedToSessionServer;
import util.trace.port.ConnectiontEventBus;

public class ARelayingDuplexConnectionsManager implements
		RelayingDuplexConnectionsManager {
	protected Set<String> sessionParticipantNames = new HashSet();
	// replacing session participant names with message receiver namesto take
	// into account that clients cannot send messages to other clients
	// keeping the former around
	protected Set<String> messageReceiverNames = new HashSet();
	protected Set<String> notifiedParticipantNames = new HashSet(); // messageReceived adds to sessionparticipant but not notified

	protected Set<String> sessionClientNames = new HashSet();
	protected Set<String> sessionServerNames = new HashSet();
	protected Set<String> sessionMemberNames = new HashSet();
	protected List<SessionParticipantDescription> servers;
	protected List<SessionParticipantDescription> clients;
	protected List<SessionParticipantDescription> members;



	// DuplexRPCClientInputPort sessionsServerInputPort;
	protected RelayerSupportingSessionServer logicalSessionsServerProxy;
	// protected Map<String, RelayerSupportingSessionsServer>
	// physicalServerNameToProxy = new HashMap();
	protected Relayer relayerProxy;
	boolean connectedToRelayer;;

	protected DuplexSessionPort<Object> duplexObjectSessionPort;
	String sessionsServerName;
	protected DuplexRPCClientInputPort sessionServerClientPort;

	protected ServerPortDescription sessionClientDescription;
//	protected ServerPortDescription sessionClientDescription;

	protected DuplexRPCClientInputPort relayerClientInputPort;
	protected String sessionName;
	protected ParticipantChoice joinChoice;

	public ARelayingDuplexConnectionsManager(
			DuplexSessionPort<Object> aDuplexSessionPort,
			ParticipantChoice aJoinChoice) {
		duplexObjectSessionPort = aDuplexSessionPort;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this,
				duplexObjectSessionPort, false));
		joinChoice = aJoinChoice;
		Tracer.setKeywordPrintStatus(this, true);
		// duplexBufferSessionPort.addReceiveListener(this);
	}

	protected boolean logicalConnectionNotificationSent = false;
	protected boolean initialJoin = true;
	
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
	@Override
	public void joined(SessionParticipantDescription sessionClientDescription) {
		Tracer.info(this, "User " + sessionClientDescription
				+ " has joined session");
		String joinerName = sessionClientDescription.getName();
		if (joinerName.equals(duplexObjectSessionPort.getLocalName())) {
			// connect message sent when call made
			// duplexObjectSessionPort.notifyConnect(joinerName);
			// no need to inform at this point because join notification already
			// sent earlier
			return;
		}
//		if (!sessionParticipantNames.contains(joinerName)) // notify people
//			return;
		
		// session participant names includes now even clients. Thee are the names of remote end points.
		// if the join choice is client only, then the process should not be able to talk to another client.
		// so in that case participants or at least remote end points should not have other clients
		// actually we need servers to not talk to other servers for replicated semantics, they should make calls only on members and clients
		// or only clients
		//let us make the semantics more flexible and allow them to make calls on non servers only
		ParticipantChoice aRemoteChoice = sessionClientDescription.getParticipantChoice();
		
		if (!((joinChoice == aRemoteChoice) && 
				(joinChoice == ParticipantChoice.CLIENT_ONLY ||  // clients do not talk to other clients
				joinChoice == ParticipantChoice.SERVER_ONLY) ))// servers do no talk to other servers  			
			messageReceiverNames.add(joinerName);
		sessionParticipantNames.add(joinerName); // set, we do not have to worry about duplicates
		switch (sessionClientDescription.getParticipantChoice()) {
		case SERVER_ONLY:		
			sessionServerNames.add(joinerName);
			if (!servers.contains(sessionClientDescription))
				servers.add(sessionClientDescription);
			break;
		case CLIENT_ONLY:
			sessionClientNames.add(joinerName); // set
			if (!clients.contains(sessionClientDescription))
				clients.add(sessionClientDescription);
			break;
		case MEMBER:
			sessionMemberNames.add(joinerName); // set
			if (!members.contains(sessionClientDescription))
				members.add(sessionClientDescription);
			break;
		}
//		ConnectionType connectType = toPeerConnectionType(joinChoice);
		// unnecessary switch again, but this may be more modular
		ConnectionType connectType = toPeerConnectionType(sessionClientDescription.getParticipantChoice());
		// if (joinChoice == ParticipantChoice.SERVER_ONLY)
		// connectType = ConnectionType.CLIENT_TO_SESSION_SERVER; // otherwise
		// joined will not be called
		// else if (joinChoice == ParticipantChoice.SERVER_ONLY)
		// connectType = ConnectionType.SERVER_TO_SESSION_SERVER;
		// if (duplexObjectSessionPort.getLocalName().equals(joinerName))
		// return;
		
		if (!notifiedParticipantNames.contains(joinerName))	{		
		    duplexObjectSessionPort.notifyConnect(joinerName, connectType);
		    notifiedParticipantNames.add(joinerName);
		}
		// can make it more efficient by distinguishing initial join with
		// notification, thru global var perhaps
		if (!initialJoin && !logicalConnectionNotificationSent
				&& duplexObjectSessionPort.getLogicalRemoteEndPoint() != null) { // a
																					// replicated
																					// port
			duplexObjectSessionPort.notifyConnect(
					duplexObjectSessionPort.getLogicalRemoteEndPoint(),
					ConnectionType.TO_LOGICAL_SERVER);
		}
	}
// should be called session connection type
	public static ConnectionType toPeerConnectionType(
			ParticipantChoice aParticipantChoice) {
		switch (aParticipantChoice) {
		case MEMBER:
			return ConnectionType.MEMBER_TO_SESSION;

		case SERVER_ONLY:
//			return ConnectionType.CLIENT_TO_SESSION_SERVER;
			return ConnectionType.SERVER_TO_SESSION;


		case CLIENT_ONLY:
//			return ConnectionType.SERVER_TO_SESSION_SERVER;
			return ConnectionType.CLIENT_TO_SESSION;

		}
		return null;
	}

	@Override
	public void left(SessionParticipantDescription sessionClientDescription) {
		Tracer.info(this, "Peer " + sessionClientDescription
				+ " has left session");
		ConnectionType connectType = toPeerConnectionType(joinChoice);

		sessionParticipantNames.remove(sessionClientDescription.getName());
		messageReceiverNames.remove(sessionClientDescription.getName());
		duplexObjectSessionPort.notifyDisconnect(
				sessionClientDescription.getName(), true, "", connectType);
	}

	@Override
	public DuplexRPCInputPort getSessionsServerInputPort() {
		return sessionServerClientPort;
	}

	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexObjectSessionPort.notifyPortSend(aRemoteEnd, message, sendId);

	}

	@Override
	public void closeAllConnections() {
		Tracer.info(this, "Closing all connections");
		logicalSessionsServerProxy.leave(sessionName, sessionClientDescription);
		sessionServerClientPort.disconnect();

	}
//	@Override
//	public Set<String> getRemoteEndPoints() {
////		if (sessionClientDescription == null)
////			return getAllButMe(sessionParticipants);
//		switch (joinChoice) {
//		case CLIENT_ONLY:
//			// members are not servers
//			return getRemoteServers();
//		case SERVER_ONLY:
//			return addToSet1(getRemoteMembers(), getRemoteClients());
//		case MEMBER:
//			return getRemoteMembers(); // this means cannot be member for fault tolerance?			
//		}
//	}
	
	// should we have a separate method to return all session particpant names
	@Override
	public Set<String> getRemoteEndPoints() {
//		return getAllButMe(sessionParticipantNames);
		return getAllButMe(messageReceiverNames);
	}

//	@Override
//	public Set<String> getRemoteEndPoints() {
//		Set<String> retVal = new HashSet();
//		for (String name : sessionParticipants) {
//			if (sessionClientDescription == null
//					|| !(name.equals(sessionClientDescription.getName()))) // if
//																			// no
//																			// session
//																			// client
//																			// description,
//																			// then
//																			// self
//																			// is
//																			// not
//																			// in
//																			// list
//
//				retVal.add(name);
//		}
//		return retVal;
//	}
	Set<String> getAllButMe(Set<String> aSet) {
		Set<String> retVal = new HashSet();
		for (String name : aSet) {
		
			if (sessionClientDescription == null
			// if there is no description then this participant is not in list					
					|| !(name.equals(sessionClientDescription.getName()))) 

				retVal.add(name);
		}
		return retVal;
	}
	public Set<String> getClientConnections() {
		return getRemoteClients();
	}
//	@Override
	public Set<String> getServerConnections() {
		return getRemoteServers();
	}
//	@Override
	public Set<String> getMemberConnections() {
		return getRemoteMembers();
	}
	
	@Override
	public Set<String> getRemoteClients() {
		return getAllButMe(sessionClientNames);
	}
	public Set<String> getRemoteServers() {
		return getAllButMe(sessionServerNames);
	}
	public Set<String> getRemoteMembers() {
		return getAllButMe(sessionMemberNames);
	}
	public Set<String> addToSet1(Set<String> set1, Set<String> set2) {
		// changing set1 but that is ok
		set1.addAll(set2);
		return set1;
	}

	// must make sure that this is mutually exlusive with late comer messages
	// this synchronized may cause deadlock
	// the control message a client sends can be receibed before the latecomer messges are received
	// as latecomer server notifies observers before returning value
	// do not know how to fix this problem if we are using rpc
	// this is why we need the server message manager to keeo track of late comer status
	// could buffer messages received until latecomer has been updated
	// we do have the information
	// we do not need to make this methiod synchronized in that case
	@Override
	public synchronized void messageReceived(String remoteClientName,
			Object message) {
//		System.out.println ("Message received in Arelating duplex manager");
		

		if (message instanceof MessageWithSource) {
//			System.out.println ("Message received in Arelating duplex manager");
			Tracer.info(this, " Received relayed message: " + message); 
			MessageWithSource messageWithSource = (MessageWithSource) message;
			String clientName = messageWithSource.getSource();
			// should message receiver names be updated at this point? assuming not as we do not know
			// but if join choive is not client then I suppose this is safe
			// looks like this code is for sever only so this seems right
			if (joinChoice != ParticipantChoice.CLIENT_ONLY)
				messageReceiverNames.add(clientName);
			// if it is a client-client situation
			sessionParticipantNames.add(clientName); // if server_only then has no
											// notification of others, maybe
											// this is bad, server should get
											// all clients explicitly so it can
											// keep track of who has left
											// some of the actions are not taken that should be of join
//			System.out.println ("Message received in Arelating duplex manager");

			duplexObjectSessionPort.notifyPortReceive(
					messageWithSource.getSource(),
					messageWithSource.getMessage());

		} else { // this must be latecomer message
//			System.out.println ("Non source message");

			Tracer.info(this, " Received message: " + message);
			duplexObjectSessionPort
					.notifyPortReceive(remoteClientName, message);
		}
	}

	// we do not make this synchronous so that sends can happen with receives
	@Override
	public void send(String remoteEnd, Object message) {
		// sessionsServerProxy.relay(sessionName, remoteEnd, message);
		// relayerProxy.relay(sessionName, remoteEnd, new
		// AMessageWithSource(duplexObjectSessionPort.getLocalName(), message));
		// relayerProxy.relay(ARelayer.localToUniqueRelayerName(sessionName,
		// remoteEnd), new
		// AMessageWithSource(duplexObjectSessionPort.getLocalName(), message));
		MessageWithSource messageWithSource = new AMessageWithSource(
				duplexObjectSessionPort.getLocalName(), message);
		// relayerProxy.relay(sessionName, remoteEnd, new
		// AMessageWithSource(duplexObjectSessionPort.getLocalName(), message));
		Tracer.info(this, "Relaying message: " + messageWithSource);
//		relayerProxy.relay(sessionName, remoteEnd, messageWithSource);
		relayerProxy.relay( remoteEnd, messageWithSource);


	}

	protected void getAndConnectToRelayer() {
		try {
			// sessionServerProxy = (RelayingSessionsServer)
			// UniRPCProxyGenerator.generateUniRPCProxy(sessionsServerInputPort,
			// null,
			// RelayingSessionsServer.class, null);
			// List<ServerPortDescription> currentMembers =
			// sessionServerProxy.join(aSessionName, aSessionClientDescription,
			// this);
			ServerPortDescription relayerDescription = logicalSessionsServerProxy
					.getRelayerDescripton(sessionName);
			if (relayerDescription == null) {
//				Tracer.error("No relayer registered with session server. Will not be able to send messages");
				relayerClientInputPort = sessionServerClientPort; // already regiustered as send and receive listener to this port
				Tracer.info(this, "No separate relayer port, using session server as relayer");
				processRelayerConnect(sessionServerClientPort.getLogicalRemoteEndPoint(), ConnectionType.TO_SERVER);
			} else {
			relayerClientInputPort = DuplexRPCInputPortSelector
					.createDuplexRPCClientInputPort(
							relayerDescription.getHost(),
							relayerDescription.getID(),
							relayerDescription.getName(),
							// ARelayer.localToUniqueRelayerName (sessionName,
							// duplexObjectSessionPort.getLocalName()));
							duplexObjectSessionPort.getLocalName());
			// relayerName = relayerDescription.getName();
			Tracer.info(this, "Created relayer client port "
					+ relayerClientInputPort + " for " + relayerDescription);
			
			Tracer.info(this, "Registering as receive and send listener for "
					+ relayerClientInputPort);
			ConnectiontEventBus.newEvent(new AConnectionEvent(this,
					relayerClientInputPort, true));
			relayerClientInputPort.addConnectionListener(this);
			relayerClientInputPort.addReceiveListener(this);
			relayerClientInputPort.addSendListener(this);
			// setRelayerProxy();
			// relayerProxy = (Relayer)
			// UniRPCProxyGenerator.generateUniRPCProxy(relayerClientInputPort,
			// null,
			// Relayer.class, null);
			
			relayerClientInputPort.connect();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		// List<ServerPortDescription> currentMembers =
		// sessionServerProxy.join(sessionName, sessionClientDescription, this);
	}
// done twice
	protected void setRelayerProxy() {
		Tracer.info(this, " Created proxy for relayer");
		relayerProxy = (Relayer) DirectedRPCProxyGenerator.generateRPCProxy(
				relayerClientInputPort, null, getRelayerType(), null);
	}

	protected Class getRelayerType() {
		return ARelayer.class;
	}

	@Override
	public void joinSessionsServer(
			DuplexRPCClientInputPort aSessionsServerClientPort,
			ServerPortDescription aSessionClientDescription, String aSessionName) {

		sessionName = aSessionName;
		sessionServerClientPort = aSessionsServerClientPort;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this,
				aSessionsServerClientPort, true));
		// session port doing so, and will lead to concurrent modification
		// sessionServerClientPort.addConnectionListener(this);
		sessionServerClientPort.addReceiveListener(this);
		sessionServerClientPort.addSendListener(this);
		// sessionsServerClientPort.addConnectionListener(this);
		sessionClientDescription = aSessionClientDescription;
		setSessionServerProxy();
		// getAndConnectToRelayer();
		// setRelayerProxy();
		// Thread thread = new Thread (this);
		// thread.setName("Relayer Connecting Thread");
		// Tracer.info(this, " Created thread " + thread.getName() +
		// " for making synchronous getRelayer call to session server");
		// thread.start();
		maybeAsyncSetUpRelayer();
		// setSessionServerProxy();
		// getAndConnectToRelayer();
		// setRelayerProxy();
		// joinSessionServer();

	}

	protected void maybeAsyncSetUpRelayer() {
		Thread thread = new Thread(this);
		thread.setName("Relayer Connecting Thread");
		Tracer.info(this, " Created thread " + thread.getName()
				+ " for making synchronous getRelayer call to session server");
		thread.start();
	}

	protected void setUpRelayer() {

		getAndConnectToRelayer();
		setRelayerProxy();
		// joinSessionServer();

	}

	// too much state inherited and shared with threads, so using one
	// run to do two different things
	// why make this synchronized. I suppose no new messages should be processed
	// it seems wrong to make a whole thread synchronized
	// no message will be sent to the world while synchronizing so we cannot expect
	// a messsage
//	public synchronized void run() {
	public  void run() {

		if (!connectedToRelayer) {
			// reversing optimistically the order of operations
			connectedToRelayer = true;
			setUpRelayer();
		} else {
			joinSessionServer();
		}

	}

	// @Override
	// public void connected(String aRemoteEndName) {
	// // joinSessionsServer(sessionsServerClientPort, sessionClientDescription,
	// sessionName);
	//
	// // joinSessionServer();
	//
	//
	// }

	protected void setSessionServerProxy() {
		if (logicalSessionsServerProxy == null) {
			Tracer.info(this, "Creating proxy for session server");
			// logicalSessionsServerProxy = (RelayerSupportingSessionsServer)
			// StaticRPCProxyGenerator.
			// generateRPCProxy(sessionsServerClientPort, null,
			// getSessionsServerInterface(),
			// sessionsServerClientPort.getLogicalRemoteEndPoint());
			logicalSessionsServerProxy = (RelayerSupportingSessionServer) DirectedRPCProxyGenerator
					.generateRPCProxy(sessionServerClientPort,
							sessionServerClientPort.getLogicalRemoteEndPoint(),
							getSessionsServerType(), null);
		}
		// physicalServerNameToProxy.put(aPhysicalName,(RelayerSupportingSessionsServer)
		// StaticRPCProxyGenerator.
		// generateRPCProxy(sessionsServerClientPort, aPhysicalName,
		// getSessionsServerInterface(), null));
		// physicalServerNameToProxy.put(arg0, arg1)

	}

	protected Class getSessionsServerType() {
		return ARelayerSupportingSessionServer.class;
	}

	protected void joinSessionServer() {
		List<SessionParticipantDescription> currentMembers = null;
		JoinInfo joinInfo = null;

		switch (joinChoice) {
		case MEMBER:
		case SYMMETRIC_JOIN: {
			joinInfo = logicalSessionsServerProxy
					.join(sessionName, sessionClientDescription, this);
//			currentMembers = AJoinInfo
//					.getMembersClientsAndServers(logicalSessionsServerProxy
//							.join(sessionName, sessionClientDescription, this));
			break;
		}
		case SERVER_ONLY: {
			joinInfo = logicalSessionsServerProxy
					.joinAsServer(sessionName,
							sessionClientDescription, this);
//			currentMembers = AJoinInfo
//					.getMembersClientsAndServers(logicalSessionsServerProxy
//							.joinAsServer(sessionName,
//									sessionClientDescription, this));
			break;
		}
		case CLIENT_ONLY: {
			// note needed as no ports are registered
			// sessionClientDescription.setID(null);
			joinInfo = logicalSessionsServerProxy
					.joinAsClient(sessionName,
							sessionClientDescription, this);
//			currentMembers = AJoinInfo
//					.getMembersClientsAndServers(logicalSessionsServerProxy
//							.joinAsClient(sessionName,
//									sessionClientDescription, this));
			break;
		}
		default: {
			System.out.println("Code should not reach here");
		}
		}
		currentMembers = AJoinInfo
				.getMembersClientsAndServers(joinInfo);
		servers = joinInfo.getServers();
		clients = joinInfo.getClients();
		members = joinInfo.getMembers();
		Tracer.info(this, "Current members:" + members);
				duplexObjectSessionPort.notifyConnect(
				duplexObjectSessionPort.getLocalName(), 
				// null));
				ARelayingDuplexConnectionsManager.toPeerConnectionType(duplexObjectSessionPort.getParticipantChoice()));

		// if (joinChoice == ParticipantChoice.JOIN_AND_OBSERVE)
		// currentMembers = logicalSessionsServerProxy.join(sessionName,
		// sessionClientDescription, this);
		// else if (joinChoice == ParticipantChoice.OBSERVE_ONLY)
		// currentMembers = logicalSessionsServerProxy.observe (sessionName,
		// this);

		// List<ServerPortDescription> currentMembers =
		// logicalSessionsServerProxy.join(sessionName,
		// sessionClientDescription, this);

		processCurrentMembers(currentMembers);
	}

	protected void processCurrentMembers(
			List<SessionParticipantDescription> aCurrentMembers) {
		Tracer.info(this, "processing current members" + aCurrentMembers);
		if (aCurrentMembers == null)
			return;
		for (int i = 0; i < aCurrentMembers.size(); i++)
			joined(aCurrentMembers.get(i)); // members, clients and servers
											// notified here
	}
	
	protected void processRelayerConnect (String remoteEndName, ConnectionType aConnectionType) {
		// connected to both relayer and session server, so can join session
					// manager
					// Runnable sessionServerRunnable =
					// new ASessionServerJoiningRunnable(
					// sessionClientDescription,sessionName, this,
					// logicalSessionsServerProxy);
					//
					// Thread thread = new Thread (sessionServerRunnable);
					ConnectedToSessionServer.newCase(this, remoteEndName, joinChoice);
					Thread thread = new Thread(this);
					thread.setName("An Async Session Joining Thread");
					Tracer.info(
							this,
							" Created thread "
									+ thread.getName()
									+ " for making synchronou join call to session server/relayer");
					thread.start();
	}

	@Override
	public void connected(String remoteEndName, ConnectionType aConnectionType) {
		if (remoteEndName.equals(relayerClientInputPort // should this not be duplexObjectSessionPort, I guess not we wait for relayer port to be opened
				.getLogicalRemoteEndPoint())) {
			processRelayerConnect(remoteEndName, aConnectionType);
//			// connected to both relayer and session server, so can join session
//			// manager
//			// Runnable sessionServerRunnable =
//			// new ASessionServerJoiningRunnable(
//			// sessionClientDescription,sessionName, this,
//			// logicalSessionsServerProxy);
//			//
//			// Thread thread = new Thread (sessionServerRunnable);
//			ConnectedToSessionServer.newCase(this, remoteEndName, joinChoice);
//			Thread thread = new Thread(this);
//			thread.setName("An Async Session Joining Thread");
//			Tracer.info(
//					this,
//					" Created thread "
//							+ thread.getName()
//							+ " for making synchronou join call to session server/relayer");
//			thread.start();
			
		}

	}

	@Override
	public void notConnected(String remoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void disconnected(String remoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub

	}

	@Override
	public void newState(Serializable newState) {
		// TODO Auto-generated method stub
		
	}

	// @Override
	// public void notConnected(String aRemoteEndName, String anExplanation) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void disconnected(String aRemoteEndName,
	// boolean anExplicitDsconnection, String anExplanation) {
	// // TODO Auto-generated method stub
	//
	// }

	// @Override
	// public void connected(String remoteEnd) {
	// if (remoteEnd.equals(sessionsServerName))
	// connectedToSessionsServer();
	// else if (remoteEnd.equals(sessionName))
	// connectedToRelayer();
	//
	//
	// }

	// @Override
	// public synchronized void connected(String remoteEnd) {
	// notify();
	// // if (remoteEnd.equals(sessionsServerName))
	// // connectedToSessionsServer();
	// // else if (remoteEnd.equals(sessionName))
	// // connectedToRelayer();
	//
	//
	// }

	// @Override
	// public void disconnected(String remoteEndName,
	// boolean explicitDsconnection, String systemMessage) {
	// // TODO Auto-generated method stub
	//
	// }
	//
	// @Override
	// public void notConnected(String remoteEnd, String message) {
	// // TODO Auto-generated method stub
	//
	// }

}
