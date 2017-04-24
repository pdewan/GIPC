package port.relay;

import java.util.Collection;
import java.util.Set;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface Relayer {
//	void relay(String aSessionName, String remoteEnd, MessageWithSource message);	
//	void relay(String sessionname, Set<String> remoteEnds, MessageWithSource message);
//	void relayOthers(String aSessionName, MessageWithSource message);
//	
	void relay(String remoteEnd, MessageWithSource message);	
	void relay(Collection<String> remoteEnds, MessageWithSource message);
	void relayOthers(MessageWithSource message);


}
