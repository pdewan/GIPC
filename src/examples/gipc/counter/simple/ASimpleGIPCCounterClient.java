package examples.gipc.counter.simple;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.ATracingConnectionListener;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.buffer.ClientNameSendInitiated;
import util.trace.port.buffer.TrapperBufferReceived;
import util.trace.port.buffer.TrapperBufferSendInitiated;
import util.trace.port.nio.SocketChannelConnectFinished;
import util.trace.port.nio.SocketChannelRead;
import util.trace.port.nio.SocketChannelWritten;
import util.trace.port.objects.TrapperObjectReceived;
import util.trace.port.objects.TrapperObjectSendInitiated;
import util.trace.port.rpc.CallInitiated;
import util.trace.port.rpc.CallReceived;
import util.trace.port.rpc.ReceivedCallDequeued;
import util.trace.port.rpc.ReceivedCallEndedOld;
import util.trace.port.rpc.ReceivedCallQueued;
import util.trace.port.rpc.ReceivedObjectTransformed;
import util.trace.port.rpc.ReceivedReturnValueQueued;
import util.trace.port.rpc.RegisteredObjectLookedUp;
import util.trace.port.rpc.RemoteCallBlockedForReturnValue;
import util.trace.port.rpc.RemoteCallFinished;
import util.trace.port.rpc.RemoteCallGenerated;
import util.trace.port.rpc.RemoteCallInitiated;
import util.trace.port.rpc.RemoteCallReturnValueDetermined;
import util.trace.port.rpc.RemoteCallUnblockingWithReturnValue;
import util.trace.port.rpc.ReturnValueDequeuedOld;
import util.trace.port.rpc.ReturnValueQueuedOld;
import util.trace.port.rpc.SentObjectTransformed;
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
