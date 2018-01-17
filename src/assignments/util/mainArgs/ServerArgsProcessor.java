package assignments.util.mainArgs;


public class ServerArgsProcessor {
	public static final int PORT_ARG_INDEX = 0;	
	public static int getServerPort(String[] args){
		return args.length > PORT_ARG_INDEX ?
				Integer.parseInt(args[PORT_ARG_INDEX]):
					ServerPort.SERVER_PORT;
	}	
}
