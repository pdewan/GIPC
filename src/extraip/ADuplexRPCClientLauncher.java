package extraip;


import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import oldtypedip.PrintingTypedReceiveListener;
import sessionport.rpc.duplex.relayed.example.Adder;
import sessionport.rpc.duplex.relayed.example.AnAdder;
import util.trace.Tracer;






public class ADuplexRPCClientLauncher {
	public static void launchRPCClient(String clientName) {	
		Tracer.showInfo(true);
		DuplexRPCClientInputPort clientInputPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort("localhost", "9090", "test client", clientName);
		PrintingTypedReceiveListener messageReceiver = new PrintingTypedReceiveListener(clientInputPort);
		clientInputPort.addConnectionListener(messageReceiver);
//		clientInputPort.addDisconnectListener(messageReceiver);

//		clientInputPort.register(messageReceiver.getClass(), messageReceiver);
//		
//		clientInputPort.addTypedReceiveListener(messageReceiver);		
		clientInputPort.addSendListener(messageReceiver);
//		clientInputPort.register(TypedReceiveListener.class, messageReceiver);
		Adder adder = new AnAdder();
		clientInputPort.register(Adder.class, adder);

		clientInputPort.connect();
//		ByteBuffer message =  ByteBuffer.wrap("hello server".getBytes());
//		System.out.println("String with padding:" + new String(message.array()));		
//		clientInputPort.send(message);
//		clientInputPort.send("hello from" + clientName);
		try {
//		Class[] parameterTypes = {Integer.class, Integer.class};
//		Method rpcMethod = Adder.class.getMethod("add", parameterTypes);
//		Serializable[] args = {5, 7};
//		clientInputPort.call(Adder.class, rpcMethod, args);

//		Method rpcMethod = RPCReceiver.class.getMethod("messageReceived", parameterTypes);
//		Serializable[] args = {clientName, "hello from " + clientName};
//		clientInputPort.call(typedMethod, args);
//		clientInputPort.call(RPCReceiver.class, rpcMethod, args);
//		Method groupAddMethod = GroupAdder.class.getMethod("messageReceived", parameterTypes);
//		clientInputPort.call(GroupAdder.class, groupAddMethod, args);
//		Integer[] args2 = { 5, 6};
//		Class[] parameterTypes2 = {Integer.class, Integer.class};
//		Method intAddMethod = Adder.class.getMethod("add", parameterTypes2);
////		Object retVal = clientInputPort.call(Adder.class, intAddMethod, args2);
//		Integer[] args3 = { 5, 6};
//		Class[] parameterTypes3 = {Integer.TYPE, Integer.TYPE};
//		Method intAddMethod2 = Adder.class.getMethod("add", parameterTypes2);
//		Object retVal2 = clientInputPort.call(Adder.class, intAddMethod, args2);
//		System.out.println(retVal2);
//		GroupAdder groupAdderProxy = (GroupAdder) RPCProxyGenerator.generateUniRPCProxy(clientInputPort, GroupAdder.class, null);
//		groupAdderProxy.groupAdd(5, 6);
		Adder adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, Adder.class, null);
		Object result = adderProxy.sum(5, 6);
		System.out.println("Result of adding:" + result);
//		DuplexAdder duplexAdderProxy = (DuplexAdder) UniRPCProxyGenerator.generateUniRPCProxy(clientInputPort, Adder.class, null);

		DuplexAdder duplexAdderProxy = (DuplexAdder) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, DuplexAdder.class, null);
		duplexAdderProxy.duplexSum(5, 6);
		
		CallingBackSquareAdder callbackAdderProxy = (CallingBackSquareAdder) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, CallingBackSquareAdder.class, null);
		RemoteAdder remoteAdder = new ARemoteAdder();
		callbackAdderProxy.add(remoteAdder, 2, 3);
		callbackAdderProxy.add(remoteAdder, 4, 2);
		
		RemoteAdderGetter remoteAdderGetter = (RemoteAdderGetter) DirectedRPCProxyGenerator.generateRPCProxy(clientInputPort, null, RemoteAdderGetter.class, null);
		RemoteAdder receivedRemoteAdder = remoteAdderGetter.getRemoteAdder();
		Object retVal = receivedRemoteAdder.sum(5, 5);
		System.out.println(retVal);
		
//		System.out.println("Result of duplex adding:" + result);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	
	public static void main (String[] args) {
		launchRPCClient("test client");
	}

}
