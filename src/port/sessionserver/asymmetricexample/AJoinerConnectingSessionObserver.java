package port.sessionserver.asymmetricexample;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.datacomm.duplex.object.echoer.example.AFrostyObjectConnectionListener;

import java.util.ArrayList;
import java.util.List;

import port.delay.example.AnEchoingObjectReceiveListener;
import port.sessionserver.AJoinInfo;
import port.sessionserver.JoinInfo;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;

public class AJoinerConnectingSessionObserver extends APrintingSessionObserver implements JoinerConnectingSessionObserver {
	String myName;
	List<DuplexClientInputPort> clientInputPorts = new ArrayList();
//	Map<String, DuplexClientInputPort<Object>> nameToPort = new HashMap();
	public AJoinerConnectingSessionObserver(String aName) {
		myName = aName;
	}
	@Override
	public void joined(SessionParticipantDescription aServerPortDescription) {
		super.joined(aServerPortDescription);
		processSessionMember(aServerPortDescription);
	}
	
	public void processInitialSessionMembers(JoinInfo aJoinInfo) {
//		List<ServerPortDescription> aServerPortDescriptionList = new ArrayList();
//		aServerPortDescriptionList.addAll(aJoinInfo.getMembers());
//		aServerPortDescriptionList.addAll(aJoinInfo.getServers());
		List<SessionParticipantDescription> aServerPortDescriptionList = AJoinInfo.getMembersAndServers(aJoinInfo);


		for (ServerPortDescription aServerPortDescription:aServerPortDescriptionList) {
			processSessionMember(aServerPortDescription);
		}		
	}
	void processSessionMember(ServerPortDescription aServerPortDescription) {
		if (!myName.equals( aServerPortDescription.getName())) {
		DuplexClientInputPort clientInputPort = DuplexObjectInputPortSelector.createDuplexClientInputPort(aServerPortDescription.getHost(), 
				aServerPortDescription.getID(), aServerPortDescription.getName(), myName);
		clientInputPort.addConnectionListener(new AFrostyObjectConnectionListener(clientInputPort));
		clientInputPort.addReceiveListener(new AnEchoingObjectReceiveListener());
		clientInputPorts.add(clientInputPort);
		clientInputPort.connect();
//		clientInputPort.send("Words of Robert Forst Please");
//		nameToPort.put(aServerPortDescription.getName(), clientInputPort);	
		}
	}
	@Override
	public List<DuplexClientInputPort> getClientInputPorts() {
		return clientInputPorts;
	}

}
