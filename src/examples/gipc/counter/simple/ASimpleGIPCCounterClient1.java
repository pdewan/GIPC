package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.ByteBufferReceived;
import port.trace.ByteBufferSendInitiated;
import port.trace.ClientNameSendInitiated;
import port.trace.ObjectReceived;
import port.trace.ObjectSendInitiated;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ASimpleGIPCCounterClient1 extends ASimpleGIPCCounterClient implements SimpleCounterClient{
	
	
	public static void main (String[] args) {	
		Tracer.showInfo(true);
		setTracing();
		init("Client 1");
		doOperations();		
	}
}
