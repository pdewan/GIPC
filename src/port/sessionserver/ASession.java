package port.sessionserver;

import inputport.ConnectionType;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import util.trace.Tracer;

public class ASession implements Session {
	Set<SessionObserver> clientObservers = new HashSet();
	Set<SessionObserver> serverObservers = new HashSet();
	
	Set<SessionObserver> observers = new HashSet(); //separate to avoid duplicates


	List<SessionParticipantDescription> servers = new ArrayList();
	List<SessionParticipantDescription> clients = new ArrayList();
	List<SessionParticipantDescription> members = new ArrayList();
	Serializable applicationDefinedState;
	@Override
	// allows same client to be registered twice which is incomaptible
	// with the relayer which keeps only one entry per client 
	// should rewrite in terms of the basic operations
	public void join(ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {		
//		joinAsServer (aSessionClientDescription, aSessionObserver);
		SessionParticipantDescription aSessionParticipantDescription = null;
		if (aSessionClientDescription instanceof SessionParticipantDescription)
			aSessionParticipantDescription = (SessionParticipantDescription) aSessionClientDescription;
		else
		
			aSessionParticipantDescription = new ASessionParticipantDescription(aSessionClientDescription, ParticipantChoice.MEMBER, null);

		notifyObserversJoin(aSessionParticipantDescription,  clientObservers);
		notifyObserversJoin(aSessionParticipantDescription,  observers);
		notifyObserversJoin(aSessionParticipantDescription,  serverObservers); // added this
		members.add(aSessionParticipantDescription);
		addObserver(aSessionObserver);
	}
	@Override
	public  void joinAsServer(ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {		

		
		
		// if we notify all, then the notification would take the participantChoice as
		// an argument
		// this is assymetric with the fact that we have join info returning everything
		// but in P2p port we are overloading joined and wonder it makes a difference
		
		
		SessionParticipantDescription aSessionParticipantDescription = new ASessionParticipantDescription(aSessionClientDescription, ParticipantChoice.SERVER_ONLY, null);
		if (aSessionClientDescription instanceof SessionParticipantDescription)
			aSessionParticipantDescription = (SessionParticipantDescription) aSessionClientDescription;
		else
		
			aSessionParticipantDescription = new ASessionParticipantDescription(aSessionClientDescription, ParticipantChoice.SERVER_ONLY, null);

		notifyObserversJoin(aSessionParticipantDescription,clientObservers);
		notifyObserversJoin(aSessionParticipantDescription, observers);
		
		servers.add(aSessionParticipantDescription);	
		addServerObserver(aSessionObserver);
	}
	 void notifyObserversJoin(SessionParticipantDescription aSessionClientDescription, 
			 Set<SessionObserver> observers) {		
		Tracer.info(this, "Session join by " +  aSessionClientDescription);		
		List<SessionObserver> disconnectedObservers = new ArrayList();
		for (SessionObserver sessionObserver:observers) {
			try {
				sessionObserver.joined(aSessionClientDescription);	
			} catch (Exception e) {
				e.printStackTrace();
				disconnectedObservers.add(sessionObserver);
			}
		}
		removeDisconnectedObservers(disconnectedObservers);
		
	}
	 void notifyObserversLeave(SessionParticipantDescription aSessionClientDescription,
			 Set<SessionObserver> observers) {		
		Tracer.info(this, "Session leave by " +  aSessionClientDescription);		
		List<SessionObserver> disconnectedObservers = new ArrayList();
		for (SessionObserver sessionObserver:observers) {
			try {
				sessionObserver.left((SessionParticipantDescription) aSessionClientDescription);	
			} catch (Exception e) {
				e.printStackTrace();
				disconnectedObservers.add(sessionObserver);
			}
		}
		removeDisconnectedObservers(disconnectedObservers);
		
	}
	@Override
	public void joinAsClient(ServerPortDescription aSessionClientDescription,
			SessionObserver aSessionObserver) {
		SessionParticipantDescription aSessionParticipantDescription;
		if (aSessionClientDescription == null) {
			aSessionParticipantDescription = new ASessionParticipantDescription(ParticipantChoice.CLIENT_ONLY);
		}
		else if (aSessionClientDescription instanceof SessionParticipantDescription)
			aSessionParticipantDescription = (SessionParticipantDescription) aSessionClientDescription;
		else
		
			aSessionParticipantDescription = new ASessionParticipantDescription(aSessionClientDescription, ParticipantChoice.CLIENT_ONLY, null);

		if (aSessionClientDescription != null) {
		notifyObserversJoin(aSessionParticipantDescription, observers);

		notifyObserversJoin(aSessionParticipantDescription,  serverObservers);
		}
		if (aSessionClientDescription != null)
		clients.add(aSessionParticipantDescription);	
		addClientObserver(aSessionObserver);
		
	}

	
	void addClientObserver(SessionObserver aSessionObserver) {
		if (aSessionObserver != null)
		clientObservers.add(aSessionObserver);

	}
	
	void addServerObserver(SessionObserver aSessionObserver) {
		if (aSessionObserver != null)
		serverObservers.add(aSessionObserver);

	}
	
	void addObserver(SessionObserver aSessionObserver) {
		if (aSessionObserver != null)
		observers.add(aSessionObserver);

	}

	@Override
	public List<SessionParticipantDescription> getMembers() {
		return members;
	}
	
	@Override
	public List<SessionParticipantDescription> getServers() {
		return servers;
	}

	@Override
	public void leave(ServerPortDescription aSessionClientDescription) {
		Tracer.info(this, "Session leave by " +  aSessionClientDescription);

		int clientIndex = -1;
		for (int i = 0; i < servers.size(); i++) {
			ServerPortDescription member = servers.get(i);
			if (member.equals(aSessionClientDescription)) {
				clientIndex = i;
				break;
			}				
		}
		if (clientIndex >= 0) {
			leaveServer(clientIndex);
			return;
		}
		for (int i = 0; i < members.size(); i++) {
			ServerPortDescription member = members.get(i);
			if (member.equals(aSessionClientDescription)) {
				clientIndex = i;
				break;
			}				
		}
		if (clientIndex >= 0) {
			leaveMember(clientIndex);
			return;
		}
		for (int i = 0; i < clients.size(); i++) {
			ServerPortDescription member = clients.get(i);
			if (member.equals(aSessionClientDescription)) {
				clientIndex = i;
				break;
			}				
		}
		if (clientIndex >= 0) {
			leaveClient(clientIndex);
			return;
		}
		if (clientIndex == -1) {
			Tracer.error("Illegal session client");		
			
		}
//		members.remove(clientIndex);
//		observers.remove(clientIndex);
//		for (SessionObserver sessionObserver:observers) {
//			sessionObserver.left(aSessionClientDescription);	
//		}	
//		leaveServer(clientIndex);
	}
	
	void leaveServer(int clientIndex) {
		if (clientIndex < 0)
			return;
		SessionParticipantDescription aSessionClientDescription = servers.get(clientIndex);
		servers.remove(clientIndex);
		// cannot simply assume that the client has also joined
		// when notification is sent will get some kind of message
//		observers.remove(clientIndex);
		notifyObserversLeave(aSessionClientDescription, clientObservers);
		notifyObserversLeave(aSessionClientDescription, observers);
//		List<SessionObserver> disconnectedObservers = new ArrayList();
//
//		for (SessionObserver sessionObserver:clientObservers) {
//			try {
//				sessionObserver.left(aSessionClientDescription);
//			} catch (Exception e) {
//				e.printStackTrace();
//				disconnectedObservers.add(sessionObserver);
//			}
//		}
//		
//		removeDisconnectedObservers(disconnectedObservers);
	}
	void leaveClient(int clientIndex) {
		if (clientIndex < 0)
			return;
		SessionParticipantDescription aSessionClientDescription = clients.get(clientIndex);
		clients.remove(clientIndex);
		// cannot simply assume that the client has also joined
		// when notification is sent will get some kind of message
//		observers.remove(clientIndex);
		notifyObserversLeave(aSessionClientDescription, serverObservers);
		notifyObserversLeave(aSessionClientDescription, observers);
//		List<SessionObserver> disconnectedObservers = new ArrayList();
//
//		for (SessionObserver sessionObserver:clientObservers) {
//			try {
//				sessionObserver.left(aSessionClientDescription);
//			} catch (Exception e) {
//				e.printStackTrace();
//				disconnectedObservers.add(sessionObserver);
//			}
//		}
//		
//		removeDisconnectedObservers(disconnectedObservers);
	}
	void leaveMember(int clientIndex) {
		if (clientIndex < 0)
			return;
		SessionParticipantDescription aSessionClientDescription = members.get(clientIndex);
		members.remove(clientIndex);
		// cannot simply assume that the client has also joined
		// when notification is sent will get some kind of message
//		observers.remove(clientIndex);
		notifyObserversLeave(aSessionClientDescription, serverObservers);
		notifyObserversLeave(aSessionClientDescription, clientObservers);

		notifyObserversLeave(aSessionClientDescription, observers);
//		List<SessionObserver> disconnectedObservers = new ArrayList();
//
//		for (SessionObserver sessionObserver:clientObservers) {
//			try {
//				sessionObserver.left(aSessionClientDescription);
//			} catch (Exception e) {
//				e.printStackTrace();
//				disconnectedObservers.add(sessionObserver);
//			}
//		}
//		
//		removeDisconnectedObservers(disconnectedObservers);
	}
	
	void removeDisconnectedObservers(List<SessionObserver> disconnectedObservers) {
		for (SessionObserver sessionObserver:disconnectedObservers) {
			clientObservers.remove(sessionObserver);
			//printing the observer will give toString() invocation error
			Tracer.info("Removed observer from observer list");
		}
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	
	int indexOf(String aName) {
		for (int i = 0; i < servers.size(); i++) {
			ServerPortDescription member = servers.get(i);
			if (member.getName().equals(aName)) {
				return i;
			}				
		}
		return -1;
	}
	
	public static int indexOf(List<SessionParticipantDescription> servers, String aName) {
		if (servers == null) return -1;
		for (int i = 0; i < servers.size(); i++) {
			SessionParticipantDescription member = servers.get(i);
			if (member.getName().equals(aName)) {
				return i;
			}				
		}
		return -1;
	}
	
	public static SessionParticipantDescription getParticipant(List<SessionParticipantDescription> servers, String aName) {
		int index = indexOf(servers, aName);
	   return (index < 0)? null:servers.get(index);
	}
	
	public static boolean contains (List<SessionParticipantDescription> servers, String aName) {
		return indexOf(servers, aName) > -1;
	}
	
	public ParticipantChoice getParticipantChoice(String aName) {
		int index = indexOf(members, aName );
		if (index >= 0) return ParticipantChoice.MEMBER;
		index = indexOf(servers, aName);
		if (index >= 0) return ParticipantChoice.SERVER_ONLY;
		// client may not have registered a serverportdescription
		return ParticipantChoice.CLIENT_ONLY;
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		int index = indexOf(remoteEndName);
		if (index >= 0) {
			leaveServer(index); // servers
		} else {
			index = indexOf(members, remoteEndName);
			if (index >= 0) {
				leave(members.get(index));
			} else {
				index = indexOf(clients, remoteEndName);
				if (index >= 0) {
					leave(clients.get(index));
				}
			}
		}

	}
	
	List<ServerPortDescription> toSuperTypeList(List<SessionParticipantDescription> subTypeList) {
		List<ServerPortDescription> retVal = new ArrayList(subTypeList.size());
		for (SessionParticipantDescription subTypeElement: subTypeList) {
			retVal.add(subTypeElement);
		}
		return retVal;
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public List<SessionParticipantDescription> getClients() {
		return clients;
	}
	@Override
	public void setApplicationDefinedState(Serializable newState) {
		applicationDefinedState = newState;
		for (SessionObserver sessionObserver:clientObservers) {
			sessionObserver.newState(newState);
		}
		for (SessionObserver sessionObserver:serverObservers) {
			sessionObserver.newState(newState);
		}
		for (SessionObserver sessionObserver:observers) {
			sessionObserver.newState(newState);
		}
		
	}
	@Override
	public Serializable getApplicationDefinedState() {
		return applicationDefinedState;
	}
	
}
