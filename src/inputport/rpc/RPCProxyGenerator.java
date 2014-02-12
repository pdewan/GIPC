package inputport.rpc;




public interface RPCProxyGenerator {
	public  Object generateRPCProxy(String aDestination, Class aClass, String anObjectName);	
	public  Object generateRPCProxy(String aDestination, Class aClass);	
	public  Object generateRPCProxy(Class aClass) ;
	public  Object generateRPCProxy(Class aClass, String anObjectName) ;

}
