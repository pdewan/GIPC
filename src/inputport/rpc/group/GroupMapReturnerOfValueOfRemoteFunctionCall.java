package inputport.rpc.group;

import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.DuplexSentCallCompleter;

import java.util.Map;
import java.util.Set;


public interface GroupMapReturnerOfValueOfRemoteFunctionCall  extends GroupReturnerOfValueOfRemoteFunctionCall{
	 Map<String, Object> returnValueOfRemoteFunctionCall (Set<String> clientNames, RemoteCall aSerializableCall);

}
