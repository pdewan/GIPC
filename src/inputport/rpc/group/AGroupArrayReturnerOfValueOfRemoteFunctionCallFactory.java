package inputport.rpc.group;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class AGroupArrayReturnerOfValueOfRemoteFunctionCallFactory 
    implements GroupReturnerOfValueOfRemoteFunctionCallFactory{

	@Override
	public GroupArrayReturnerOfValueOfRemoteFunctionCall 
	       createGroupReturnerOfValueOfRemoteFunctionCall(
	    		   GroupRPCServerInputPort anInputPort,
	    		   LocalRemoteReferenceTranslator locatRemoteReferenceTranslator) {
		return new AGroupArrayReturnerOfValueOfRemoteFunctionCall(anInputPort, locatRemoteReferenceTranslator);
	}

}
