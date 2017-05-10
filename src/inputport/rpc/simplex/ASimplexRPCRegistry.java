package inputport.rpc.simplex;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import inputport.rpc.RPCRegistry;
import util.introspect.JavaIntrospectUtility;
import util.misc.RemoteReflectionUtility;

public class ASimplexRPCRegistry implements RPCRegistry {
	Map<String, Object> nameToServer = new ConcurrentHashMap();
	public void register(Class aType, Object aServerObject) {
//		nameToServer.put(aType.getName(), aServerObject);
		register(aType.getName(), aServerObject);	// will be overridden in subclass

	}
	
	// maybe all of these other methods should take a class as an argument that describes the interface being served
	@Override
	public void register(String aName, Object aServerObject) {
		nameToServer.put(aName, aServerObject);	

	}
	@Override
	public void register(Object aServerObject) {
//		nameToServer.put(aServerObject.getClass().getName(), aServerObject);	
		List<Class> types = JavaIntrospectUtility.getTypes(aServerObject.getClass());
		for (Class aClass:types) {
		
			if (aClass.getPackage().getName() != null && aClass.getPackage().getName().startsWith("java")) // no point registering predefined classes
				continue;
			RemoteReflectionUtility.checkRemoteMethods(aClass);
			String aName = aClass.getName();
			if (nameToServer.get(aName) == null)
//			register(aClass.getName(), aServerObject);
				nameToServer.put(aName, aServerObject);	
		}
		String mainName = aServerObject.getClass().getName();
		nameToServer.put(mainName, aServerObject);	// just in case nothing got registered
	}
	
	public Object getServerObject(String aName) {
		return nameToServer.get(aName);
	}	
	
	@Override
	public Set<String> registeredMethodNames() {
		return nameToServer.keySet();
	}
}
