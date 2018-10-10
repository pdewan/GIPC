package inputport.rpc.duplex.mvc.singleuser.example;

import examples.mvc.local.duplex.ACounter;
import inputport.rpc.simplex.mvc.example.SimplexRPCClientMVCLauncher;

public interface DuplexRPCClientMVCLauncher extends SimplexRPCClientMVCLauncher  {
	public static final Class REGISTERED_COUNTER_CLASS =  ACounter.class;

}
