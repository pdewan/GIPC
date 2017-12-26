package examples.gipc.counter.simple;


import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import trace.port.buffer.BufferTraceUtility;
import trace.port.buffer.ClientNameSendInitiated;
import trace.port.buffer.TrapperBufferReceived;
import trace.port.buffer.TrapperBufferSendInitiated;
import trace.port.objects.TrapperObjectReceived;
import trace.port.objects.TrapperObjectSendInitiated;
import trace.port.rpc.RPCTraceUtility;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ASimpleGIPCCounterClient0 extends ASimpleGIPCCounterClient implements SimpleCounterClient{
	
	
	public static void main (String[] args) {	
		RPCTraceUtility.setTracing();
//		BufferTraceUtility.setTracing();
		init("Client 1");
		doOperations();		
	}
}
