package port.sessionserver.relay.late;

import java.util.ArrayList;
import java.util.Collection;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
// looks like we are creating synchronizes versions of certain ArrayList operations
public class ASynchronizedLatecomerMessageList extends ArrayList<MessageWithSource> {
	@Override
	public synchronized boolean addAll(int index, Collection<? extends MessageWithSource> aList) {
//		return this.addAll(index, aList);
		return super.addAll(index, aList);

	}
	public synchronized boolean add(MessageWithSource aMessage) {
		return super.add(aMessage);
	}
}
