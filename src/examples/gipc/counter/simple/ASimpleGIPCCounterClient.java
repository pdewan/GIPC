package examples.gipc.counter.simple;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.ATracingConnectionListener;
import port.trace.buffer.TrapperBufferReceived;
import port.trace.buffer.TrapperBufferSendInitiated;
import port.trace.buffer.ClientNameSendInitiated;
import port.trace.buffer.nio.SocketChannelConnectFinished;
import port.trace.buffer.nio.SocketChannelRead;
import port.trace.buffer.nio.SocketChannelWritten;
import port.trace.objects.TrapperObjectReceived;
import port.trace.objects.TrapperObjectSendInitiated;
import port.trace.rpc.ReceivedCallDequeued;
import port.trace.rpc.CallGenerated;
import port.trace.rpc.CallInitiated;
import port.trace.rpc.ReceivedCallQueued;
import port.trace.rpc.CallReceived;
import port.trace.rpc.ReceivedCallEndedOld;
import port.trace.rpc.ReceivedObjectTransformed;
import port.trace.rpc.ReceivedReturnValueDequeued;
import port.trace.rpc.ReceivedReturnValueQueued;
import port.trace.rpc.RegisteredObjectLookedUp;
import port.trace.rpc.RemoteCallBlockedForReturnValue;
import port.trace.rpc.RemoteCallFinished;
import port.trace.rpc.RemoteCallInitiated;
import port.trace.rpc.RemoteCallReturnValueDetermined;
import port.trace.rpc.ReturnValueDequeuedOld;
import port.trace.rpc.ReturnValueQueuedOld;
import port.trace.rpc.SentObjectTransformed;
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
