package examples.gipc.counter.simple;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.ATracingConnectionListener;
import trace.port.buffer.ClientNameSendInitiated;
import trace.port.buffer.TrapperBufferReceived;
import trace.port.buffer.TrapperBufferSendInitiated;
import trace.port.nio.SocketChannelConnectFinished;
import trace.port.nio.SocketChannelRead;
import trace.port.nio.SocketChannelWritten;
import trace.port.objects.TrapperObjectReceived;
import trace.port.objects.TrapperObjectSendInitiated;
import trace.port.rpc.CallInitiated;
import trace.port.rpc.CallReceived;
import trace.port.rpc.ReceivedCallDequeued;
import trace.port.rpc.ReceivedCallEndedOld;
import trace.port.rpc.ReceivedCallQueued;
import trace.port.rpc.ReceivedObjectTransformed;
import trace.port.rpc.ReceivedReturnValueQueued;
import trace.port.rpc.RegisteredObjectLookedUp;
import trace.port.rpc.RemoteCallBlockedForReturnValue;
import trace.port.rpc.RemoteCallFinished;
import trace.port.rpc.RemoteCallGenerated;
import trace.port.rpc.RemoteCallInitiated;
import trace.port.rpc.RemoteCallReturnValueDetermined;
import trace.port.rpc.RemoteCallUnblockingWithReturnValue;
import trace.port.rpc.ReturnValueDequeuedOld;
import trace.port.rpc.ReturnValueQueuedOld;
import trace.port.rpc.SentObjectTransformed;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ASimpleGIPCCounterClient implements SimpleCounterClient{
	protected static DistributedRMICounter counter;
	protected static GIPCRegistry gipcRegistry;
	
	public static void init(String aClientName) {
		ACachingAbstractRPCProxyInvocationHandler.setInvokeObjectMethodsRemotely(false);
		gipcRegistry = GIPCLocateRegistry.getRegistry(REGISTRY_HOST_NAME, REGISTRY_PORT_NAME, aClientName);
		counter = (DistributedRMICounter) gipcRegistry.lookup(DistributedRMICounter.class, COUNTER_NAME);	
		gipcRegistry.getInputPort().addConnectionListener(new ATracingConnectionListener(gipcRegistry.getInputPort()));
	}
	public static void doOperations() {
		try {
		counter.increment(1);
		System.out.println (counter.getValue());		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

}
