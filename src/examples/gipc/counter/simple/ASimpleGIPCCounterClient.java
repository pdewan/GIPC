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
import util.trace.port.nio.SocketChannelConnected;
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

public class ASimpleGIPCCounterClient implements SimpleCounterClient {
	protected static DistributedRMICounter counter;
	protected static GIPCRegistry gipcRegistry;
	public static final int RPC_INCREMENT = 1; // value passed by rpc call to
												// server increment operation

	public static void init(String aClientName) {
		ACachingAbstractRPCProxyInvocationHandler
				.setInvokeObjectMethodsRemotely(false);
		gipcRegistry = GIPCLocateRegistry.getRegistry(SERVER_HOST_NAME,
				SERVER_PORT, aClientName);
		if (gipcRegistry == null) {
			System.err.println("Could not connect to server :"
					+ SERVER_HOST_NAME + ":" + SERVER_PORT);
			System.exit(-1);
		}
		counter = (DistributedRMICounter) gipcRegistry.lookup(
				DistributedRMICounter.class, COUNTER_NAME);

		gipcRegistry.getInputPort().addConnectionListener(
				new ATracingConnectionListener(gipcRegistry.getInputPort()));
	}

	public static void doOperations() {
		try {
			System.out.println("Making RPC call with increment:"
					+ RPC_INCREMENT);
			counter.increment(RPC_INCREMENT);
			System.out.println("Making RPC call to get counter value");
			System.out
					.println("Returned remote value:" + counter.getValue());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
