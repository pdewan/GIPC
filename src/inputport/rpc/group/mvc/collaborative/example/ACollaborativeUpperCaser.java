package inputport.rpc.group.mvc.collaborative.example;

import inputport.rpc.duplex.DuplexRPCInputPort;
import examples.mvc.local.duplex.ADuplexUpperCaser;
import examples.mvc.local.duplex.Counter;
import examples.mvc.rmi.collaborative.DisplayLibrary;

public class ACollaborativeUpperCaser extends ADuplexUpperCaser {
	protected DuplexRPCInputPort rpcPort;
	public ACollaborativeUpperCaser(Counter aCounter, DuplexRPCInputPort anRPCPort) {
		super (aCounter);
		rpcPort = anRPCPort;
	}	
	protected String toStringCounterValue(Object aCounterValue) {
    	return DisplayLibrary.toString( (Object[])aCounterValue);
    }
}
