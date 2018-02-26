package assignments.util.mainArgs;

import java.rmi.registry.Registry;
import java.util.Arrays;

/** 
 * Example server args supported by this library:
 * 
 * 9001
 * 
 * 
 * Pass the main args of your server to the various methods of this class to
 * get the specified or defiult values * 
 *
 */

/**
 * Extracts the server port from argument #0,  if it exists, returns default
 * port otherwise
 */
public class ServerArgsProcessor {
	public static final int NIO_PORT_ARG_INDEX = 0;	
	public static final int REGISTRY_HOST_ARG_INDEX = 1;
	public static final int REGISTRY_PORT_ARG_INDEX = 2;
	public static final int GIPC_PORT_ARG_INDEX = 3;



	public static String[] removeEmpty(String[] args) {
		return Arrays.stream(args).filter(s -> !s.isEmpty()).toArray(String[]::new);
	}
	
	/**
	 * Extracts the NIO port from argument #0,  if it exists, returns default
	 * port
	 */
	public static int getServerPort(String[] args){
		return args.length > NIO_PORT_ARG_INDEX ?
				Integer.parseInt(args[NIO_PORT_ARG_INDEX]):
					ServerPort.SERVER_PORT;
	}
		
	/**
	 * Extracts the registry hostname from argument #1,  if it exists, returns default
	 * hostname (localhost) otherwise
	 */
	public static String getRegistryHost(String[] args){
		return args.length > REGISTRY_HOST_ARG_INDEX?
				args[REGISTRY_HOST_ARG_INDEX]:
				"localhost";
	}

	/**
	 * Extracts the Registry RMI port from argument #2,  if it exists, returns default
	 * port
	 */
	public static int getRegistryPort(String[] args){
		return args.length > REGISTRY_PORT_ARG_INDEX ?
				Integer.parseInt(args[REGISTRY_PORT_ARG_INDEX]):
					Registry.REGISTRY_PORT;
	}
	
	/**
	 * Extracts the GIPC port from argument #3,  if it exists, returns default
	 * port
	 */
	public static int getGIPCServerPort(String[] args){
		return args.length > GIPC_PORT_ARG_INDEX ?
				Integer.parseInt(args[GIPC_PORT_ARG_INDEX]):
					ServerPort.GIPC_SERVER_PORT;
	}
}
