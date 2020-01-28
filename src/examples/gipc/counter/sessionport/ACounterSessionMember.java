package examples.gipc.counter.sessionport;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;
import java.util.Set;

import examples.gipc.counter.layers.AMultiLayeServerReceiveListener;
import examples.rmi.counter.ADistributedCounter;
import examples.rmi.counter.DistributedCounter;
import port.ATracingConnectionListener;
import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;

public class ACounterSessionMember implements CounterSessionMember {
	protected static DistributedCounter counter;
	protected static GIPCSessionRegistry gipcRegistry;
	protected static GroupRPCSessionPort groupRPCSessionPort;
	protected static short numMembersToWaitFor = 3;
	protected static SessionChoice sessionChoice = SessionChoice.P2P;

	protected static void init(String aMyName, int aPortNumber) {
		gipcRegistry = GIPCLocateSessionRegistry.createOrGetSessionRegistry(
				"mysession", "localhost", aPortNumber, aMyName,
				sessionChoice, 
				numMembersToWaitFor);
		counter = new ADistributedCounter();
		gipcRegistry.rebind(COUNTER_NAME, counter);
		groupRPCSessionPort = gipcRegistry.getSessionPort();
		
	}
	protected static void addListeners() {
		groupRPCSessionPort.addConnectionListener(
				new ATracingConnectionListener(groupRPCSessionPort));
		groupRPCSessionPort.addReceiveListener(new AMultiLayeServerReceiveListener(counter));		

	}
	protected static void sendObjects() {
		groupRPCSessionPort.sendAllRemoteMembers(2);		
	}
	protected static void sendByteBuffers() {	
		ByteBuffer aByteBuffer = ByteBuffer.wrap("3".getBytes());
		groupRPCSessionPort.sendAllMembers(aByteBuffer);		
	}

	public static void doOperations() {
		// DistributedRMICounter aProxy = (DistributedRMICounter)
		// gipcRegistry.lookupAllRemoteButCallerProxy(COUNTER_NAME);
		
		try {
			Set<String> aMembers = gipcRegistry.getAllMembers();
			for (String aMember : aMembers) {
				DistributedCounter aMemberCounter = (DistributedCounter) gipcRegistry
						.lookup(aMember, COUNTER_NAME);
				aMemberCounter.increment(Integer.parseInt(aMember));
			}
			DistributedCounter anAllRemoteCounter = (DistributedCounter) gipcRegistry
						.lookupAllRemote(COUNTER_NAME);
//			anAllRemoteCounter.increment(2);
			System.out.println("All remote counter values:" + anAllRemoteCounter.getValue());
			DistributedCounter anAllRemoteAndMeCounter = (DistributedCounter) gipcRegistry
					.lookupAll(COUNTER_NAME);
//		anAllRemoteCounter.increment(2);
		System.out.println("All remote and me counter values:" + anAllRemoteAndMeCounter.getValue());
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	public static void beSessionMember(String aMemberName, int aMemberPort) {
		init(aMemberName, aMemberPort);
		addListeners();
		sendByteBuffers();
		sendObjects();
		doOperations();
	}
	public static void main(String[] args) {
		
		beSessionMember("1", 7000);
//		addListeners();
//		sendByteBuffers();
//		sendObjects();
//		doOperations();
	}

}
