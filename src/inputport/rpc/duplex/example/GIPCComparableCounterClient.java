package inputport.rpc.duplex.example;


import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;

public class GIPCComparableCounterClient extends ComparableCounterLauncher  {
	public final static String SERVER_NAME = "Echo Server";
	public final static String SERVER_PORT = "9090";
	public static void main (String[] args) {	
		try {
			(new ADuplexRPCInputPortLauncherSupport()).init();

			DuplexRPCClientInputPort aClientInputPort = 
				DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
					"localhost", SERVER_PORT, SERVER_NAME, 	"counter client");
			RPCProxyGenerator rpcProxyGenerator = aClientInputPort.getRPCProxyGenerator();
			ComparableCounter counter11 = (ComparableCounter) 
			  rpcProxyGenerator.generateRPCProxy(ComparableCounter.class, COUNTER1);
			ComparableCounter counter12 = (ComparableCounter) 
			  rpcProxyGenerator.generateRPCProxy(ComparableCounter.class, COUNTER1);
			ComparableCounter counter2 = (ComparableCounter) 
			  rpcProxyGenerator.generateRPCProxy(ComparableCounter.class, COUNTER2);
			aClientInputPort.connect();
			ComparableCounter greaterCounter = counter11.greater(counter11);
			System.out.println(greaterCounter == counter11);
			System.out.println(greaterCounter.equals(counter11));
			System.out.println(counter12 == counter11);
			System.out.println(counter12.equals(counter11));
			System.out.println(counter11.hashCode() == counter12.hashCode());
			System.out.println(greaterCounter.hashCode() == counter11.hashCode());
			System.out.println(counter11.equals(counter2));
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
