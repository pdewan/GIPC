package inputport.rpc.group;

import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.DuplexSentCallCompleter;

import java.util.Set;


public interface GroupReturnerOfValueOfRemoteFunctionCall  extends DuplexSentCallCompleter{
	 Object[] returnValueOfRemoteFunctionCall (Set<String> clientNames, RemoteCall aSerializableCall);

}
