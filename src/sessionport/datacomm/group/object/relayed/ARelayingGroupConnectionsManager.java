package sessionport.datacomm.group.object.relayed;

import java.util.Collection;
import java.util.Set;

import port.ParticipantChoice;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.ARelayingDuplexConnectionsManager;
import sessionport.datacomm.group.GroupSessionPort;
import util.trace.Tracer;



public class ARelayingGroupConnectionsManager
	extends ARelayingDuplexConnectionsManager 
	implements RelayingGroupConnectionsManager {	
	protected GroupSessionPort<Object> groupObjectSessionPort;	
	
	public ARelayingGroupConnectionsManager(
			GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		super (aGroupSessionPort, aChoice);
		groupObjectSessionPort = aGroupSessionPort;
	}
	// again, we do not make this synchronous, so that sends can happen with receives
	@Override
	public void send(Collection<String> clientNames, Object message) {
//		Set<String> globalClientNames = new HashSet();
//		for (String clientName: clientNames) {
//			globalClientNames.add(ARelayer.localToUniqueRelayerName(sessionName, clientName));
//		}
//		relayerProxy.relay(globalClientNames, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));	 
		Tracer.info(this, "Sending message via relay method");
//		relayerProxy.relay(sessionName, clientNames, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));	
		relayerProxy.relay(clientNames, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));	


	}

	@Override
	public void sendOthers(Object message) {
//		relayerProxy.relayOthers(sessionName, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));
		relayerProxy.relayOthers(new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));

		
	}
	
	
	


}
