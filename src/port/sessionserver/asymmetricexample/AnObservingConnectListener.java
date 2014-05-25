package port.sessionserver.asymmetricexample;

import inputport.InputPort;
import inputport.datacomm.simplex.buffer.example.UICreator;
import port.sessionserver.SessionObserver;

public class AnObservingConnectListener extends ASessionParticipatingConnectListener implements Runnable {
	
	SessionObserver sessionObserver;
	UICreator uiCreator;
	

	public AnObservingConnectListener(InputPort anInputPort, String aSessionName, Class aSessionServerClass,
			
			SessionObserver aSessionObserver, UICreator aUICreator) {
		super(anInputPort, aSessionName, aSessionServerClass);
		sessionObserver = aSessionObserver;
		uiCreator = aUICreator;
	}
	protected void participateInSession() {
		((JoinerProcessingSessionObserver) sessionObserver).processInitialSessionMembers(
				sessionServerProxy.joinAsClient(sessionName, null, sessionObserver));
		uiCreator.createUI(inputPort);
	}
	

}
