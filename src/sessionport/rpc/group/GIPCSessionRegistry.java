package sessionport.rpc.group;

public interface GIPCSessionRegistry {
	void rebind(String aProxyName, Object anObject);
	Object lookupAllRemoteButCallerProxy(String aProxyName) ;
	Object lookupMemberProxy(String aRemoteName);
	Object lookupAllRemoteProxy(String aProxyName);
	Object lookupAllRemoteAndMeProxy(String aProxyName);


}
