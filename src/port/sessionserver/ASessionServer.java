package port.sessionserver;


import inputport.rpc.duplex.DuplexRPCServerInputPort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import trace.port.JoinCallInitiated;
import util.trace.Tracer;



public class ASessionServer implements LocalSessionsServer {
	protected Map<String, Session> nameToSession = new HashMap();
//	public static int SESSION_SERVER_PORT = 9090;
//	public static String SESSION_SERVER_NAME = "Session Server";
//	GroupRPCServerInputPort groupRPCServerInputPort;
	protected DuplexRPCServerInputPort duplexRPCServerInputPort;

	


	public ASessionServer(DuplexRPCServerInputPort aDuplexRPCServerInputPort) {
		duplexRPCServerInputPort = aDuplexRPCServerInputPort;
	}
	public ASessionServer() {
		
	}
//	protected Session createSession() {
//		return new ASession();
//	}
	
	
	
	protected Session createSession(String aSessionName) {
		return new ASession();
//		nameToSession.put(aSessionName, session);
//		return session;
	}
	public Session getAndMaybeCreateSession(String aSessionName) {
		Session session = nameToSession.get(aSessionName);
		if (session == null) {
			session = createSession(aSessionName);
			nameToSession.put(aSessionName, session);
			duplexRPCServerInputPort.addConnectionListener(session);
		}
		return session;
	}
	@Override
	public synchronized JoinInfo join(String aSessionName,
			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {
		JoinCallInitiated.newCase(this);
		Session session = getAndMaybeCreateSession(aSessionName);
		session.join(aSessionClientDescription, aSessionObserver);
		JoinInfo retVal = new AJoinInfo(session.getMembers(), session.getServers(), session.getClients(), session.getApplicationDefinedState());
		Tracer.info(this, "Joined session: aSessionName" + " participant " + aSessionClientDescription + " new members " + retVal );
		return retVal;
	}
	
	@Override
	public JoinInfo joinAsServer(String aSessionName,
			ServerPortDescription peerDescription, SessionObserver aSessionObserver) {
		JoinCallInitiated.newCase(this);
		Session session = getAndMaybeCreateSession(aSessionName);
		session.joinAsServer(peerDescription, aSessionObserver);
		JoinInfo retVal = new AJoinInfo(session.getMembers(), session.getServers(), session.getClients(), session.getApplicationDefinedState());

		Tracer.info(this, "Joined session: aSessionName" + " participant " + peerDescription + " new members " + retVal );
		return retVal;

	}
	
	@Override
	public JoinInfo joinAsClient(String theSessionName,
			ServerPortDescription aServerPortDescription, SessionObserver sessionObserver) {
		JoinCallInitiated.newCase(this);

		Session session = getAndMaybeCreateSession(theSessionName);

		session.joinAsClient(aServerPortDescription, sessionObserver);
		JoinInfo retVal = new AJoinInfo(session.getMembers(), session.getServers(), session.getClients(), session.getApplicationDefinedState());

		Tracer.info(this, "Joined session: aSessionName" + " participant " + aServerPortDescription + " new members " + retVal );
		return retVal;
	}
	@Override
	public synchronized void leave(String aSessionName, ServerPortDescription aSessionClientDescription) {
		Session session = nameToSession.get(aSessionName);
		if (session == null)
			Tracer.error("Leaving unknown session");
		Tracer.info(this,  aSessionClientDescription + " left session " + aSessionName);
		session.leave(aSessionClientDescription);
	}
	@Override
	public DuplexRPCServerInputPort getDuplexRPCServerInputPort() {
		return duplexRPCServerInputPort;
	}
	@Override
	public void setDuplexRPCServerInputPort(
			DuplexRPCServerInputPort duplexRPCServerInputPort) {
		this.duplexRPCServerInputPort = duplexRPCServerInputPort;
	}
	
	@Override
	public List<SessionParticipantDescription> getMembers(String theSessionName) {
		Session session = getAndMaybeCreateSession(theSessionName);
		return session.getMembers();
	}
	
	@Override
	public List<SessionParticipantDescription> getServers(String theSessionName) {
		Session session = getAndMaybeCreateSession(theSessionName);
		return session.getServers();
	}
	
	@Override
	public List<SessionParticipantDescription> getClients(String theSessionName) {
		Session session = getAndMaybeCreateSession(theSessionName);
		return session.getClients();
	}
	
	// caller can screw around and delete, but only in this VM
	public Set<String> getSessionNames() {
		return nameToSession.keySet();
	}
	


	

}
