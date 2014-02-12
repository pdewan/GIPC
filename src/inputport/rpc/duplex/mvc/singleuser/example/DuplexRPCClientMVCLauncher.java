package inputport.rpc.duplex.mvc.singleuser.example;

import inputport.rpc.simplex.mvc.example.SimplexRPCClientMVCLauncher;
import examples.mvc.local.duplex.ACounter;

public interface DuplexRPCClientMVCLauncher extends SimplexRPCClientMVCLauncher  {
	public static final Class REGISTERED_COUNTER_CLASS =  ACounter.class;

}
