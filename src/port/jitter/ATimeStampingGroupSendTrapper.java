package port.jitter;

import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;

import java.util.Set;

import util.trace.Tracer;

public class ATimeStampingGroupSendTrapper extends AnAbstractGroupSendTrapper {
	long lastTimeStamp = 0;

	public ATimeStampingGroupSendTrapper(GroupNamingSender aDestination) {
		super(aDestination);
	}

	@Override
	public void send(Set clientNames, Object aMessage) {
		long timeStamp = System.currentTimeMillis();		
		destination.send(clientNames, 
				new AMessageWithTimeStamp(aMessage, timeStamp));
		int sendingDelay = (int) (timeStamp - lastTimeStamp);
		Tracer.info(this, "Sending delay:" + sendingDelay + " for message: " + aMessage );	
		lastTimeStamp = timeStamp;
	}

}
