package oldrpcip;

import java.util.HashMap;
import java.util.Map;

public class AnRPCRegistery implements RPCRegistry {
	Map<String, Object> nameToServer = new HashMap();	
	public void register(Class type, Object server) {
		nameToServer.put(type.getName(), server);		
	}		
	public Object getServer(String name) {
		return nameToServer.get(name);
	}
	@Override
	public void register(String name, Object server) {
		nameToServer.put(name, server);
		
	}
	@Override
	public void register(Object server) {
		nameToServer.put(server.getClass().getName(), server);
	}
}
