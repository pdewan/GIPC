package sessionport.rpc.group;

import inputport.rpc.group.AGroupRPCServerInputPort;

import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;



public class AGroupRPCSessionPort extends AGroupRPCServerInputPort implements GroupRPCSessionPort{
	GroupSessionPort groupSessionPort;
	public AGroupRPCSessionPort(
			GroupSessionPort<Object> typedGroupServerInputPort) {
		super(typedGroupServerInputPort);
		groupSessionPort = typedGroupServerInputPort;
	}	
//	public GroupRPCSessionPort createGroupRPCSessionPort(
//			String aSessionsServerHost, String aSessionsServerId,
//			String aSessionsServerName, String aSessionName, String anId,
//			String aName) {
//		GroupSessionPort<Object> objectSessionPort = ObjectGroupSessionPortSelector.createObjectGroupSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName);
//		return new AGroupRPCSessionPort(objectSessionPort); 
//	}
	@Override
	public List<SessionParticipantDescription> getServers() {
		return groupSessionPort.getServers();
	}
	@Override
	public List<SessionParticipantDescription> getMembers() {
		// TODO Auto-generated method stub
		return groupSessionPort.getMembers();
	}
	@Override
	public List<SessionParticipantDescription> getClients() {
		return groupSessionPort.getClients();
	}
	@Override
	public SessionParticipantDescription getServer(String aName) {
		return groupSessionPort.getServer(aName);
	}
	@Override
	public SessionParticipantDescription getClient(String aName) {
		return groupSessionPort.getClient(aName);
	}
	@Override
	public SessionParticipantDescription getMember(String aName) {
		return groupSessionPort.getMember(aName);
	}
	@Override
	public Set<String> getClientConnections() {
		// TODO Auto-generated method stub
		return groupSessionPort.getClientConnections();
	}
	@Override
	public Set<String> getServerConnections() {
		return groupSessionPort.getServerConnections();
	}
	@Override
	public Set<String> getMemberConnections() {
		return  groupSessionPort.getMemberConnections();
	}
	@Override
	public void sendOtherClients(Object message) {
		groupSessionPort.sendOtherClients(message);
	}
	@Override
	public void sendOtherServers(Object message) {
		groupSessionPort.sendOtherServers(message);
		
	}
	@Override
	public void sendOtherMembers(Object message) {
		groupSessionPort.sendOtherMembers(message);
	}
	@Override
	public void sendAllClients(Object message) {
		groupSessionPort.sendAllClients(message);
	}
	@Override
	public void sendAllServers(Object message) {
		groupSessionPort.sendAllServers(message);
	}
	@Override
	public void sendAllRemoteMembers(Object message) {
		groupSessionPort.sendAllRemoteMembers(message);
	}
	@Override
	public void sendAllMembers(Object message) {
		groupSessionPort.sendAllMembers(message);
	}
	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return groupSessionPort.getParticipantChoice();
	}
}
