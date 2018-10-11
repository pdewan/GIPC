package port.sessionserver.relay.late;

import java.util.Collection;

import inputport.rpc.group.GroupRPCServerInputPort;
import port.relay.ARelayer;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;


public class ALatecomerRelayer  
	extends ARelayer
	implements LatecomerRelayer {
	LocalLatecomerSessionsServer sessionsServer;
	public ALatecomerRelayer(GroupRPCServerInputPort duplexPCServerInputPort, LocalLatecomerSessionsServer aSessionsServer) {
		super(duplexPCServerInputPort);
		sessionsServer = aSessionsServer;
	}
	
	@Override
	public void relay(String sessionName, Collection<String> remoteEnds, MessageWithSource message) {
		Tracer.info(this, "Group relay, remote ends: " + remoteEnds + "of message " + message);
//		super.relay(sessionName, remoteEnds, message);
		super.relay(remoteEnds, message);

		LatecomerSession latecomerSession = (LatecomerSession) sessionsServer.getAndMaybeCreateSession(sessionName);
		addMessage(latecomerSession, message);
//		latecomerSession.addMessage(message); // add bot client and server messages
//		switch (latecomerSession.getParticipantChoice(message.getSource())) {
//		case SERVER_ONLY:
//			latecomerSession.addServerMessage(message);
//			break;			
//		case CLIENT_ONLY:
//			latecomerSession.addClientMessage(message);
//			break;
//		case MEMBER:
//			break;			
//		}

	}
	void addMessage (LatecomerSession latecomerSession, MessageWithSource message) {
		Tracer.info(this, "add Message others");
		latecomerSession.addMessage(message); // add bot client and server messages
		switch (latecomerSession.getParticipantChoice(message.getSource())) {
		case SERVER_ONLY:
			latecomerSession.addServerMessage(message);
			break;			
		case CLIENT_ONLY:
			latecomerSession.addClientMessage(message);
			break;
		case MEMBER:
			break;			
		}

	}
	@Override
	public void relayOthers(MessageWithSource message) {
		Tracer.info(this, "relay others");
		super.relayOthers(message);
		for (String sessionName:sessionsServer.getSessionNames()) {
			addMessage ((LatecomerSession) sessionsServer.getAndMaybeCreateSession(sessionName), message);
		}
		
		

		
	}
//	public void relay(String sessionName, String remoteEnd, MessageWithSource message) {
//		Tracer.info(this, "Single relay, remote ends: " + remoteEnd + "of message " + message);
//		super.relay(sessionName, remoteEnd, message);
//		LatecomerSession latecomerSession = (LatecomerSession) sessionsServer.getAndMaybeCreateSession(sessionName);
//		latecomerSession.addMessage(message); // add bot client and server messages
//		switch (latecomerSession.getParticipantChoice(message.getSource())) {
////		case SERVER_ONLY:
////			latecomerSession.addServerMessage(message);
////			break;			
//		case CLIENT_ONLY:
//			latecomerSession.addClientMessage(message); // return values of clients  need to be buffered
//			break;
////		case MEMBER:
////			break;			
//		}
//		
//
//	}
//	@Override
//	public void relayOthers(String aSessionName, MessageWithSource aMessage) {
//		super.relayOthers(aSessionName, aMessage);		
//	}
	

}
