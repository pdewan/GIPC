package inputport.rpc.duplex;



import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCProxyGenerator;
import inputport.rpc.simplex.ASimplexRPCProxyGenerator;



public class ADuplexRPCProxyGenerator extends ASimplexRPCProxyGenerator implements RPCProxyGenerator {
	DuplexRPC duplexRPC;
	LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	public ADuplexRPCProxyGenerator(DuplexRPC aDuplexRPC, LocalRemoteReferenceTranslator aLocalRemoteReferenceTranslator) {
		super(aDuplexRPC);
		duplexRPC = aDuplexRPC;
		localRemoteReferenceTranslator = aLocalRemoteReferenceTranslator;
	}
	public  Object generateRPCProxy(String aDestination, Class aClass, String anObjectName) {
		anObjectName = defaultName(anObjectName, aClass);		
		Object remoteSerializable = localRemoteReferenceTranslator.createRemoteSerializable(aDestination, aClass.getName(), anObjectName);
		Object retVal = localRemoteReferenceTranslator.getRemote(remoteSerializable);
		if (retVal != null)	return retVal;	
		retVal = super.generateRPCProxy(aDestination, aClass, anObjectName);
		localRemoteReferenceTranslator.connectRemoteAndRemoteSerializable(remoteSerializable, retVal);
		return retVal;
	}
	protected String defaultDestination () {
		DuplexInputPort duplexPort =  (DuplexInputPort) simplexRPC;
		return duplexPort.getSender();
	}
	
	public static String defaultName (String aName, Class aClass) {
		if (aName != null) return aName;
		else if (aClass != null) 
			return (ADuplexRPCClientInputPort.defaultNameFromClass(aClass));
		else 
			return null;
			
	}
	
	

}
