package inputport.rpc.duplex.echoingadder.example;


import port.APortDescription;
import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortDescription;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import serialization.Serializer;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;
import inputport.InputPort;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.simplex.ASimplexRPCClientInputPort;
import examples.mvc.local.duplex.ACounter;

public class ADuplexAdderServerLauncher extends AnAbstractDuplexRPCServerPortLauncher implements DuplexAdderServerLauncher {
	public ADuplexAdderServerLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public void launch() {
		super.launch();
		getPortLauncherSupport().tracePrints();
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_PACKAGE_NAME);
		Tracer.setKeywordPrintStatus("inputport.rpc.duplex.echoingadder.example", true);	
	}

	protected void registerRemoteObjects() {		
		register(new ASimpleAdder());
	}
	public static void main (String[] args) {		
		(new ADuplexAdderServerLauncher(ADDER_SERVER_NAME, ADDER_SERVER_ID)).launch();
	}
}
