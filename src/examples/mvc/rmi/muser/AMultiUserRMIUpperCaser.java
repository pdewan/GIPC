package examples.mvc.rmi.muser;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.Map;

import examples.mvc.rmi.collaborative.relaying.DistributedRMIEchoer;
import examples.mvc.rmi.duplex.ADistributedRMIUpperCaser;
import examples.mvc.rmi.duplex.DistributedRMICounter;

public class AMultiUserRMIUpperCaser extends ADistributedRMIUpperCaser
		implements MultiUserRMIUpperCaser {
	protected Map<String, DistributedRMICounter> nameToCounter = new HashMap();	
	protected DistributedRMICounter getCounterProxy(String aUserName) {
		try {
			DistributedRMICounter counter = nameToCounter.get(aUserName);
			if (counter == null) {

				Registry rmiRegistry = LocateRegistry.getRegistry();
				counter = (DistributedRMICounter) rmiRegistry.lookup(aUserName
						+ DistributedRMICounter.class.getName());
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
