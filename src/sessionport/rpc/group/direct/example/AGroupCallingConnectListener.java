package sessionport.rpc.group.direct.example;

import java.util.Arrays;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.group.GroupRPCProxyGenerator;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import sessionport.rpc.duplex.relayed.example.ACallingConnectListener;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.group.GroupRPCSessionPort;

public class AGroupCallingConnectListener extends ACallingConnectListener implements ConnectionListener{
	public AGroupCallingConnectListener(DuplexRPCSessionPort aSessionPort) {
		super(aSessionPort);
	}
	protected Adder createAdderProxy(String remoteEnd) {
		if (adderProxy != null) {
			return adderProxy;
		}
		return (Adder) GroupRPCProxyGenerator.generateAllRPCProxy((GroupRPCSessionPort) sessionPort,  Adder.class, null);
	}
	
	protected void processObjectSum(Object aSum) {
		if (aSum instanceof Object[]) {
			System.out.println ("Object Sum:" + Arrays.toString((Object[]) aSum) );

		}
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
