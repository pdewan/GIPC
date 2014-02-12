package sessionport.datacomm.duplex.buffer.fullp2p;

import inputport.ConnectionType;
import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import port.ParticipantChoice;
import port.sessionserver.AJoinInfo;
import port.sessionserver.ASession;
import port.sessionserver.ASessionServer;
import port.sessionserver.JoinInfo;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionServer;
import staticsessionport.datacomm.duplex.buffer.fullp2p.ABufferStaticSessionPortConnectionsManagerFullP2P;
import util.trace.Tracer;
import variableserverport.datacomm.duplex.DuplexVariableServerClientPort;


public class ASessionBasedFP2PBufferConnectionsManager extends ABufferStaticSessionPortConnectionsManagerFullP2P 
													implements SessionBasedFP2PBufferConnectionsManager {
	DuplexRPCClientInputPort sessionsServerInputPort;
	SessionServer sessionServerProxy;
	protected boolean hasJoined; 

	String sessionName;
//	List<SessionParticipantDescription> servers;
//	List<SessionParticipantDescription> clients;
//	List<SessionParticipantDescription> members;
//	List<SessionParticipantDescription> participants;
	
	
//	ParticipantChoice joinChoice;
	
	public ASessionBasedFP2PBufferConnectionsManager(
			DuplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aJoinChoice
			) {
		super(aDuplexBufferSessionPort, aJoinChoice);
//		joinChoice = aJoinChoice;

	}
//	public List<SessionParticipantDescription> getServers() {
//		return servers;
//	}
//	public List<SessionParticipantDescription> getMembers() {
//		return members;
//	}
//	public List<SessionParticipantDescription> getClients() {
//		return clients;
//	}
//	public SessionParticipantDescription getServer(String aName) {
//		return ASession.getParticipant(servers, aName);
//	}
//	public SessionParticipantDescription getClient(String aName) {
//		return ASession.getParticipant(clients, aName);
//	}
//	public SessionParticipantDescription getMember(String aName) {
//		return ASession.getParticipant(members, aName);
//	}
	
	
	
	//maybe there can be a flag that indicates if this port should itself join or not
	public synchronized void joinSessionsServer(DuplexRPCClientInputPort aSessionServerClientPort, String aSessionName) {
		sessionsServerInputPort = aSessionServerClientPort;
		JoinInfo joinInfo = null;
		try {
			sessionName = aSessionName;
			String sessionServerName = sessionsServerInputPort.getLogicalRemoteEndPoint();
			sessionServerProxy = (SessionServer) DirectedRPCProxyGenerator.generateRPCProxy(sessionsServerInputPort, 
					sessionServerName,
					ASessionServer.class, sessionServerName);
//			List<SessionParticipantDescription> participants = new ArrayList();
		
			switch (joinChoice) {
			case MEMBER:
			case SYMMETRIC_JOIN:
			{
				joinInfo = sessionServerProxy.join(aSessionName, serverPortDescription, this);
//				currentMembers = AJoinInfo.getMembersClientsAndServers(sessionServerProxy.join(aSessionName, serverPortDescription, this));
//				participants = AJoinInfo.getMembersClientsAndServers(joinInfo);
//				servers = joinInfo.getServers();

				
				break;
			}
			case SERVER_ONLY: {
			    joinInfo = sessionServerProxy.joinAsServer(aSessionName, serverPortDescription, this);
//				servers = joinInfo.getServers();
				break;
			}
			case CLIENT_ONLY: {
				if (serverPortDescription != null)
					serverPortDescription.setID(null); // making your ID null regradless of whether you have one or not
				joinInfo = sessionServerProxy.joinAsClient(aSessionName, null, this);
//				participants = AJoinInfo.getMembersClientsAndServers(joinInfo);
//				currentMembers = AJoinInfo.getMembersClientsAndServers( sessionServerProxy.joinAsClient(aSessionName, null, this));
				break;
			}
			}
			servers = joinInfo.getServers();
			clients = joinInfo.getClients();
			members = joinInfo.getMembers();
			participants = AJoinInfo.getMembersClientsAndServers(joinInfo);

			// race condition with peer connect. let us store if joined or not
			connected();
			
//			
//			if (joinChoice == ParticipantChoice.JOIN_AND_OBSERVE ||) {
//				currentMembers = sessionServerProxy.join(aSessionName, serverPortDescription, this);
//			} else if (joinChoice == ParticipantChoice.JOIN_ONLY) {
//				sessionServerProxy.join(aSessionName, serverPortDescription);
//			} else if (joinChoice == ParticipantChoice.OBSERVE_ONLY) {
//				sessionServerProxy.observe(aSessionName, this);
//			}
//			List<ServerPortDescription> currentMembers = sessionServerProxy.join(aSessionName, serverPortDescription, this);
			
			for (int i=0; i<participants.size(); i++)
				joined(participants.get(i));
			hasJoined  = true;

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}	
//	@Override
//	public void connected(String remoteEnd) {
//		super.connected(remoteEnd);
//		SimplexClientInputPort<ByteBuffer> port = nameToClientInputPort.get(remoteEnd);
//		if (port != null) {
//			Tracer.info(this, "This computer has connected to:" + remoteEnd);
////			serversToBeHeardFrom.remove(remoteEnd);
//
//
//		}	else {
//			Tracer.info(this, "Remote end:" + remoteEnd + " has connected to this computer");
//			return;
//		}
//		
//		Tracer.info(this, "Connected to server " + remoteEnd);
//		bufferBroadcastPort.notifyConnect(remoteEnd);	
//	}


	@Override
	public void closeAllConnections() {
		sessionServerProxy.leave(sessionName, serverPortDescription);
		super.closeAllConnections();
		
	}
	protected boolean isServer(String aRemoteEnd) {
		return ASession.contains(servers, aRemoteEnd);
	}
	
	@Override
	protected boolean doublyConnected (String aRemoteEnd) {
		boolean retVal = isServer(aRemoteEnd);
		if (retVal) // this means we have joined because sever would not open connectiom to us
			return false;
		if (!hasJoined && joinChoice != ParticipantChoice.SERVER_ONLY) // this means someone else has connected to us before we have joined so we are doubly connected
			return true;
		return super.doublyConnected(aRemoteEnd);
//		return retVal?false: super.doublyConnected(aRemoteEnd);
//			return retVal;
//		return super.doublyConnected(aRemoteEnd);
	}
	@Override
	public void joined(SessionParticipantDescription sessionClientDescription) {
//		Tracer.info(this, "Creating client input port for:" + sessionClientDescription);
		// perhaps session manager should not tell server about client in p2p mode
		// but session server does not know about p2p
		// this function is also called by the joining routine for the initial participants hence the check before add
		// should  have a routine that join calls
//		switch (sessionClientDescription.getParticipantChoice()) {
//		case MEMBER:
//		case SYMMETRIC_JOIN:
//			if (!members.contains(sessionClientDescription))
//			members.add(sessionClientDescription);
//			break;
//		case SERVER_ONLY:
//			if (!servers.contains(sessionClientDescription))
//			servers.add(sessionClientDescription);
//			break;
//		case CLIENT_ONLY:
//			if (!clients.contains(sessionClientDescription))
//			clients.add(sessionClientDescription);
//			break;
//		}
		
//		if (joinChoice != ParticipantChoice.SERVER_ONLY) // do not open connection if server

		super.joined(sessionClientDescription);
//		else {
//			servers.add(sessionClientDescription);
//		}
//		variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName(), toMyConnectionType(joinChoice));

//		if (sessionClientDescription.getName().equals(variableServerClientPort.getLocalName())) {
//			switch (joinChoice) {
//			case MEMBER: 
//				variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName(), ConnectionType.MEMBER_TO_SESSION_SERVER);
//				break;
//			case SERVER_ONLY:
//				variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName(), ConnectionType.SERVER_TO_SESSION_SERVER);
//				break;
//			
//		case CLIENT_ONLY:
//			variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName(), ConnectionType.CLIENT_TO_SESSION_SERVER);
//			break;		
//		}
//		}

	}
	
	protected void connected() {
		variableServerClientPort.notifyConnect(variableServerClientPort.getLocalName(), toMyConnectionType(joinChoice));
	}
	
	public static ConnectionType toMyConnectionType (ParticipantChoice aParticipantChoice) {
		switch (aParticipantChoice) {
		case MEMBER: 
			return ConnectionType.MEMBER_TO_SESSION;
			
		case SERVER_ONLY:
			return ConnectionType.SERVER_TO_SESSION;
		
		case CLIENT_ONLY:
			return ConnectionType.CLIENT_TO_SESSION;
	}
		return null;
	}
	// no notify now as we will wait for user to join session manager
//	@Override
//	protected void maybeNotifyOnServerPortCreation() {
//
//	}
	boolean logicalConnectionNotificationSent;
	@Override
	protected void doNotifyConnect(String aRemoteEnd, ConnectionType aConnectiontype) {
		
		variableServerClientPort.notifyConnect(aRemoteEnd, aConnectiontype);
		String aLogicalName = variableServerClientPort.getLogicalRemoteEndPoint();
		if (aLogicalName != null && !logicalConnectionNotificationSent && !variableServerClientPort.getLogicalRemoteEndPoint().equals(aRemoteEnd)) {
			System.out.println("Connected to  a replicated port");
			// this should triger a control messgae in single response
			// but it creates issues for other listeners. Can one simply in a notifier the fact that
			// some connection has already been made. Or we can change single response
			// to make it less efficient
			// we do nee dto send to rpc return value receiver the logical address
			// so let us simply send this message a single time
			logicalConnectionNotificationSent = true;
			variableServerClientPort.notifyConnect(variableServerClientPort.getLogicalRemoteEndPoint(), ConnectionType.TO_LOGICAL_SERVER);
			
		}
	}
	
	@Override
	public void left(SessionParticipantDescription sessionClientDescription) {
		Tracer.info("Received left message : " + sessionClientDescription );
		SimplexClientInputPort clientInputPort =
		nameToClientInputPort.remove(sessionClientDescription.getName());
		if (clientInputPort != null) {			
			clientInputPort.disconnect();
			Tracer.info("Sending notification message to listeners");
			variableServerClientPort.notifyDisconnect(sessionClientDescription.getName(), true, "Received Left Message from Session Server", null);
		}		
	}



}
