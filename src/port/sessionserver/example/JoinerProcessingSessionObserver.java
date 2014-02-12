package port.sessionserver.example;

import port.sessionserver.JoinInfo;
import port.sessionserver.SessionObserver;
import inputport.datacomm.duplex.DuplexClientInputPort;



public interface JoinerProcessingSessionObserver extends SessionObserver{
	
	public void processInitialSessionMembers(JoinInfo aServerPortDescriptionList);
}
