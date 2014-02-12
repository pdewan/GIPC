package variableserverport.datacomm.duplex;

import inputport.datacomm.ReceiveListener;

import java.nio.ByteBuffer;

import variableserverport.datacomm.simplex.buffer.SimplexBufferVariableServerConnectionsManager;



public interface DuplexBufferVariableServerConnectionManager  extends SimplexBufferVariableServerConnectionsManager, ReceiveListener<ByteBuffer>{



}
