package examples.gipc.mvc.counter;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import bus.uigen.ObjectEditor;
import examples.gipc.counter.AGIPCCounterClient;
import examples.mvc.counter.ACounterController;
import examples.mvc.counter.ACounterView;
import examples.mvc.counter.CounterController;
import examples.mvc.counter.CounterDriver;
import examples.mvc.counter.ModelFactory;
import examples.mvc.counter.ObservableCounter;
import examples.rmi.counter.CounterServer;
import examples.rmi.mvc.counter.ADistributedCounterController;
import examples.rmi.mvc.counter.ADistributedCounterView;
import examples.rmi.mvc.counter.ADistributedRemotelyObservableCounter;
import examples.rmi.mvc.counter.DistributedObservableCounter;
import examples.rmi.mvc.counter.RMIMVCCounterClientLauncher;
import examples.rmi.mvc.counter.RemotePropertyChangeListener;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import util.trace.Tracer;

public class GIPCMVCCounterClientLauncher extends AGIPCCounterClient {
	protected static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(GIPCMVCCounterClientLauncher.class, true);
		Tracer.setKeywordPrintStatus(RMIMVCCounterClientLauncher.class, true);

		Tracer.setDisplayThreadName(true);
		Tracer.setDisplayTime(true);
	}
	
	public static void main (String[] args) {	
		try {
			setTracing();
			GIPCRegistry rmiRegistry = GIPCLocateRegistry.getRegistry(SERVER_HOST_NAME, SERVER_PORT, "client");

			DistributedObservableCounter anObservableCounter = (DistributedObservableCounter) rmiRegistry.lookup(ADistributedRemotelyObservableCounter.class, CounterServer.COUNTER1);
			CounterController aCounterController = new ADistributedCounterController(anObservableCounter);
			RemotePropertyChangeListener aDistributedView = new ADistributedCounterView(anObservableCounter);
//			UnicastRemoteObject.exportObject(aDistributedView, 0);
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
