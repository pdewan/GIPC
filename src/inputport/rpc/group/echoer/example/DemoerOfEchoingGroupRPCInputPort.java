package inputport.rpc.group.echoer.example;

import inputport.rpc.duplex.echoer.example.AliceEchoingDuplexRPCClientLauncher;
import inputport.rpc.duplex.echoer.example.BobEchoingDuplexRPCClientLauncher;
import util.models.ListenableVector;
import bus.uigen.ObjectEditor;
import bus.uigen.models.AMainClassListLauncher;
import bus.uigen.models.MainClassLaunchingUtility;

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
		MainClassLaunchingUtility.interactiveLaunch(classes);
	}	

}
