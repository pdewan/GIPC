package sessionport.datacomm.group.object.relayed;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.NoMessageReceivedByResponderException;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupToUniSendTrapper;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.Set;

import port.ParticipantChoice;
import port.trace.AConnectionEvent;
import port.trace.AReplaceConnectionEvent;
import port.trace.ConnectiontEventBus;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPort;
import sessionport.datacomm.duplex.object.relayed.RelayingDuplexConnectionsManager;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.latecomer.ObjectGroupSPRelayedTrapperSelector;
import util.trace.Tracer;
// this one sends bulk sends groups to relayers rather than individuals
// so we use group port instead of duplex
public class AnEfficientRelayingObjectGroupSessionPort  
	extends ARelayingObjectDuplexSessionPort
	implements GroupSessionPort<Object> {
	GroupSendTrapper<Object, Object> groupSendTrapper;
	protected RelayingGroupConnectionsManager groupConnectionsManager;


	public AnEfficientRelayingObjectGroupSessionPort(
			DuplexRPCClientInputPort sessionsServerClientPort,
			String sessionName, String anId, String name, ParticipantChoice aChoice) {
		super(sessionsServerClientPort, sessionName, anId, name, aChoice);
//		groupSendTrapper = GlobalState.getObjectGroupSPRelayedTrapperSelector().createGroupSendTrapper(this, groupConnectionsManager);
//		setGroupSendTrapper (createGroupSendTrapper());

	}
	protected void setSendTrapper() {
//		sendSendTrapper( createSendTrapper());
		setGroupSendTrapper (createGroupSendTrapper());


	}
	protected GroupSendTrapper<Object, Object> createGroupSendTrapper() {
		return ObjectGroupSPRelayedTrapperSelector.getTrapperSelector().
		createGroupSendTrapper(this, groupConnectionsManager);
	}
	
	protected ReceiveTrapper<Object, Object> createReceiveTrapper() {
		return ObjectGroupSPRelayedTrapperSelector.getTrapperSelector().createReceiveTrapper
				(this, receiptRegistrarAndNotifier);
	}
	
	// do not need the duplex send trapper when we have the group one
	// duplex is used for directed messages
//	protected SendTrapper<Object, Object> createSendTrapper() {
//		return null;
//	}
	
//	protected void setGroupSendTrapper(GroupSendTrapper<Object, Object> newVal) {
//		if (newVal.getDestination() == null) {
//			newVal.setDestination(groupSendTrapper.getDestination());
//			Tracer.warning("send trapper == mull!");
//		} else if (newVal.getDestination() == groupSendTrapper) { // adding a new one in front of old one
//			DistEventsBus.newEvent(new AReplaceConnectionEvent(this, groupSendTrapper, newVal, true, false));
//
//		} else {
//			DistEventsBus.newEvent(new AConnectionEvent(this, newVal, true));
//		}
//		groupSendTrapper = newVal;
//	}
	
	
	@Override
	protected RelayingDuplexConnectionsManager createConnectionsManager() {
//		groupConnectionsManager = new ARelayingGroupConnectionsManager(this, participantChoice); 
		groupConnectionsManager = RelayingGroupConnectionManagerSelector.
				createRelayingGroupConnectionManager(this, participantChoice); // ouch, side effect
		return groupConnectionsManager;
	}

	@Override
	public void send(Set<String> clientNames, Object message) {
		groupSendTrapper.send(clientNames, message);
	}
	// not sendimg to self!
	@Override
	public void sendAll(Object message) {
		Set<String> peerNames = getConnections();
		send(peerNames, message);
		
	}
//	@Override
	public void sendAllClients(Object message) {
		Set<String> peerNames = getClientConnections();
		send(peerNames, message);
		
	}
//	@Override
	public void sendAllServers(Object message) {
		Set<String> peerNames = getServerConnections();
		send(peerNames, message);
		
	}
//	@Override
	public void sendAllRemoteMembers(Object message) {
		Set<String> peerNames = getMemberConnections();
		send(peerNames, message);
		
	}
	protected Set<String> getConnectionsAndMe() {
		Set<String> retVal = getMemberConnections();
		retVal.add(getLocalName());
		return retVal;
	}
	@Override
	public void sendAllMembers(Object message) {
		send(getConnectionsAndMe(), message);		
	}
	@Override
	public void send(String remoteName, Object message) {
		Tracer.info(this, "Sending to:" + remoteName + " Message: " + message);
//		Set<String> remoteEnds = new HashSet();
//		remoteEnds.add(remoteName);
		
		groupSendTrapper.send(remoteName, message);
	}
	// this not even passed the sender
	protected Set<String> getAllButSender(Set<String> aSet) {
		if (getSender() == null) throw new NoMessageReceivedByResponderException();
		aSet.remove(getSender());
		return aSet;
	}
	
	// need to reuse code among these four
	public void sendOthers(Object message) {
//		if (getSender() == null) throw new NoMessageReceivedByResponderException();
//		Set<String> peerNames = getConnections();
//		peerNames.remove(getSender());
		// getAllutSender is redundant, getConnections gives peer names,not sender
//		send(getAllButSender(getConnections()), message);
		send(getConnections(), message);
	}
//	@Override
	public void sendOtherClients(Object message) {
		send(getAllButSender(getClientConnections()), message);

	}
//	@Override
	public void sendOtherServers(Object message) {
		send(getAllButSender(getServerConnections()), message);
	}
//	@Override

	public void sendOtherMembers(Object message) {
		send(getAllButSender(getMemberConnections()), message);
	}
	

	@Override
	public GroupSendTrapper<Object, Object> getGroupSendTrapper() {
		return groupSendTrapper;
	}

	@Override
	public GroupToUniSendTrapper<Object, Object> getGroupToUniSendTrapper() {
		return null;
	}

//	@Override
//	public void setGroupSendTrapper(
//			GroupSendTrapper<Object, Object> newVal) {
//		groupSendTrapper = newVal;
//		
//	}
	@Override
	public void setGroupSendTrapper(GroupSendTrapper<Object, Object> newVal) {
	if (newVal.getDestination() == null) {
		newVal.setDestination(groupSendTrapper.getDestination());
		Tracer.warning("send trapper == mull!");
	} else if (newVal.getDestination() == groupSendTrapper) { // adding a new one in front of old one
		ConnectiontEventBus.newEvent( new AReplaceConnectionEvent(this, groupSendTrapper, newVal, true, false));

	} else {
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, newVal, true));
	}
	groupSendTrapper = newVal;
}

	@Override
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<Object, Object> groupToUniSendTrapper) {
		
	}
	

}
