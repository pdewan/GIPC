package staticsessionport.datacomm.duplex.buffer.fullp2p;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.List;

import multiserverport.datacomm.duplex.buffer.ABufferDuplexMultiServerClientPort;
import port.ParticipantChoice;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.buffer.example.ABufferDuplexSessionPortLauncher;
import util.trace.Tracer;
import variableserverport.SimplexVariableServerClientPort;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;

// like a multisever port in that a client can send messages to multiple servers
// also each client is itself a server, so we must reguster a client port for each server
// and also a server port for each of the other parties
public class ABufferDuplexStaticSessionPortFullP2P extends ABufferDuplexMultiServerClientPort implements DuplexSessionPort<ByteBuffer> {
	
	String id;

	BufferStaticSessionPortConnectionManagerFullP2P bufferStaticSessionConnectionManager;

	
	public ABufferDuplexStaticSessionPortFullP2P (
			SessionParticipantDescription[] anOtherServers, 
			String anId, String aName, String aRemoteEndPoint, 
			ParticipantChoice aChoice) {
		super(anOtherServers,  aRemoteEndPoint, aName, aChoice);
		id = anId;
		createServerPort();
//		// this is the part that multiserver port does not have to do. Hence multiserver port cannot be subclass of static port
//		try {
//			ServerPortDescription aServerPortDescription =  new AServerPortDescription(InetAddress.getLocalHost().getHostName(), id, name);
//			Tracer.info(this, "Asking connections manager to create server port for P2P connections");
//			bufferStaticSessionConnectionManager.createServerInputPort(aServerPortDescription);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}


		
	}
	// this is the part that multiserver port does not have to do. Hence multiserver port cannot be subclass of static port
	protected void createServerPort() {
				try {
					ServerPortDescription aServerPortDescription =  new AServerPortDescription(InetAddress.getLocalHost().getHostName(), id, name);
					Tracer.info(this, "Asking connections manager to create server port for P2P connections");
					bufferStaticSessionConnectionManager.createServerInputPort(aServerPortDescription);
					} catch (Exception e) {
						e.printStackTrace();
					}

		
	}

	@Override
	protected SimplexBufferVariableServerConnectionsManager createFP2PBufferConnectionsManager(
			SimplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice) {
		try {
			bufferStaticSessionConnectionManager = new ABufferStaticSessionPortConnectionsManagerFullP2P(this, aParticipantChoice);
			Tracer.info(this, "Created connectio manager:" + bufferStaticSessionConnectionManager);

			return bufferStaticSessionConnectionManager;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

}
	
	
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9091", "Test Client", ParticipantChoice.MEMBER);		
	}
	@Override
	public String getServerId() {
		return id;
	}
	@Override
	public List<SessionParticipantDescription> getServers() {
		return bufferStaticSessionConnectionManager.getServers();
	}
	@Override
	public List<SessionParticipantDescription> getMembers() {
		return bufferStaticSessionConnectionManager.getMembers();
	}
	@Override
	public List<SessionParticipantDescription> getClients() {
		// TODO Auto-generated method stub
		return bufferStaticSessionConnectionManager.getClients();
	}
	@Override
	public SessionParticipantDescription getServer(String aName) {
		return bufferStaticSessionConnectionManager.getServer(aName);
	}
	@Override
	public SessionParticipantDescription getClient(String aName) {
		return bufferStaticSessionConnectionManager.getClient(aName);
	}
	@Override
	public SessionParticipantDescription getMember(String aName) {
		// TODO Auto-generated method stub
		return bufferStaticSessionConnectionManager.getMember(aName);
	}

}
