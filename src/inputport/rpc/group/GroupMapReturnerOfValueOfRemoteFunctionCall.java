package inputport.rpc.group;

import java.util.Map;
import java.util.Set;

import inputport.rpc.RemoteCall;


public interface GroupMapReturnerOfValueOfRemoteFunctionCall  extends GroupReturnerOfValueOfRemoteFunctionCall{
	 Map<String, Object> returnValueOfRemoteFunctionCall (Set<String> clientNames, RemoteCall aSerializableCall);

}
