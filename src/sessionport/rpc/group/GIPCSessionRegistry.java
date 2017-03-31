package sessionport.rpc.group;

import java.util.Set;

import inputport.InputPort;
import inputport.rpc.GIPCRegistry;

public interface GIPCSessionRegistry extends GIPCRegistry{
	GroupRPCSessionPort getSessionPort(); 
	InputPort getInputPort();
	void rebind(String aProxyName, Object anObject);
	Object lookupAllRemoteButCallerProxy(String aProxyName) ;
	Object lookupMemberProxy(String aRemoteName, String aProxyName);
	Object lookupAllRemoteMembersProxy(String aProxyName);
	Object lookupAllMembersProxy(String aProxyName);	
	Object lookupAllRemoteButCallerProxy(String aProxyName, Class aProxyClass) ;
	Object lookupMemberProxy(String aRemoteName, String aProxyName, Class aProxyClass);
	Object lookupAllRemoteProxy(String aProxyName, Class aProxyClass);
	Object lookupAllRemoteAndMeProxy(String aProxyName, Class aProxyClass);
	Set<String> getAllRemoteMembers();
	Set<String> getAllMembers();

}
