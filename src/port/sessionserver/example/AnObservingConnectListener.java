package port.sessionserver.example;

import port.sessionserver.SessionObserver;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.example.UICreator;

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
