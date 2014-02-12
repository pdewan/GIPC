package inputport.rpc;

import java.util.Set;

public interface RPCRegistry  {
	void register(String aName, Object aRemoteObject);
	void register(Object aRemoteObject);
	void register (Class anInterface, Object aRemoteObject);
	Set<String> registeredMethodNames();
	Object getServerObject(String aName);
}
