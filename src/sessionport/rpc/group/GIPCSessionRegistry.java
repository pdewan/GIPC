package sessionport.rpc.group;

import java.util.Set;

import inputport.InputPort;
import inputport.rpc.GIPCRegistry;

public interface GIPCSessionRegistry extends GIPCRegistry{
	GroupRPCSessionPort getSessionPort(); 
	InputPort getInputPort();
	void rebind(String aProxyName, Object anObject);
	Object lookupAllRemoteButCaller(String aProxyName) ;
	Object lookup(String aMemberName, String aProxyName);
	Object lookupAllRemote(String aProxyName);
	Object lookupAllRemoteButCaller(Class anInterface, String aProxyName);

	Object lookupAll(String aProxyName);	
	Object lookup(String aMemberName, Class aProxyClass, String aProxyName);
	Object lookupAllRemote(Class aProxyClass, String aProxyName);
	Object lookupAll(Class aProxyClass, String aProxyName);
	Set<String> getAllRemoteMembers();
	Set<String> getAllMembers();

}
