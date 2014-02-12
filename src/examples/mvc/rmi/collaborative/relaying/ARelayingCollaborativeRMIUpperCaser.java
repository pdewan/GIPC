package examples.mvc.rmi.collaborative.relaying;

import java.util.HashMap;
import java.util.Map;

import examples.mvc.rmi.collaborative.ACollaborativeRMIUpperCaser;
import examples.mvc.rmi.collaborative.DisplayLibrary;

public class ARelayingCollaborativeRMIUpperCaser extends
		ACollaborativeRMIUpperCaser implements
		RelayingCollaborativeRMIUpperCaser {
	protected Map<String, DistributedRMIEchoer> nameToEchoer = new HashMap();
	public void relayToOthers(String aString, String aCallerName) {
		for (String aClient : nameToEchoer.keySet()) {
			DistributedRMIEchoer echoer = nameToEchoer.get(aClient);
			if (!aClient.equals(aCallerName))
				try {
					echoer.echo(aString);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
	}
	public void addListener(String aName, DistributedRMIEchoer anEchoer) {
		nameToEchoer.put(aName, anEchoer);
	}
}
