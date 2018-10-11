package port.sessionserver.example;

import java.util.List;

import inputport.datacomm.duplex.DuplexClientInputPort;


public interface JoinerConnectingSessionObserver extends JoinerProcessingSessionObserver{
//	public void processInitialSessionMembers(List<ServerPortDescription> aServerPortDescriptionList);
	List<DuplexClientInputPort> getClientInputPorts();
}
