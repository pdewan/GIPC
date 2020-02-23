package inputport.rpc.duplex.counter.example;

import examples.counter.ACounter;

public interface DuplexCounterServerLauncher  {
	public static final Class REGISTERED_COUNTER_CLASS =  ACounter.class;
	public static String COUNTER1 = "counter1";
	public static String COUNTER2 = "counter2";
	public static final String COUNTER_SERVER_NAME = "Counter Server";
	public static final String COUNTER_SERVER_ID = "9090";

}
