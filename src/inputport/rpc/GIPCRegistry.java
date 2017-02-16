package inputport.rpc;

import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import port.PortLauncher;

public interface GIPCRegistry extends PortLauncher{
	/*
	 * Give name to object and listen for client connections.
	 * Use this operation if you have only one registered object
	 */
//	void rebindAndListen (String aName, Object anObject);
	
//	/*
//	 * If you use this operation and are a server and have not listened, then you must do so
//	 * at some point.
//	 * If you are a client, then listening is not important.
//	 */	
	/*
	 * Like the RMIRegistry rebind method, except anObject does not have to be exported
	 */
	void rebind(String aName, Object anObject);
	
	/*
	 * Listens for client connections.
	 * Needs to be done once, either before or after rebinding, ideally after
	 */
//	void listen();
	/*
	 * Like the RMIRegistry lookup method, except that the interface of the returned object
	 * should be passed as an extra first parameter.
	 */
	public Object lookup(Class anInterface, String aName);

	DuplexRPCClientInputPort getRPCClientPort();
	DuplexRPCServerInputPort getRPCServerPort();
	InputPort getInputPort();
//	public RPCInputPort getRPCInputPort() ;
}
