package examples.gipc.counter.customization;

import util.trace.Tracer;
import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;

public class ACustomReceiveNotifier extends AReceiveRegistrarAndNotifier{
	/**
	 * Lets the super class notify the listeners, simply traces the call
	 */
	@Override
	public void notifyPortReceive (String aSource, Object aMessage) {	
		System.out.println (aSource + "->" + aMessage);
		super.notifyPortReceive(aSource, aMessage);
	}
}
