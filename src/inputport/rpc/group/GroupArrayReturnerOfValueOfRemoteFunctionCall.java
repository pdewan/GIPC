package inputport.rpc.group;

import java.util.Set;

import inputport.rpc.RemoteCall;


public interface GroupArrayReturnerOfValueOfRemoteFunctionCall  extends GroupReturnerOfValueOfRemoteFunctionCall{
	 Object[] returnValueOfRemoteFunctionCall (Set<String> clientNames, RemoteCall aSerializableCall);

}
