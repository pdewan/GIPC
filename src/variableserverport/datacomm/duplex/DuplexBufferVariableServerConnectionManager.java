package variableserverport.datacomm.duplex;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;
import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;



public interface DuplexBufferVariableServerConnectionManager  extends SimplexBufferVariableServerConnectionsManager, ReceiveListener<ByteBuffer>{



}
