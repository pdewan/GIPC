package inputport.rpc.simplex;



import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.RPCProxyGenerator;


public class ASimplexRPCProxyGenerator implements RPCProxyGenerator {
	protected SimplexRPC simplexRPC;
	String defaultDestination;
	public ASimplexRPCProxyGenerator(SimplexRPC aSimplexRPC) {
		simplexRPC = aSimplexRPC;
		defaultDestination = defaultDestination();
	}
	public  Object generateRPCProxy(String aDestination, Class aClass, String anObjectName) {
		return DirectedRPCProxyGenerator.generateRPCProxy(simplexRPC, aDestination, aClass, anObjectName);				
	}
	
	public  Object generateRPCProxy(Class aClass) {
		return generateRPCProxy(defaultDestination, aClass, null);
				
	}
	public  Object generateRPCProxy(Class aClass, String anObjectName) 	{
		return generateRPCProxy(defaultDestination, aClass, anObjectName);				
	}
	protected String defaultDestination () {
		SimplexRPCClientInputPort clientPort =  (SimplexRPCClientInputPort) simplexRPC;
		return clientPort.getLogicalRemoteEndPoint();
	}
	@Override
	public Object generateRPCProxy(String aDestination, Class aClass) {
		return generateRPCProxy(aDestination, aClass, null);
	}
}
