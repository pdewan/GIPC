package examples.gipc.mvc.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.gipc.counter.AGIPCCounterServer;
import examples.rmi.counter.CounterServer;
import examples.rmi.counter.RMICounterServerLauncher;
import examples.rmi.mvc.counter.ADistributedRemotelyObservableCounter;
import examples.rmi.mvc.counter.DistributedObservableCounter;
import examples.rmi.mvc.counter.RMIMVCCounterServerLauncher;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import util.trace.Tracer;

public class GIPCMVCCounterServerLauncher extends AGIPCCounterServer  {	
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(GIPCMVCCounterServerLauncher.class, true);
		Tracer.setKeywordPrintStatus(RMIMVCCounterServerLauncher.class, true);

		Tracer.setDisplayThreadName(true);
		Tracer.setDisplayTime(true);
	}
			
	public static void main (String[] args) {
		try {
			setTracing();
			GIPCRegistry aRegistry = GIPCLocateRegistry.createRegistry(SERVER_PORT);
			DistributedObservableCounter aCounter = new ADistributedRemotelyObservableCounter();
			
//			UnicastRemoteObject.exportObject(aCounter, 0);
			aRegistry.rebind (CounterServer.COUNTER1, aCounter);
			

		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
