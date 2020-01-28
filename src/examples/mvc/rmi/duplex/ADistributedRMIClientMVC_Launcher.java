package examples.mvc.rmi.duplex;


import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import examples.mvc.local.duplex.ADuplexFrostyLauncher;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.simplex.ProgramLauncher;
import examples.rmi.counter.ADistributedCounter;
import examples.rmi.counter.DistributedCounter;

public class ADistributedRMIClientMVC_Launcher extends ADuplexFrostyLauncher implements ProgramLauncher{
	protected String name;
	protected Registry serverRMIRegistry;
	public static final String COUNTER_NAME = DistributedCounter.class.getName();	
	public ADistributedRMIClientMVC_Launcher(String aName, String aServerHost) {
		name = aName;
		try {
		   serverRMIRegistry = LocateRegistry.getRegistry(aServerHost);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected String upperCaserName() {
		return ADistributedRMIServerMVC_Launcher.UPPER_CASER_NAME;
	}
	@Override
	protected DuplexFrostyModel getFrostyModel() {
		try {
		DistributedRMIUpperCaser upperCasePrinter = 
			(DistributedRMIUpperCaser) serverRMIRegistry.lookup(upperCaserName());
		return new ADistributedRMIFrostyModel(upperCasePrinter,  (DistributedCounter) getCounter());

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}		
	}	
	protected String counterName() {
		return COUNTER_NAME;
	}
	@Override
	protected Counter getCounter() {
		DistributedCounter counter = new ADistributedCounter();
		try {
		UnicastRemoteObject.exportObject(counter, 0);
		Registry clientRMIRegistry = LocateRegistry.getRegistry();
		clientRMIRegistry.rebind(counterName(), counter);			
		} catch (Exception e) {
			e.printStackTrace();			
		}
		return (Counter) counter;
	}

	public static void main (String[] args) {
		(new ADistributedRMIClientMVC_Launcher("Client", "localhost")).launch();
	}
	

}
