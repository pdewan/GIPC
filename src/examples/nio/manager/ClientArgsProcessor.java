package examples.nio.manager;

public class ClientArgsProcessor {
	public static String chooseServerHost(String[] args){
		return args.length > 0?args[0]:"localhost";
	}
	public static String chooseClientName(String[] args){
		return args.length > 1?args[1]:"Generic Client";
	}
}
