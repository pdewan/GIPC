package assignments.util.mainArgs;


public class ClientArgsProcessor {
	public static final int HOST_ARG_INDEX = 0;	
	public static final int PORT_ARG_INDEX = 1;
	public static final int CLIENT_NAME_ARG_INDEX = 2;
	public static final String DEFAULT_CLIENT_NAME = "Generic Client";		
	public static String getServerHost(String[] args){
		return args.length > HOST_ARG_INDEX?
				args[HOST_ARG_INDEX]:
				"localhost";
	}
	public static int getServerPort(String[] args){
		return args.length > PORT_ARG_INDEX ?
				Integer.parseInt(args[PORT_ARG_INDEX]):
				ServerPort.SERVER_PORT;
	}
	public static String getClientName(String[] args){
		return args.length > CLIENT_NAME_ARG_INDEX?
			args[CLIENT_NAME_ARG_INDEX]:
				DEFAULT_CLIENT_NAME;
	}
}
