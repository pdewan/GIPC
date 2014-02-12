package inputport.rpc.duplex.echoingadder.example;

import examples.mvc.local.duplex.ACounter;
import examples.mvc.local.simplex.ASimplexUpperCaser;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;

public interface DuplexAdderServerLauncher  {
	public static final Class REGISTERED_ADDER_CLASS =  ASimpleAdder.class;
	
	public static final String ADDER_SERVER_NAME = "Adder Server";
	public static final String ADDER_SERVER_ID = "9090";

}
