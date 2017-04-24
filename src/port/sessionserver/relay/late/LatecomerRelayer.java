package port.sessionserver.relay.late;

import java.util.Collection;
import java.util.Set;

import port.relay.Relayer;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
public interface LatecomerRelayer extends Relayer {

//	void relay(String aSessionName, String remoteEnd, MessageWithSource message);	
	void relay(String sessionname, Collection<String> remoteEnds, MessageWithSource message);
//	void relayOthers(String aSessionName, MessageWithSource message);

}
