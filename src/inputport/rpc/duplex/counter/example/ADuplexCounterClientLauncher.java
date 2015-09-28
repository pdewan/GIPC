package inputport.rpc.duplex.counter.example;


import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
import examples.mvc.local.duplex.Counter;

public class ADuplexCounterClientLauncher extends  AnAbstractDuplexRPCClientPortLauncher    {
	Counter counter11Proxy, counter12Proxy, counter2Proxy;
	public static final String CLIENT_NAME =  "Counter CLient";
	public ADuplexCounterClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	@Override
	protected  void createProxies() {		
    	counter11Proxy = (Counter) createProxy( DuplexCounterServerLauncher.REGISTERED_COUNTER_CLASS, DuplexCounterServerLauncher.COUNTER1);
    	counter12Proxy = (Counter) createProxy(DuplexCounterServerLauncher.REGISTERED_COUNTER_CLASS, DuplexCounterServerLauncher.COUNTER1);
    	counter2Proxy = (Counter)  createProxy( DuplexCounterServerLauncher.REGISTERED_COUNTER_CLASS, DuplexCounterServerLauncher.COUNTER2);
	}
	@Override
	protected void doPostConnectsAsyncOperations() {
		System.out.println(counter11Proxy == counter12Proxy);
		System.out.println(counter11Proxy.equals(counter12Proxy));
		System.out.println(counter11Proxy.hashCode() == counter11Proxy.hashCode());
		System.out.println(counter11Proxy.equals(counter2Proxy));
		System.out.println(counter2Proxy);
	}
	public static void main (String[] args) {		
		(new ADuplexCounterClientLauncher(CLIENT_NAME, "localhost", DuplexCounterServerLauncher.COUNTER_SERVER_ID,
				DuplexCounterServerLauncher.COUNTER_SERVER_NAME )).launch();
	}
}
