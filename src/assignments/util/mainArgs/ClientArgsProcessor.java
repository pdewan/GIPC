package assignments.util.mainArgs;

import java.rmi.registry.Registry;
import java.util.Arrays;

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
	public static final int NIO_PORT_ARG_INDEX = 1;
	public static final int CLIENT_NAME_ARG_INDEX = 2;
	public static final String DEFAULT_CLIENT_NAME = "Generic Client";
	public static final String DEFAULT_HEADLESS_VALUE = "false";

	public static final int HEADLESS_ARG_INDEX = 3;
	public static final int REGISTRY_HOST_ARG_INDEX = 4;
	public static final int REGISTRY_PORT_ARG_INDEX = 5;
	public static final int GIPC_PORT_ARG_INDEX = 6;
	public static final int ACCEPT_IPC_CHANGE_ARG_INDEX =7;
	public static final String DEFAULT_ACCEPT_IPC_CHANGE_VALUE = "true";

	
	public static String[] removeEmpty(String[] args) {
		return Arrays.stream(args).filter(s -> !s.isEmpty()).toArray(String[]::new);
	}
	
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
		return args.length > NIO_PORT_ARG_INDEX ?
				Integer.parseInt(args[NIO_PORT_ARG_INDEX]):
				ServerPort.SERVER_PORT;
	}
	/**
	 * Extracts the client name from argument #2,  if it exists, returns default
	 * value (false) otherwise
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
	/**
	 * Extracts the registry port from argument #5,  if it exists, returns default
	 * port otherwise
	 */
	public static int getRegistryPort(String[] args){
		return args.length > REGISTRY_PORT_ARG_INDEX ?
				Integer.parseInt(args[REGISTRY_PORT_ARG_INDEX]):
				Registry.REGISTRY_PORT;
	}
	
	/**
	 * Extracts the GIPC port from argument #6,  if it exists, returns default
	 * port otherwise
	 */
	public static int getGIPCPort(String[] args){
		return args.length > GIPC_PORT_ARG_INDEX ?
				Integer.parseInt(args[GIPC_PORT_ARG_INDEX]):
					ServerPort.GIPC_SERVER_PORT;
	}
//	/**
//	 * Extracts the acceptipcchange property from argument #7,  if it exists, returns default
//	 * value (true) otherwise
//	 */
//	public static String getAcceptIPCChange(String[] args){
//		return args.length > ACCEPT_IPC_CHANGE_ARG_INDEX?
//			args[ACCEPT_IPC_CHANGE_ARG_INDEX]:
//				DEFAULT_ACCEPT_IPC_CHANGE_VALUE;
//	}

}
