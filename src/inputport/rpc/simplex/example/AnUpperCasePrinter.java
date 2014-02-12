package inputport.rpc.simplex.example;

import inputport.InputPort;
import inputport.datacomm.simplex.object.example.AnUpperCaseObjectReceiveListener;
import inputport.rpc.RPCInputPort;



public class AnUpperCasePrinter extends AnUpperCaseObjectReceiveListener implements UpperCasePrinter{
	public AnUpperCasePrinter(RPCInputPort anInputPort) {
		super(anInputPort);
	}

	@Override
	public void printUppercase(String string) {
		System.out.println(computeResponse(string));		
	}
}
