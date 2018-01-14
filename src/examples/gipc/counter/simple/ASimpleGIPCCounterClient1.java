package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.buffer.BufferTraceUtility;
import util.trace.port.buffer.ClientNameSendInitiated;
import util.trace.port.buffer.TrapperBufferReceived;
import util.trace.port.buffer.TrapperBufferSendInitiated;
import util.trace.port.objects.TrapperObjectReceived;
import util.trace.port.objects.TrapperObjectSendInitiated;
import util.trace.port.rpc.RPCTraceUtility;
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
