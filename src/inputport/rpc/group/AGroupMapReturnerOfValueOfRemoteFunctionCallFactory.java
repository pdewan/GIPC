package inputport.rpc.group;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AGroupMapReturnerOfValueOfRemoteFunctionCallFactory 
    implements GroupReturnerOfValueOfRemoteFunctionCallFactory{

	@Override
	public GroupReturnerOfValueOfRemoteFunctionCall 
	       createGroupReturnerOfValueOfRemoteFunctionCall(
	    		   GroupRPCServerInputPort anInputPort,
	    		   LocalRemoteReferenceTranslator locatRemoteReferenceTranslator) {
		return new AGroupMapReturnerOfValueOfRemoteFunctionCall(anInputPort, locatRemoteReferenceTranslator);
	}

}
