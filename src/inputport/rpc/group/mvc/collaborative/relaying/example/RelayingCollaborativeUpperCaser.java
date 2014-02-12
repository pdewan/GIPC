package inputport.rpc.group.mvc.collaborative.relaying.example;

import examples.mvc.local.duplex.DuplexUpperCaser;
import examples.mvc.rmi.collaborative.relaying.Echoer;

public interface RelayingCollaborativeUpperCaser extends DuplexUpperCaser{
	void addListener(Echoer anEchoer);
	void relayToOthers(String aString);

}
