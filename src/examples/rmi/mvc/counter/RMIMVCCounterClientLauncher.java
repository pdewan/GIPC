package examples.rmi.mvc.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bus.uigen.ObjectEditor;
import examples.mvc.counter.ACounterController;
import examples.mvc.counter.ACounterView;
import examples.mvc.counter.CounterController;
import examples.mvc.counter.CounterDriver;
import examples.mvc.counter.ModelFactory;
import examples.mvc.counter.ObservableCounter;
import examples.rmi.counter.CounterServer;
import util.trace.Tracer;

public class RMIMVCCounterClientLauncher {
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(RMIMVCCounterClientLauncher.class, true);
		Tracer.setDisplayThreadName(true);
		Tracer.setDisplayTime(true);
	}
	
	public static void main (String[] args) {	
		try {
			setTracing();
			Registry rmiRegistry = LocateRegistry.getRegistry();

			DistributedObservableCounter anObservableCounter = (DistributedObservableCounter) rmiRegistry.lookup(CounterServer.COUNTER1);
			CounterController aCounterController = new ADistributedCounterController(anObservableCounter);
			RemotePropertyChangeListener aDistributedView = new ADistributedCounterView(anObservableCounter);
			UnicastRemoteObject.exportObject(aDistributedView, 0);
			anObservableCounter.addPropertyChangeListener(aDistributedView);
//			ObjectEditor.edit(anObservableCounter);
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
