package port.sessionserver.relay.late.mvc.dynamic.example;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.mvc.collaborative.example.AGroupRPCServerCollaborativeMVCLauncher;

import java.net.InetAddress;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ASessionServer;
import port.sessionserver.SessionServer;
import port.sessionserver.SessionServerLauncher;
import port.sessionserver.example.APrintingSessionObserver;
import port.sessionserverAndRelay.mvc.example.UpperCaseSession;



public class ASessionMemberMVCServerLauncher {
	
	public static void main (String[] args) {
		port.sessionserver.relay.mvc.example.ASessionMemberMVCServerLauncher.main(args);
		
	}	
}
