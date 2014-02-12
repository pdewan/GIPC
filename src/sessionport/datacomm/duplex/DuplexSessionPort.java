package sessionport.datacomm.duplex;

import java.util.Set;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import inputport.datacomm.duplex.DuplexServerInputPort;

public interface DuplexSessionPort<MessageType> extends DuplexServerInputPort<MessageType>
				,DuplexMultiServerClientPort<MessageType>, SessionConnections, SessionInfo
{
//	public Set<String> getClientConnections() ;
//	
//	public Set<String> getServerConnections() ;
//	public Set<String> getMemberConnections() ;
}
