package variableserverport.datacomm.duplex.buffer;

import inputport.datacomm.ReceiveListener;

import java.nio.ByteBuffer;

import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;



public interface DirectDuplexBufferMultiServerConnectionsManager  extends SimplexBufferVariableServerConnectionsManager,
 
		ReceiveListener<ByteBuffer>{

	


}
