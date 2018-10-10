package inputport.rpc.group;

import java.util.Set;

import inputport.rpc.RemoteCall;
import inputport.rpc.duplex.DuplexSentCallCompleter;


public interface GroupReturnerOfValueOfRemoteFunctionCall  extends DuplexSentCallCompleter{
	 Object returnValueOfRemoteFunctionCall (Set<String> clientNames, RemoteCall aSerializableCall);

}
