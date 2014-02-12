package inputport.rpc.duplex.example;


import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

public class GIPCComparableCounterServer extends ComparableCounterLauncher {
	public final static String SERVER_NAME = "Echo Server";
	public final static String SERVER_PORT = "9090";
	public static void main (String[] args) {
//		(new ADuplexRPCInputPortLauncherSupport()).init();
		try {
			DuplexRPCServerInputPort aServerInputPort = 
				DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(
					SERVER_PORT, 
					SERVER_NAME);
			ComparableCounter counter1 = new AComparableCounter();
			ComparableCounter counter2 = new AComparableCounter();
			aServerInputPort.register(COUNTER1, counter1);
			aServerInputPort.register(COUNTER2, counter2);
			aServerInputPort.connect();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
