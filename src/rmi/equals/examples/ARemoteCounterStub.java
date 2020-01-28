package rmi.equals.examples;

import java.lang.reflect.Method;
import java.rmi.RemoteException;

import examples.rmi.counter.DistributedCounter;
import inputport.rpc.NamingRPC;


public class ARemoteCounterStub implements DistributedCounter {
	NamingRPC rpcPort;
	String destination;
	public void init(NamingRPC anRPCPort, String aDestination) {
		rpcPort = anRPCPort;
		destination = aDestination;
	}
	public Object getValue() {	
		try {		
			Method method = DistributedCounter.class.getMethod("getValue");
			Object[] args = {};
			return (Integer) rpcPort.call(destination, DistributedCounter.class.getName(), method, args);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
	@Override
	public void increment(int val) throws RemoteException {
		try {		
			Method method = DistributedCounter.class.getMethod("increment", Integer.TYPE);
			Object[] args = {val};
			rpcPort.call(destination, DistributedCounter.class.getName(), method, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main (String[] args) {
		ARemoteCounterStub counter = new ARemoteCounterStub();
		counter.init(null, null);		
		try {
			counter.getValue();
			counter.increment(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			Class counterStubClass = null;
			ARemoteCounterStub counterStub = (ARemoteCounterStub) counterStubClass.newInstance();
			counter.increment(5);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
