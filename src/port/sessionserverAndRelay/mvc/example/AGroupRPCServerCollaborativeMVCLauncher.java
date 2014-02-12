package port.sessionserverAndRelay.mvc.example;

import port.PortLauncherSupport;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import inputport.rpc.group.mvc.collaborative.example.ACollaborativeUpperCaser;
import examples.mvc.local.duplex.Counter;
import examples.mvc.local.simplex.SimplexUpperCaser;



public class AGroupRPCServerCollaborativeMVCLauncher  {

	public static void main (String[] args) {
		inputport.rpc.group.mvc.collaborative.example.
		AGroupRPCServerCollaborativeMVCLauncher.main(args);
//		(new AGroupRPCServerCollaborativeMVCLauncher()).launch();
	}	
}
