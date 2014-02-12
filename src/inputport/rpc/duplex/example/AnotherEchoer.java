package inputport.rpc.duplex.example;

import java.rmi.Remote;

public interface AnotherEchoer<EchoedType> {
	void echo(EchoedType value);

}
