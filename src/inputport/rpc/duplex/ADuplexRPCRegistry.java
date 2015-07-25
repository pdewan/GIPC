package inputport.rpc.duplex;

import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.RPCInputPort;
import inputport.rpc.simplex.ASimplexRPCRegistry;

public class ADuplexRPCRegistry extends ASimplexRPCRegistry {
	DuplexRPCInputPort serverPort;
	public ADuplexRPCRegistry(RPCInputPort aServerPort) {
		serverPort = (DuplexRPCInputPort) aServerPort;
	}
	
	@Override
	public void register(String aName, Object aServerObject) {
		super.register(aName, aServerObject);
		associateWithRemoteSerialiable(aName, aServerObject);
//		RemoteSerializable aRemoteSerializable = new ARemoteSerializable(serverPort.getLocalName(), 
//				aServerObject.getClass().getName(), aName); // null class is ok as a proxy will not be generated by the client end, it already has a proxy
//		serverPort.getLocalRemoteReferenceTranslator().connectRemoteAndRemoteSerializable(aRemoteSerializable, aServerObject);
		
	}
	void associateWithRemoteSerialiable(String aName, Object aServerObject) {
		RemoteSerializable aRemoteSerializable = new ARemoteSerializable(serverPort.getLocalName(), 
				aServerObject.getClass().getName(), aName); // null class is ok as a proxy will not be generated by the client end, it already has a proxy
		if (DirectedRPCProxyGenerator.isShortCircuitLocalCallsToRemotes()) // this is for received parameters, may want to go through proxy layers
		serverPort.getLocalRemoteReferenceTranslator().connectRemoteAndRemoteSerializable(aRemoteSerializable, aServerObject);
		
	}
	@Override
	public void register(Object aServerObject) {
		super.register(aServerObject); // should not cause recursion
		associateWithRemoteSerialiable(aServerObject.getClass().getName(), aServerObject); // in case a refrerence arrives, return this object, looks like a sent reference uses the same data structure

	}
	
}
