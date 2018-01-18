package assignments.util.mainArgs;

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
	public static final int PORT_ARG_INDEX = 0;	
	public static int getServerPort(String[] args){
		return args.length > PORT_ARG_INDEX ?
				Integer.parseInt(args[PORT_ARG_INDEX]):
					ServerPort.SERVER_PORT;
	}	
}
