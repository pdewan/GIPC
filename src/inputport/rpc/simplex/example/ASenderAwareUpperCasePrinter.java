package inputport.rpc.simplex.example;

import inputport.rpc.simplex.SimplexRPCServerInputPort;


public class ASenderAwareUpperCasePrinter extends AnUpperCasePrinter implements UpperCasePrinter{	
	SimplexRPCServerInputPort serverInputPort;
	public ASenderAwareUpperCasePrinter(SimplexRPCServerInputPort aSimplexInputPort) {
		super(aSimplexInputPort);
		serverInputPort = aSimplexInputPort;
	}	
	@Override
	public void printUppercase(String string) {
		System.out.println(serverInputPort.getSender() + "<--" + serverInputPort.getLocalName() + ":" + computeResponse(string));	
	}
	
}
