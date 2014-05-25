package port.sessionserver.asymmetricexample;

import inputport.datacomm.duplex.DuplexClientInputPort;

import java.util.List;


public interface JoinerConnectingSessionObserver extends JoinerProcessingSessionObserver{
//	public void processInitialSessionMembers(List<ServerPortDescription> aServerPortDescriptionList);
	List<DuplexClientInputPort> getClientInputPorts();
}
