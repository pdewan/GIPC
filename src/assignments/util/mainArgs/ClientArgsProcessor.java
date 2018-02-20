package assignments.util.mainArgs;

/** 
 * Example client args supported by this library:
 * 
 * localhost 9001 Alice |
 * dewan-t431.cs.unc.edu 9001 Alice |
 * dewan-t431.cs.unc.edu 9001 |
 * localhost
 * 
 * Pass the main args of your client to the various methods of this class to
 * get the specified or defiult values * 
 *
 */
public class ClientArgsProcessor {
	public static final int HOST_ARG_INDEX = 0;	
	public static final int PORT_ARG_INDEX = 1;
	public static final int CLIENT_NAME_ARG_INDEX = 2;
	public static final String DEFAULT_CLIENT_NAME = "Generic Client";
	public static final String DEFAULT_HEADLESS_VALUE = "false";

	public static final int HEADLESS_ARG_INDEX = 3;
	public static final int REGISTRY_HOST_ARG_INDEX = 4;
	
	
	/**
	 * Extracts the server hostname from argument #0,  if it exists, returns default
	 * hostname (localhost) otherwise
	 */
	public static String getServerHost(String[] args){
		return args.length > HOST_ARG_INDEX?
				args[HOST_ARG_INDEX]:
				"localhost";
	}	
	/**
	 * Extracts the server port from argument #1,  if it exists, returns default
	 * port otherwise
	 */
	public static int getServerPort(String[] args){
		return args.length > PORT_ARG_INDEX ?
				Integer.parseInt(args[PORT_ARG_INDEX]):
				ServerPort.SERVER_PORT;
	}
	/**
	 * Extracts the client name from argument #2,  if it exists, returns default
	 * value otherwise
	 */
	public static String getClientName(String[] args){
		return args.length > CLIENT_NAME_ARG_INDEX?
			args[CLIENT_NAME_ARG_INDEX]:
				DEFAULT_CLIENT_NAME;
	}
	/**
	 * Extracts the headless property from argument #3,  if it exists, returns default
	 * value otherwise
	 */
	public static String getHeadless(String[] args){
		return args.length > HEADLESS_ARG_INDEX?
			args[HEADLESS_ARG_INDEX]:
				DEFAULT_HEADLESS_VALUE;
	}
	/**
	 * Extracts the registry hostname from argument #4,  if it exists, returns server
	 * hostname otherwise
	 */
	public static String getRegistryHost(String[] args){
		return args.length > REGISTRY_HOST_ARG_INDEX?
				args[REGISTRY_HOST_ARG_INDEX]:
				getServerHost(args);
	}

}
