package inputport.rpc.group.mvc.collaborative.relaying.example;

import java.util.HashMap;
import java.util.Map;

import examples.counter.Counter;
import examples.mvc.rmi.collaborative.relaying.Echoer;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.group.mvc.collaborative.example.ACollaborativeUpperCaser;

public class ARelayingCollaborativeUpperCaser extends ACollaborativeUpperCaser implements RelayingCollaborativeUpperCaser{
	protected Map<String, Echoer> nameToEchoer = new HashMap();
	public ARelayingCollaborativeUpperCaser(Counter aCounter, DuplexRPCInputPort anRPCPort) {
		super (aCounter, anRPCPort);
	}
//	public String toCountedUpperCase(String anInput) {
//		MVCTraceableInfo.newInfo("Server Computing Uppercase", this);
//		return super.toCountedUpperCase(anInput);
//}
	public void relayToOthers(String aString) {
		MVCTraceableInfo.newInfo("Server Relaying Output", this);
		String aCallerName = rpcPort.getSender();
		for (String aClient:nameToEchoer.keySet()) {
			Echoer echoer = nameToEchoer.get(aClient);
			if (!aClient.equals(aCallerName)) 
				echoer.echo(aString);			
		}		
	}
	public void addListener(Echoer anEchoer) {		
		nameToEchoer.put(rpcPort.getSender(), anEchoer);
	}
}
