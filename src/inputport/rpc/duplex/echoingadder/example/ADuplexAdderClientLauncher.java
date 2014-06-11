package inputport.rpc.duplex.echoingadder.example;


import inputport.rpc.duplex.AnAbstractDuplexRPCClientPortLauncher;

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
//		getPortLauncherSupport().tracePrints();
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
