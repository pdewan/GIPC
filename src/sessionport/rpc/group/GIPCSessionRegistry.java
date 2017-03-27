package sessionport.rpc.group;

public interface GIPCSessionRegistry {
	void rebind(String aName, Object anObject);
	Object getOthersProxy() ;
	Object getemberProxy(String aRemoteName);
	Object getAllProxy();


}
