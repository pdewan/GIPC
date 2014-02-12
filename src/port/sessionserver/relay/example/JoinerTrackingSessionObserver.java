package port.sessionserver.relay.example;

import java.util.Set;

import port.relay.Relayer;
import port.sessionserver.example.JoinerProcessingSessionObserver;


public interface JoinerTrackingSessionObserver extends JoinerProcessingSessionObserver{
//	public void processInitialSessionMembers(List<ServerPortDescription> aServerPortDescriptionList);
	Set<String> getJoiners();
	void setRelayerProxy(Relayer aRelayer);
}
