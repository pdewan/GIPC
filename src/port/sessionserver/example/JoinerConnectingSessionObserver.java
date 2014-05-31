package port.sessionserver.example;

import inputport.datacomm.duplex.DuplexClientInputPort;

import java.util.List;


public interface JoinerConnectingSessionObserver extends JoinerProcessingSessionObserver{
//	public void processInitialSessionMembers(List<ServerPortDescription> aServerPortDescriptionList);
	List<DuplexClientInputPort> getClientInputPorts();
}
