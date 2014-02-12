package inputport.rpc.duplex.example;

public class ARegisteredEchoer<EchoedType> implements AnotherEchoer<EchoedType> {

	@Override
	public void echo(EchoedType value) {
		System.out.println("Message:" + value);		
	}

}
