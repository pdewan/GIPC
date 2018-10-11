package sessionport.datacomm.group.object;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.object.AGroupObjectServerInputPort;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.group.GroupSessionPort;




public class AnObjectGroupSessionPort extends AGroupObjectServerInputPort implements GroupSessionPort<Object> {
	DuplexSessionPort<Object> duplexSessionPort;
	public AnObjectGroupSessionPort(
			DuplexSessionPort<Object> duplexServerInputPort,
			GroupServerInputPort<ByteBuffer> bgroupServerInputPort) {
		super(duplexServerInputPort, bgroupServerInputPort);
		duplexSessionPort = duplexServerInputPort;
		//replace regular server trappers with session trappers
//		createGroupSendTrapper();
//		GroupSendTrapper<Object, Object> sessionGroupSendTrapper = SessionObjectGroupTrapperSelector.getTrapperSelector().
//				createGroupSendTrapper(this, groupSendTrapper);	
//		groupSendTrapper = sessionGroupSendTrapper;
//		setGroupSendTrapper(groupSendTrapper);
//		ReceiveTrapper<Object, Object> sessionReceiveTrapper = SessionObjectGroupTrapperSelector.getTrapperSelector().
//				createReceiveTrapper(this, receiveTrapper.getDestination());
//		receiveTrapper.setDestination(sessionReceiveTrapper);
		
	}
	@Override
	protected GroupSendTrapper<Object, Object> createGroupSendTrapper() {
		// do we need this extra level of forwarding?
		GroupSendTrapper<Object, Object> superGroupSendTrapper = super.createGroupSendTrapper();
		GroupSendTrapper<Object, Object> sessionGroupSendTrapper = SessionObjectGroupTrapperSelector.getTrapperSelector().
				createGroupSendTrapper(this, superGroupSendTrapper);	
		return sessionGroupSendTrapper;

	}
	@Override
	protected ReceiveTrapper<Object, Object> createReceiveTrapper() {
		ReceiveTrapper<Object, Object> superReceiveTrapper = super.createReceiveTrapper();
		ReceiveTrapper<Object, Object> sessionReceiveTrapper = SessionObjectGroupTrapperSelector.getTrapperSelector().
				createReceiveTrapper(this, superReceiveTrapper.getDestination());
		superReceiveTrapper.setDestination(sessionReceiveTrapper);
		return superReceiveTrapper;
	}
	
//	String physicalRemoteEndPoint;
//	String logicalRemoteEndPoint;
	public String getLogicalRemoteEndPoint() {
		return bbGroupServerInputPort.getLogicalRemoteEndPoint();
	}
	@Override
	public String getPhysicalRemoteEndPoint() {
		return bbGroupServerInputPort.getPhysicalRemoteEndPoint();
	}
	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		bbGroupServerInputPort.setLogicalRemoteEndPoint(newVal);
	}
	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		bbGroupServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}
	@Override
	public List<SessionParticipantDescription> getServers() {
		return duplexSessionPort.getServers();
	}
	@Override
	public List<SessionParticipantDescription> getMembers() {
		// TODO Auto-generated method stub
		return duplexSessionPort.getMembers();
	}
	@Override
	public List<SessionParticipantDescription> getClients() {
		return duplexSessionPort.getClients();
	}
	@Override
	public SessionParticipantDescription getServer(String aName) {
		return duplexSessionPort.getServer(aName);
	}
	@Override
	public SessionParticipantDescription getClient(String aName) {
		return duplexSessionPort.getClient(aName);
	}
	@Override
	public SessionParticipantDescription getMember(String aName) {
		return duplexSessionPort.getMember(aName);
	}
	@Override
	public Set<String> getClientConnections() {
		// TODO Auto-generated method stub
		return duplexSessionPort.getClientConnections();
	}
	@Override
	public Set<String> getServerConnections() {
		return duplexSessionPort.getServerConnections();
	}
	protected Set<String> getConnectionsAndMe() {
		Set<String> retVal = getMemberConnections();
		retVal.add(getLocalName());
		return retVal;
	}
	@Override
	public Set<String> getMemberConnections() {
		return  duplexSessionPort.getMemberConnections();
	}
	public void sendOtherClients(Object message) {
		send(toOthers(getClientConnections()), message);
	}
	public void sendAllClients(Object message) {
		send(getClientConnections(), message);
	}
	public void sendOtherServers(Object message) {
		send(toOthers(getServerConnections()), message);
	}
	public void sendAllServers(Object message) {
		send(getServerConnections(), message);
	}
	public void sendOtherMembers(Object message) {
		send(toOthers(getMemberConnections()), message);
	}
	public void sendAllRemoteMembers(Object message) {
		send(getMemberConnections(), message);
	}
	public void sendAllMembers(Object message) {
		send(getConnectionsAndMe(), message);
	}
	
	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return duplexSessionPort.getParticipantChoice();
	}

	
	
//	static {
//		GlobalState.getObjectGroupTranslatingSessionIPTrapperSelector().
//		setGroupSendTrapperFactory(new ASerializingGroupForwarderFactory());
//	
//	}
}
