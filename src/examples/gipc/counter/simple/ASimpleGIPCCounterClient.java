package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.ByteBufferReceived;
import port.trace.ByteBufferSent;
import port.trace.ClientNameSent;
import port.trace.ObjectReceived;
import port.trace.ObjectSent;
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
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		Tracer.setKeywordPrintStatus(ObjectReceived.class, true);
		Tracer.setKeywordPrintStatus(ObjectSent.class, true);
		Tracer.setKeywordPrintStatus(ClientNameSent.class, true);
		Tracer.setKeywordPrintStatus(ByteBufferSent.class, true);
		Tracer.setKeywordPrintStatus(ByteBufferReceived.class, true);

	}
	
	public static void main (String[] args) {	
		Tracer.showInfo(true);
		setTracing();
		init();
		doOperations();		
	}
}
