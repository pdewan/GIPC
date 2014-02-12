package port.sessionserver.example;

import inputport.datacomm.duplex.DuplexClientInputPort;

import java.util.List;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionObserver;


public interface JoinerConnectingSessionObserver extends JoinerProcessingSessionObserver{
//	public void processInitialSessionMembers(List<ServerPortDescription> aServerPortDescriptionList);
	List<DuplexClientInputPort> getClientInputPorts();
}
