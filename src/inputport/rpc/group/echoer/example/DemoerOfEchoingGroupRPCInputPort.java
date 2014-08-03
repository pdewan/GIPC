package inputport.rpc.group.echoer.example;

import inputport.rpc.duplex.echoer.example.AliceEchoingDuplexRPCClientLauncher;
import inputport.rpc.duplex.echoer.example.BobEchoingDuplexRPCClientLauncher;
import bus.uigen.pipes.MainClassLaunchingUtility;

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
