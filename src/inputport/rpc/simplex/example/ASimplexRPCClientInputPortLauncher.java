package inputport.rpc.simplex.example;


import inputport.InputPort;
import inputport.datacomm.simplex.buffer.example.UserInterfaceManager;
import inputport.datacomm.simplex.object.example.ASimplexObjectClientInputPortLauncher;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.simplex.ASimplexRPCInputPortLauncherSupport;
import inputport.rpc.simplex.SimplexRPCClientInputPort;
import inputport.rpc.simplex.SimplexRPCInputPortSelector;
import port.PortLauncherSupport;





public class ASimplexRPCClientInputPortLauncher extends ASimplexObjectClientInputPortLauncher  {
	public ASimplexRPCClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}

	protected UpperCasePrinter upperCaseProxy;
	protected PortLauncherSupport getPortLauncherSupport() {
		return new ASimplexRPCInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return SimplexRPCInputPortSelector.createSimplexRPCClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	@Override
	protected  void createProxies() {
		RPCProxyGenerator rpcProxyGenerator = ((SimplexRPCClientInputPort) mainPort).getRPCProxyGenerator();
    	upperCaseProxy = (UpperCasePrinter) rpcProxyGenerator.generateRPCProxy(null, AnUpperCasePrinter.class, null);

	}
	protected UserInterfaceManager createUserInterfaceManager(InputPort anInputPort) {
		return new ASimplexRPCFrostyConsoleUI(upperCaseProxy);
	}
	
//	@Override
//	public void createUI (InputPort anInputPort) {
//		SimplexRPCClientInputPort aClientInputPort = (SimplexRPCClientInputPort) anInputPort;
//		String stringMessage1;
////		try {			
//		
////		Scanner in = new Scanner(System.in);	
//		while (true) {
//			System.out.println("Please enter a message to be concatenated by the server:");
////		    stringMessage1  = in.nextLine();
//		    stringMessage1  = Console.readString();
//
//
//		   upperCaseProxy.printUppercase(stringMessage1);    	
//		    	
//
//		}
////		} catch (Exception e) {
////			e.printStackTrace();
////			return;
////		}
//	}	
	
	
	
//	public static final String SERVER_HOST = "localhost";
//	public static void launch (String clientName) {
//		setTracing();
//		SimplexRPCClientInputPort aClientInputPort = 
//			SimplexRPCInputPortSelector.createSimplexRPCClientInputPort(
//				SERVER_HOST, SERVER_PORT, SERVER_NAME, 	clientName);
//		ASimplexObjectClientInputPortLauncher.addListeners(aClientInputPort);
////		addListeners(aClientInputPort);
//		aClientInputPort.connect();
//		processInput(aClientInputPort);		
//	}
	
//	public static void addListeners(SimplexClientInputPort<Object> aClientInputPort) {
//		aClientInputPort.addConnectionListener(
//			new ATracingConnectionListener(aClientInputPort));
//		aClientInputPort.addSendListener(
//				new ATracingSendListener(aClientInputPort));
//	}
//	public static void processInput (SimplexRPCClientInputPort aClientInputPort) {
//		String stringMessage1;
//		String stringMessage2;
//		RPCProxyGenerator rpcProxyGenerator = aClientInputPort.getRPCProxyGenerator();
//		try {
//	    	SumPrinter adderProxy = (SumPrinter) rpcProxyGenerator.generateRPCProxy(null, SumPrinter.class, null);
//
//			
//		
//		Scanner in = new Scanner(System.in);	
//		while (true) {
//			System.out.println("Please enter two messages:");
//		    stringMessage1  = in.nextLine();
//		    stringMessage2 = in.nextLine();
////		    try {		    	
////		    	SumPrinter adderProxy = (SumPrinter) StaticRPCProxyGenerator.generateRPCProxy(aClientInputPort, null, SumPrinter.class, null);
//
//		    	adderProxy.printSum(stringMessage1, stringMessage2); 	
//		    	
////		    			
////		    } catch (Exception e) {
////		    		e.printStackTrace();
////		    }
//		}
//		} catch (Exception e) {
//			e.printStackTrace();
//			return;
//		}
//	}	
//	
//	
//	
//	
//	public static void main (String[] args) {
//		launch("test client");
//	}

}
