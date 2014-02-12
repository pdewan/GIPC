package inputport.rpc.duplex.echoingadder.example;


import port.APortDescription;
import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortDescription;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import serialization.Serializer;
import util.trace.Tracer;
import inputport.InputPort;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.simplex.ASimplexRPCClientInputPort;
import examples.mvc.local.duplex.Counter;

public class ADuplexAdderClientLauncher extends  AnAbstractDuplexRPCClientPortLauncher    {
	SimpleAdder adderProxy;
	public static final String CLIENT_NAME =  "Adder CLient";
	public ADuplexAdderClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}

	protected  void createProxies() {		
		adderProxy = (SimpleAdder) createProxy( DuplexAdderServerLauncher.REGISTERED_ADDER_CLASS);
    	
	}
	
	public void launch() {
		super.launch();
		getPortLauncherSupport().tracePrints();
//		getPortLauncherSupport().tracePrints();
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeywordPrintStatus(Serializer.class, true);

	}
	
	protected void doPostConnectsAsyncOperations() {
		SimpleEchoer echoer = new ASimpleEchoer();		
		System.out.println(adderProxy.add(echoer, 5, 6));
		
	}
	public static void main (String[] args) {		
		(new ADuplexAdderClientLauncher(CLIENT_NAME, "localhost", DuplexAdderServerLauncher.ADDER_SERVER_ID,
				DuplexAdderServerLauncher.ADDER_SERVER_NAME )).launch();
	}
}
