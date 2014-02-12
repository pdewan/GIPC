package inputport.rpc.group;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public interface GroupReturnerOfValueOfRemoteFunctionCallFactory {
	GroupReturnerOfValueOfRemoteFunctionCall
	    createGroupReturnerOfValueOfRemoteFunctionCall(GroupRPCServerInputPort anInputPort, LocalRemoteReferenceTranslator aLocatRemoteReferenceTranslator);

}
