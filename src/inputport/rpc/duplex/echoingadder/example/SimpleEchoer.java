package inputport.rpc.duplex.echoingadder.example;

import java.rmi.Remote;

public interface SimpleEchoer extends Remote {
	void echo(String value);

}
