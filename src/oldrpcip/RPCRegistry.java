package oldrpcip;

public interface RPCRegistry  {
	void register(String name, Object server);
	void register(Object server);
	void register (Class type, Object server);
	Object getServer(String name);
}
