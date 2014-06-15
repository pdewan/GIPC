package inputport.rpc.duplex.echoer.example;
import bus.uigen.models.MainClassLaunchingUtility;
public class DemoerOfEchoingDuplexRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AnEchoingDuplexRPCServerLauncher.class,
				AliceEchoingDuplexRPCClientLauncher.class,	
				BobEchoingDuplexRPCClientLauncher.class	
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
}
