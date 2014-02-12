package port.sessionserver.example;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.example.UICreator;

public class AJoiningAndObservingConnectListener extends AJoiningConnectListener {

	SessionObserver sessionObserver;
	UICreator uiCreator;

	public AJoiningAndObservingConnectListener(InputPort anInputPort, String aSessionName, Class aSessionServerClass,
			
			ServerPortDescription aServerPortDescription,
			 JoinerProcessingSessionObserver aSessionObserver, UICreator aUICreator) {
		super(anInputPort, aSessionName,  aSessionServerClass, aServerPortDescription);
		sessionObserver = aSessionObserver;
		uiCreator = aUICreator;


	}

	protected void participateInSession() {
		((JoinerProcessingSessionObserver) sessionObserver).processInitialSessionMembers(
				sessionServerProxy.join(sessionName, serverPortDescription,  sessionObserver));
		uiCreator.createUI(inputPort);
		
	}

}
