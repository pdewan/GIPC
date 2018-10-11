package inputport.rpc.group.echoer.example;

import bus.uigen.pipe.MainClassLaunchingUtility;
import inputport.rpc.duplex.echoer.example.AliceEchoingDuplexRPCClientLauncher;
import inputport.rpc.duplex.echoer.example.BobEchoingDuplexRPCClientLauncher;

public class DemoerOfEchoingGroupRPCInputPort {
	public static void main(String args[]) {
		demo();
	}	
	public static void demo() {		
		Class[] classes = {
				AnEchoingGroupRPCServerCollaborativeLauncher.class,
				AliceEchoingDuplexRPCClientLauncher.class,
				BobEchoingDuplexRPCClientLauncher.class
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}	

}
