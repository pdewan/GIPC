package inputport.rpc.simplex;

import inputport.rpc.RemoteCall;


public interface SimplexSentCallCompleter {
	Object returnValueOfRemoteMethodCall(String aRemoteEndPoint, RemoteCall aCall);
}
