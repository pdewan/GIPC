package inputport.rpc.duplex.echoer.example;

import examples.mvc.local.simplex.ASimplexUpperCaser;

public interface EchoingDuplexServerLauncher {
	public static final Class REGISTERED_UPPER_CASER_CLASS =  ASimplexUpperCaser.class;
	public static final String ECHOER_SERVER_NAME = "Echoer Server";
	public static final String ECHOER_SERVER_ID = "9190";
}