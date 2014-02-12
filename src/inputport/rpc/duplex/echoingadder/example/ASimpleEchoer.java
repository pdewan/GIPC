package inputport.rpc.duplex.echoingadder.example;

public class ASimpleEchoer implements SimpleEchoer{

	@Override
	public void echo(String value) {
		System.out.println(value);		
	}

}
