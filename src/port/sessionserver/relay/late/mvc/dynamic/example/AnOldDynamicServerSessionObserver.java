package port.sessionserver.relay.late.mvc.dynamic.example;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;

import java.io.Serializable;
import java.util.List;

import port.ParticipantChoice;
import port.relay.Relayer;
import port.sessionserver.AJoinInfo;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.SessionObserver;
import port.sessionserver.relay.late.mvc.example.AnOldLatecomerRelayerConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class AnOldDynamicServerSessionObserver extends AnOldLatecomerRelayerConnectionListener implements DynamicServerSessionObserver  {
	String serverName;
	
	public AnOldDynamicServerSessionObserver(String aServerName, ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener, List<MessageWithSource> aMessageList) {
		super(aServerPortDescription, aClientName, aConnectionListener, aMessageList);
		serverName = aServerName;
		
	}
	@Override
	public void joined(SessionParticipantDescription aServerPortDescription) {
		System.out.println("Session member has joined: " + aServerPortDescription);
		if (aServerPortDescription.getParticipantChoice() == ParticipantChoice.SERVER_ONLY ) { // server 
			initNextServerPortDescription(aServerPortDescription);
			// delaying the fake connect until we have the server port description
			connected(serverName, ConnectionType.CLIENT_TO_SESSION); 
		}
	}	
	
	
	
	@Override
	public void left(SessionParticipantDescription sessionClientDescription) {
		System.out.println("Session member has left: " + sessionClientDescription);
		
	}
	@Override
	public void newState(Serializable newState) {
		// TODO Auto-generated method stub
		
	}
	
	
}
