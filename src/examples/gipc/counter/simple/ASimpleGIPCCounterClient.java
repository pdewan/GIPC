package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.ObjectReceiveInfo;
import port.trace.ObjectSendInfo;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ASimpleGIPCCounterClient implements SimpleCounterClient{
	protected static DistributedRMICounter counter;
	protected static GIPCRegistry gipcRegistry;
	public static void init() {
		gipcRegistry = GIPCLocateRegistry.getRegistry(REGISTRY_HOST_NAME, REGISTRY_PORT_NAME);
		counter = (DistributedRMICounter) gipcRegistry.lookup(DistributedRMICounter.class, COUNTER_NAME);			
	}
	public static void doOperations() {
		try {
		counter.increment(1);
		System.out.println (counter.getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public static void setTracing() {
		Tracer.showInfo(true);
		TraceableInfo.setPrintTraceable(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setKeywordPrintStatus(ObjectReceiveInfo.class, true);
		Tracer.setKeywordPrintStatus(ObjectSendInfo.class, true);
	}
	
	public static void main (String[] args) {	
		Tracer.showInfo(true);
		setTracing();
		init();
		doOperations();		
	}
}
