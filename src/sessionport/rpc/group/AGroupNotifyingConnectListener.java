package sessionport.rpc.group;

import java.util.Arrays;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.group.GroupRPCProxyGenerator;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.relayed.example.ACallingConnectListener;
import sessionport.rpc.duplex.relayed.example.Adder;

public class AGroupNotifyingConnectListener extends ACallingConnectListener implements ConnectionListener{
	public AGroupNotifyingConnectListener(DuplexRPCSessionPort aSessionPort) {
		super(aSessionPort);
	}
	


//	@Override
//	public void connected(String remoteEnd, ConnectionType aConnectionType) {
//		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION ) { // connected to self
//		System.out.println("Connected as client to: " + remoteEnd);
//		Adder adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, Adder.class, null);
//
//		adderProxy.printSum(5, 6);		
//		}
//	}
//
//	@Override
//	public void disconnected(String remoteEndName,
//			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
//		
//	}
//
//	@Override
//	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
//		
//	}

}
