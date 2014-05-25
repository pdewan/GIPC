package sessionport.datacomm.duplex;

import inputport.datacomm.duplex.DuplexServerInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;

public interface DuplexSessionPort<MessageType> extends DuplexServerInputPort<MessageType>
				,DuplexMultiServerClientPort<MessageType>, SessionConnections, SessionInfo
{
//	public Set<String> getClientConnections() ;
//	
//	public Set<String> getServerConnections() ;
//	public Set<String> getMemberConnections() ;
}
