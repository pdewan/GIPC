package replicatedserverport.datacomm.duplex;

import inputport.datacomm.simplex.SenderQueryable;

public class ALastSenderQueryable implements SenderQueryable {
	String lastSender;
	@Override
	public String getSender() {
		return lastSender;
	}

	@Override
	public void setSender(String newVal) {
		lastSender = newVal;
	}

}
