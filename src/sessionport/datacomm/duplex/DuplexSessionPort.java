package sessionport.datacomm.duplex;

import variableserverport.SimplexVariableServerClientPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;

public interface DuplexSessionPort<MessageType> extends DuplexServerInputPort<MessageType>
				,DuplexMultiServerClientPort<MessageType>, SessionConnections, SessionInfo, 
				SimplexVariableServerClientPort<MessageType>
{
//	public Set<String> getClientConnections() ;
//	
//	public Set<String> getServerConnections() ;
//	public Set<String> getMemberConnections() ;
}
