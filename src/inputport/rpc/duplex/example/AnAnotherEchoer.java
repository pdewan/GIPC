package inputport.rpc.duplex.example;

public class AnAnotherEchoer<EchoedType> implements AnotherEchoer<EchoedType> {

	@Override
	public void echo(EchoedType value) {
		System.out.println(value);		
	}

}
