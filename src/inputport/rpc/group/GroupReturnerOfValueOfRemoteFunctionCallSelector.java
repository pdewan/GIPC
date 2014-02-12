package inputport.rpc.group;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class GroupReturnerOfValueOfRemoteFunctionCallSelector {
static GroupReturnerOfValueOfRemoteFunctionCallFactory 
	groupReturnerOfValueOfFunctionCallFactory = new AGroupReturnerOfValueOfRemoteFunctionCallFactory();

public static GroupReturnerOfValueOfRemoteFunctionCallFactory getGroupReturnerOfValueOfFunctionCallFactory() {
	return groupReturnerOfValueOfFunctionCallFactory;
}

public static void setGroupReturnerOfValueOfFunctionCallFactory(
		GroupReturnerOfValueOfRemoteFunctionCallFactory groupReturnerOfValueOfFunctionCallFactory) {
	GroupReturnerOfValueOfRemoteFunctionCallSelector.groupReturnerOfValueOfFunctionCallFactory = groupReturnerOfValueOfFunctionCallFactory;
}
public GroupReturnerOfValueOfRemoteFunctionCall
     createGroupReturnerOfValueOfRemoteFunctionCall(
    		 GroupRPCServerInputPort anInputPort,
    		 LocalRemoteReferenceTranslator aLocatRemoteReferenceTranslator) {
	return groupReturnerOfValueOfFunctionCallFactory.createGroupReturnerOfValueOfRemoteFunctionCall(anInputPort, aLocatRemoteReferenceTranslator);
}

}
