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
public class RegistryArgsProcessor {
	public static final int REGISTRY_PORT_ARG_INDEX = 0;



	public static String[] removeEmpty(String[] args) {
		return Arrays.stream(args).filter(s -> !s.isEmpty()).toArray(String[]::new);
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
}
