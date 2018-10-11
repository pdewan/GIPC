package examples.gipc.counter.simple;

import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;
import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;

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
