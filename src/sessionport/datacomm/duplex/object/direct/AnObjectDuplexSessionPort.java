package sessionport.datacomm.duplex.object.direct;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.ADuplexObjectServerInputPort;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Set;

import port.sessionserver.SessionParticipantDescription;

import sessionport.datacomm.duplex.DuplexSessionPort;




public class AnObjectDuplexSessionPort extends ADuplexObjectServerInputPort implements DuplexSessionPort<Object>{
	DuplexSessionPort<ByteBuffer> bufferSessionPort;	
	public AnObjectDuplexSessionPort(
			DuplexSessionPort<ByteBuffer> duplexServerInputPort) {
		super(duplexServerInputPort);
		bufferSessionPort = duplexServerInputPort;
	}
	String physicalRemoteEndPoint;
	String logicalRemoteEndPoint;
	@Override
	public String getLogicalRemoteEndPoint() {
		return logicalRemoteEndPoint;
	}

	@Override
	public String getPhysicalRemoteEndPoint() {
		return physicalRemoteEndPoint;
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		physicalRemoteEndPoint = newVal;
	}

	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		logicalRemoteEndPoint = newVal;
	}

	@Override
	public Set<String> getClientConnections() {
		return bufferSessionPort.getClientConnections();
	}

	@Override
	public Set<String> getServerConnections() {
		return bufferSessionPort.getServerConnections();
	}

	@Override
	public Set<String> getMemberConnections() {
		return bufferSessionPort.getMemberConnections();
	}

	@Override
	public List<SessionParticipantDescription> getServers() {
		return bufferSessionPort.getServers();
	}

	@Override
	public List<SessionParticipantDescription> getMembers() {
		return bufferSessionPort.getMembers();
	}

	@Override
	public List<SessionParticipantDescription> getClients() {
		
		return  bufferSessionPort.getClients();
	}

	@Override
	public SessionParticipantDescription getServer(String aName) {
		return bufferSessionPort.getServer(aName);
	}

	@Override
	public SessionParticipantDescription getClient(String aName) {
		return  bufferSessionPort.getClient(aName);
	}

	@Override
	public SessionParticipantDescription getMember(String aName) {
		return bufferSessionPort.getMember(aName);
	}
	
}
