package sessionport.rpc.group;

import inputport.InputPort;

public interface GIPCSessionRegistry {
	GroupRPCSessionPort getSessionPort(); 
	InputPort getInputPort();
	void rebind(String aProxyName, Object anObject);
	Object lookupAllRemoteButCallerProxy(String aProxyName) ;
	Object lookupMemberProxy(String aRemoteName);
	Object lookupAllRemoteProxy(String aProxyName);
	Object lookupAllRemoteAndMeProxy(String aProxyName);


}
