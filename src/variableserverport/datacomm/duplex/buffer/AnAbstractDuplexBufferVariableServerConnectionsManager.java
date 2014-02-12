package variableserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import port.ParticipantChoice;
import port.trace.AConnectionEvent;
import port.trace.ConnectiontEventBus;
import util.trace.Tracer;
import variableserverport.datacomm.duplex.DuplexVariableServerClientPort;
import variableserverport.datacomm.simplex.buffer.AnAbstractSimplexBufferVariableServerConnectionsManager;

//this port is used only by multiserver port
// variable servger means that we can send to one of several servers connected to us.
// so this is like a group server port in that sense
// more like an output port, one client many servers
// implementation of message received finally, as this is duplex
/// so this should be called Simplex vs duplex
// send implmenetation makes it look as if message was sent to a logical name
// message received gives the actual server

// client port would have to be created by someone else

// as there is a receive listener, that is why it is duplex

// it makes client unaware of servers, which seems wrong
// this functionality should be in the replicated port
public abstract class AnAbstractDuplexBufferVariableServerConnectionsManager 
	extends AnAbstractSimplexBufferVariableServerConnectionsManager implements DirectDuplexBufferMultiServerConnectionsManager {

	protected DuplexVariableServerClientPort<ByteBuffer> duplexBufferVariableServerPort;

	public AnAbstractDuplexBufferVariableServerConnectionsManager(
			DuplexVariableServerClientPort<ByteBuffer> aDuplexBufferSessionPort, ParticipantChoice aParticipantChoice/*,
										 ServerPortDescription aServerPortDescription*/) {
		super(aDuplexBufferSessionPort, aParticipantChoice);
		duplexBufferVariableServerPort = aDuplexBufferSessionPort;
		ConnectiontEventBus.newEvent(new AConnectionEvent(this, duplexBufferVariableServerPort, false));

	}
	
	// 
	// why are we overriding this method here?
	// makes it look as if message sent only to logical name
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sendId) {
		duplexBufferVariableServerPort.notifyPortSend(variableServerClientPort.getLogicalRemoteEndPoint(), message, sendId);
		
	}
	// actual client name given
	@Override
	public void messageReceived(String remoteClientName,
			ByteBuffer message) {
		Tracer.info(this, "Mesage " + message + " received from " + remoteClientName);
		duplexBufferVariableServerPort.notifyPortReceive(remoteClientName, message);		
	}



}
