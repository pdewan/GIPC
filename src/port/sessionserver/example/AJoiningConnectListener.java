package port.sessionserver.example;

import port.sessionserver.ServerPortDescription;
import inputport.InputPort;

public class AJoiningConnectListener extends ASessionParticipatingConnectListener implements Runnable {
	ServerPortDescription serverPortDescription;


	public AJoiningConnectListener(InputPort anInputPort, String aSessionName,
			Class aSessionServerClass, ServerPortDescription aServerPortDescription
		) {
		super(anInputPort, aSessionName, aSessionServerClass);
		serverPortDescription = aServerPortDescription;

	}
	protected void participateInSession() {
		sessionServerProxy.joinAsServer(sessionName, serverPortDescription, null);
		
	}
	

}
