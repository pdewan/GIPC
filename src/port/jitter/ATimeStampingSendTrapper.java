package port.jitter;

import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;
import util.trace.Tracer;


public class ATimeStampingSendTrapper extends AnAbstractSendTrapper<Object, Object> {
//	GroupNamingSender<Object> destination;
	long lastTimeStamp = 0;
	public ATimeStampingSendTrapper(NamingSender<Object> aForwarder) {
		super (aForwarder);	
	}
	@Override
	public void send(String aRemoteName, Object aMessage) {
		long timeStamp = System.currentTimeMillis();
		destination.send(aRemoteName, 
				new AMessageWithTimeStamp(aMessage, timeStamp));
//		destination.send(aRemoteName, TimeStampUtility.wrapApplicationMessageWithTimeStamp(aMessage));
		int sendingDelay = (int) (timeStamp - lastTimeStamp);
		Tracer.info(this, "For message: " + aMessage + "Sending delay:" + sendingDelay );	
		lastTimeStamp = timeStamp;		
	}	

}
