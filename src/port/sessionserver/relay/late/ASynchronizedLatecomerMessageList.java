package port.sessionserver.relay.late;

import java.util.ArrayList;
import java.util.Collection;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ASynchronizedLatecomerMessageList extends ArrayList<MessageWithSource> {
	@Override
	public synchronized boolean addAll(int index, Collection<? extends MessageWithSource> aList) {
		return this.addAll(index, aList);
	}
	public synchronized boolean add(MessageWithSource aMessage) {
		return add(aMessage);
	}
}
