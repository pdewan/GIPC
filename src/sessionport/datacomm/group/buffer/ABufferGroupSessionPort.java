package sessionport.datacomm.group.buffer;


import inputport.datacomm.ReceiveListener;
import inputport.datacomm.group.buffer.ABufferGroupServerInputPort;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.group.GroupSessionPort;


public class ABufferGroupSessionPort extends ABufferGroupServerInputPort implements GroupSessionPort<ByteBuffer>{
	DuplexSessionPort<ByteBuffer> duplexSessionPort;
	public ABufferGroupSessionPort(
			DuplexSessionPort<ByteBuffer> duplexServerInputPort) {
		super(duplexServerInputPort);
		duplexSessionPort = duplexServerInputPort;
	}
//	String physicalRemoteEndPoint;
//	String logicalRemoteEndPoint;
	@Override
	public String getLogicalRemoteEndPoint() {
		return duplexServerInputPort.getLogicalRemoteEndPoint();
	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return duplexServerInputPort.getPhysicalRemoteEndPoint();
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setPhysicalRemoteEndPoint(newVal);
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setLogicalRemoteEndPoint(newVal);
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
	@Override
	public Set<String> getMemberConnections() {
		return  duplexSessionPort.getMemberConnections();
	}
//	Set<String> toOthers (Set<String> all) {
//		if (getSender() == null) throw new NoMessageReceivedByResponderException();
//		all.remove(getSender());
//		return all;
//	}
	public void sendOtherClients(ByteBuffer message) {
		send(toOthers(getClientConnections()), message);
	}
	public void sendAllClients(ByteBuffer message) {
		send(getClientConnections(), message);
	}
	public void sendOtherServers(ByteBuffer message) {
		send(toOthers(getServerConnections()), message);
	}
	public void sendAllServers(ByteBuffer message) {
		send(getServerConnections(), message);
	}
	public void sendOtherMembers(ByteBuffer message) {
		send(toOthers(getMemberConnections()), message);
	}
	public void sendAllMembers(ByteBuffer message) {
		send(getMemberConnections(), message);
	}

	@Override
	public ParticipantChoice getParticipantChoice() {
		// TODO Auto-generated method stub
		return duplexSessionPort.getParticipantChoice();
	}


}
