package sessionport.rpc.group;

import java.util.Set;

import inputport.InputPort;
import inputport.rpc.GIPCRegistry;

public interface GIPCSessionRegistry extends GIPCRegistry{
	GroupRPCSessionPort getSessionPort(); 
	InputPort getInputPort();
	void rebind(String aProxyName, Object anObject);
	Object lookupAllRemoteButCaller(String aProxyName) ;
	Object lookupMember(String aRemoteName, String aProxyName);
	Object lookupAllRemoteMembers(String aProxyName);
	Object lookupAllMembers(String aProxyName);	
	Object lookupAllRemoteButCaller(Class aProxyClass, String aProxyName) ;
	Object lookupMember(String aRemoteName, Class aProxyClass, String aProxyName);
	Object lookupAllRemote(Class aProxyClass, String aProxyName);
//	Object lookupAllRemoteAndMe(String aProxyName, Class aProxyClass);
	Set<String> getAllRemoteMembers();
	Set<String> getAllMembers();

}
