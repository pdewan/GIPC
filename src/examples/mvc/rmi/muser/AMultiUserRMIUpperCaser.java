package examples.mvc.rmi.muser;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import examples.mvc.rmi.duplex.ADistributedRMIUpperCaser;
import examples.rmi.counter.DistributedCounter;

public class AMultiUserRMIUpperCaser extends ADistributedRMIUpperCaser
		implements MultiUserRMIUpperCaser {
	protected Map<String, DistributedCounter> nameToCounter = new HashMap();	
	protected DistributedCounter getCounterProxy(String aUserName) {
		try {
			DistributedCounter counter = nameToCounter.get(aUserName);
			if (counter == null) {

				Registry rmiRegistry = LocateRegistry.getRegistry();
				counter = (DistributedCounter) rmiRegistry.lookup(aUserName
						+ DistributedCounter.class.getName());
				nameToCounter.put(aUserName, counter);
			}
			return counter;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	protected Object getCounterValue(String aCallerName) {
		return getCounterValue(getCounterProxy(aCallerName));
	}	
	public String toCountedUpperCase(String anInput, String aCallerName) {
		return toUpperCase(anInput)
				+ counterFeedback(getCounterValue(aCallerName));
	}

	

}
