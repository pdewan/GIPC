package multiserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;
import port.sessionserver.SessionParticipantDescription;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;

public interface BufferDuplexMultiServerConnectionsManager extends 
//BufferStaticSessionPortConnectionManagerFullP2P {
SimplexBufferVariableServerConnectionsManager, ReceiveListener<ByteBuffer>{
	
	public void connect(SessionParticipantDescription[] otherServers);
	public void initPeers(SessionParticipantDescription[] otherServers);


}
