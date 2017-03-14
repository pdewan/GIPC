package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.buffer.BufferTraceUtility;
import port.trace.buffer.TrapperBufferReceived;
import port.trace.buffer.TrapperBufferSendInitiated;
import port.trace.buffer.ClientNameSendInitiated;
import port.trace.objects.TrapperObjectReceived;
import port.trace.objects.TrapperObjectSendInitiated;
import port.trace.rpc.RPCTraceUtility;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ASimpleGIPCCounterClient1 extends ASimpleGIPCCounterClient implements SimpleCounterClient{
	
	
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
		BufferTraceUtility.setTracing();
		init("Client 1");
		doOperations();		
	}
}
