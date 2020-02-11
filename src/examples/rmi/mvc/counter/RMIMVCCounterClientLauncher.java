package examples.rmi.mvc.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bus.uigen.ObjectEditor;
import examples.mvc.ACounterController;
import examples.mvc.ACounterView;
import examples.mvc.CounterController;
import examples.mvc.CounterDriver;
import examples.mvc.ModelFactory;
import examples.mvc.ObservableCounter;
import examples.rmi.counter.CounterServer;
import util.trace.Tracer;

public class RMIMVCCounterClientLauncher {
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(RMIMVCCounterClientLauncher.class, true);
		Tracer.setDisplayThreadName(true);
	}
	
	public static void main (String[] args) {	
		try {
			setTracing();
			Registry rmiRegistry = LocateRegistry.getRegistry();

			DistributedObservableCounter anObservableCounter = (DistributedObservableCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			CounterController aCounterController = new ADistributedCounterController(anObservableCounter);
			RemotePropertyChangeListener aDistributedView = new ADistributedCounterView();
			UnicastRemoteObject.exportObject(aDistributedView, 0);
			anObservableCounter.addPropertyChangeListener(aDistributedView);
			aCounterController.processInput();
			
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
//	public static void remoteEqualsIssue () {	
//		Object counter1 = null;
//		Object counter2 = null;
//		try {
//			Registry rmiRegistry = LocateRegistry.getRegistry();
//			counter1 = rmiRegistry.lookup(COUNTER1);
//			counter2 = rmiRegistry.lookup(COUNTER2);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		System.out.println(counter1.equals(counter2));
//	}
}
