package inputport;

import java.util.Set;

public interface QueryablePort extends ConnectionsQueryable{
	String getLocalName();
	Set<String> getConnections();
	boolean isConnected(String remoteName);
}
