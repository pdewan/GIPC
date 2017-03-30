package examples.gipc.counter.group;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Set;

import inputport.rpc.GIPCRegistry;
import port.ATracingConnectionListener;
import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleRegistryAndCounterServer;

public class ACounterSessionMember implements SimpleRegistryAndCounterServer {
	protected static DistributedRMICounter counter;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static Integer NUM_MEMBERS_TO_WAIT_FOR = 3;

	protected static void init(String aMyName, int aPortNumber) {
		gipcRegistry = GIPCLocateSessionRegistry.createSessionRegistry(
				"mysession", "localhost", aPortNumber, aMyName,
				SessionChoice.P2P, NUM_MEMBERS_TO_WAIT_FOR);
		counter = new ADistributedInheritingRMICounter();
		gipcRegistry.rebind(COUNTER_NAME, counter);
		gipcRegistry.getInputPort().addConnectionListener(
				new ATracingConnectionListener(gipcRegistry.getInputPort()));
	}

	public static void doOperations() {
		// DistributedRMICounter aProxy = (DistributedRMICounter)
		// gipcRegistry.lookupAllRemoteButCallerProxy(COUNTER_NAME);
		
		try {
			Set<String> aMembers = gipcRegistry.getAllRemoteMembersAndMe();
			for (String aMember : aMembers) {
				DistributedRMICounter aMemberCounter = (DistributedRMICounter) gipcRegistry
						.lookupMemberProxy(aMember, COUNTER_NAME);
				aMemberCounter.increment(Integer.parseInt(aMember));
			}
			DistributedRMICounter anAllRemoteCounter = (DistributedRMICounter) gipcRegistry
						.lookupAllRemoteProxy(COUNTER_NAME);
//			anAllRemoteCounter.increment(2);
			Object aCounterValues =  anAllRemoteCounter.getValue();
			System.out.println("Counter values:" +aCounterValues);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		// BufferTraceUtility.setTracing();
		// RPCTraceUtility.setTracing();
		init("1", 7000);
		doOperations();
	}

}
