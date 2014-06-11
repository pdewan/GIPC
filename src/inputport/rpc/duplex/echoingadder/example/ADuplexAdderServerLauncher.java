package inputport.rpc.duplex.echoingadder.example;


import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;
import util.trace.ImplicitKeywordKind;
import util.trace.Tracer;

public class ADuplexAdderServerLauncher extends AnAbstractDuplexRPCServerPortLauncher implements DuplexAdderServerLauncher {
	public ADuplexAdderServerLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public void launch() {
		super.launch();
//		getPortLauncherSupport().tracePrints();
//		Tracer.showInfo(true);
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
