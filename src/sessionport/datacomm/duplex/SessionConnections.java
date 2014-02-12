package sessionport.datacomm.duplex;

import java.util.Set;

public interface SessionConnections {
    public Set<String> getClientConnections() ;
	
	public Set<String> getServerConnections() ;
	public Set<String> getMemberConnections() ;
}
