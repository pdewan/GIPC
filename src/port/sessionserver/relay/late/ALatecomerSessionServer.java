package port.sessionserver.relay.late;

import inputport.rpc.duplex.DuplexRPCServerInputPort;

import java.util.List;

import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.Session;
import port.sessionserver.SessionObserver;
import port.sessionserver.relay.ARelayerSupportingSessionServer;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;

public class ALatecomerSessionServer 
	extends ARelayerSupportingSessionServer
	implements LocalLatecomerSessionsServer {
	LatecomerRelayer latecomerRelayer;
//	Set<String> servers = new HashSet();

	public ALatecomerSessionServer(
			DuplexRPCServerInputPort duplexRPCServerInputPort) {
		super(duplexRPCServerInputPort);
	}
	
//	public Set<String> getServers() {
//		return servers;
//	}
	// syncyhronized because we want the observer to not add a message while the return 
	// info is being computed
	@Override
	public  synchronized LatecomerJoinInfo lateJoin(String aSessionName,
			ServerPortDescription aSessionClientDescription,
			SessionObserver aSessionObserver) {	
		
		JoinInfo aJoinInfo = join(aSessionName, aSessionClientDescription, aSessionObserver);
		return toLateComerJoinInfo(aSessionName, aJoinInfo);
//		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
////		List<MessageWithSource> messages = latecomerSession.getMessages(); 
//		LatecomerJoinInfo retVal = new ALatecomerJoinInfo(aJoinInfo, 
//				latecomerSession.getMessages(),
//				latecomerSession.getServerMessages(),
//				latecomerSession.getClientMessages());
//		Tracer.info(this, "Late join call returning " + retVal);
//		return retVal;		
	}
	
	LatecomerJoinInfo toLateComerJoinInfo(String aSessionName, JoinInfo aJoinInfo) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
//		return toLateComerJoinInfo(aSessionName, aJoinInfo);

//		List<MessageWithSource> messages = latecomerSession.getMessages(); 
		LatecomerJoinInfo retVal = new ALatecomerJoinInfo(aJoinInfo, 
				latecomerSession.getMessages(),
				latecomerSession.getServerMessages(),
				latecomerSession.getClientMessages());
		Tracer.info(this, "Late join call returning " + retVal);
		return retVal;
	}
	
	@Override
	public  synchronized LatecomerJoinInfo lateJoinAsServer(String aSessionName,
			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {	
		JoinInfo aJoinInfo = super.joinAsServer(aSessionName, aSessionClientDescription, aSessionObserver);
		return toLateComerJoinInfo(aSessionName, aJoinInfo);
//
//		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
//		List<MessageWithSource> messages = latecomerSession.getMessages(); 
//		LatecomerJoinInfo retVal = new ALatecomerJoinInfo(aJoinInfo, messages);
//
//		Tracer.info(this, "Late join call returning " + retVal);
//		return retVal;		
	}
	
	@Override
	public  synchronized LatecomerJoinInfo lateJoinAsClient(String aSessionName,
			ServerPortDescription aSessionClientDescription, SessionObserver aSessionObserver) {	
		JoinInfo aJoinInfo = super.joinAsClient(aSessionName, aSessionClientDescription, aSessionObserver);
		return toLateComerJoinInfo(aSessionName, aJoinInfo);
	
	}

	
	@Override
	protected Session createSession(String aSessionName) {
		Session session = new ALatecomerSession();
		Tracer.info(this, "Created for session named: " + aSessionName + " session object: " + session);
		return session;

//		return new ALatecomerSession();

	}

	@Override
	public List<MessageWithSource> getMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		return latecomerSession.getMessages();
	}

	@Override
	public void addMessage(String aSessionName, MessageWithSource aMessage) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
        latecomerSession.addMessage(aMessage);
	}

	@Override
	public void clearMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		latecomerSession.clearMessages();		
	}

	@Override
	public List<MessageWithSource> getServerMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		return latecomerSession.getServerMessages();
	}

	@Override
	public void addServerMessage(String aSessionName, MessageWithSource message) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		latecomerSession.addServerMessage(message);
		
	}

	@Override
	public void clearServerMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		latecomerSession.clearServerMessages();
		
	}

	@Override
	public List<MessageWithSource> getClientMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		return latecomerSession.getClientMessages();
	}

	@Override
	public void addClientMessage(String aSessionName, MessageWithSource message) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		latecomerSession.addClientMessage(message);
	}

	@Override
	public void clearClientMessages(String aSessionName) {
		LatecomerSession latecomerSession = (LatecomerSession) getAndMaybeCreateSession(aSessionName);
		latecomerSession.clearClientMessages();
		
	}




}
