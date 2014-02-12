package inputport.rpc.duplex.example;

public class AnUnregisteredEchoer<EchoedType> implements AnotherEchoer<EchoedType> {

	@Override
	public void echo(EchoedType value) {
		System.out.println("Feedback:" + value);		
	}

}
