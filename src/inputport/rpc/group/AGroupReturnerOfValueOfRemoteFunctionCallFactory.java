package inputport.rpc.group;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AGroupReturnerOfValueOfRemoteFunctionCallFactory 
    implements GroupReturnerOfValueOfRemoteFunctionCallFactory{

	@Override
	public GroupReturnerOfValueOfRemoteFunctionCall 
	       createGroupReturnerOfValueOfRemoteFunctionCall(
	    		   GroupRPCServerInputPort anInputPort,
	    		   LocalRemoteReferenceTranslator locatRemoteReferenceTranslator) {
		return new AGroupReturnerOfValueOfRemoteFunctionCall(anInputPort, locatRemoteReferenceTranslator);
	}

}
