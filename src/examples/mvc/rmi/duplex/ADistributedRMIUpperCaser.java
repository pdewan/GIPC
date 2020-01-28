package examples.mvc.rmi.duplex;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.RemoteServer;

import examples.mvc.local.duplex.ADuplexUpperCaser;
import examples.mvc.rmi.collaborative.relaying.DistributedRMIEchoer;
import examples.rmi.counter.DistributedCounter;

public class ADistributedRMIUpperCaser extends ADuplexUpperCaser implements DistributedRMIUpperCaser{
	protected DistributedCounter counter; 
	DistributedRMIEchoer echoer;
	@Override
	protected Object getCounterValue() {
		return getCounterValue(getCounterProxy()); 
	}
	protected Object getCounterValue(DistributedCounter counter) {
		try {
			return counter.getValue();
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}
	protected DistributedCounter getCounterProxy()  {
		if (counter != null) return counter;
		try {			
			Registry rmiRegistry = LocateRegistry.getRegistry(RemoteServer.getClientHost());
			counter = (DistributedCounter) rmiRegistry.lookup(ADistributedRMIClientMVC_Launcher.COUNTER_NAME);			
			return counter;
			} catch (Exception e){
				e.printStackTrace();
				return null;
			}		
	}
}
