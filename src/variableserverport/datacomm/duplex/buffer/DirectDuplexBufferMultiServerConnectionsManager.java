package variableserverport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;



public interface DirectDuplexBufferMultiServerConnectionsManager  extends SimplexBufferVariableServerConnectionsManager,
 
		ReceiveListener<ByteBuffer>{

	


}
