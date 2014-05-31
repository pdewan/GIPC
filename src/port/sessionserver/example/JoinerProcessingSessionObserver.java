package port.sessionserver.example;

import port.sessionserver.JoinInfo;
import port.sessionserver.SessionObserver;



public interface JoinerProcessingSessionObserver extends SessionObserver{
	
	public void processInitialSessionMembers(JoinInfo aServerPortDescriptionList);
}
