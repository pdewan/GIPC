package examples.rmi.mvc.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.rmi.counter.CounterServer;
import examples.rmi.counter.RMICounterServerLauncher;
import util.trace.Tracer;

public class RMIMVCCounterServerLauncher  {	
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(RMIMVCCounterServerLauncher.class, true);
		Tracer.setDisplayThreadName(true);
	}
			
	public static void main (String[] args) {
		try {
			setTracing();
			Registry rmiRegistry = LocateRegistry.getRegistry();
			DistributedObservableCounter aCounter = new ADistributedRemotelyObservableCounter();
			
			UnicastRemoteObject.exportObject(aCounter, 0);
			rmiRegistry.rebind (CounterServer.COUNTER1, aCounter);
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
