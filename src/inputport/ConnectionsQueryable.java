package inputport;

import java.util.Set;

public interface ConnectionsQueryable {
//	public Set<String> getConnections();
	Set<String> getConnections();
	boolean isConnected(String remoteName);

}
