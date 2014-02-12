package inputport.rpc.duplex;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;

import java.util.HashMap;
import java.util.Map;

import util.trace.Tracer;


public class ADuplexSentCallCompleter extends AnAbstractDuplexSentCallCompleter implements DuplexSentCallCompleter, ConnectionListener {
	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
	protected Map<String, RPCReturnValueQueue> nameToRPCReturnValueReceiver = new HashMap();
	protected InputPort inputPort;
	public ADuplexSentCallCompleter(InputPort anInputPort, LocalRemoteReferenceTranslator aRemoteHandler) {
		inputPort = anInputPort;
		localRemoteReferenceTranslator = aRemoteHandler;
		inputPort.addConnectionListener(this);
	}
//	public ADuplexSentCallCompleter(DuplexRPCInputPort aDuplexRPCInputPort) {
//		localRemoteReferenceTranslator = aDuplexRPCInputPort.getLocalRemoteReferenceTranslator();
//	}
	protected RPCReturnValueQueue createRPCReturnValueReceiver(LocalRemoteReferenceTranslator aRemoteHandler, String aRemoteEnd) {
		return  new AnRPCReturnValueQueue(aRemoteHandler, aRemoteEnd);
	}

	protected void createRPCReturnValueReceiver(String aRemoteEnd) {
		RPCReturnValueQueue returnValueReceiver = createRPCReturnValueReceiver(localRemoteReferenceTranslator, aRemoteEnd);
//			nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
		nameToRPCReturnValueReceiver.put(aRemoteEnd, returnValueReceiver);
		Tracer.info(this, "Created return value for:" + aRemoteEnd );

	}
	protected RPCReturnValueQueue getRPCReturnValueReceiver(String aRemoteEnd) {
		RPCReturnValueQueue retVal = nameToRPCReturnValueReceiver.get(aRemoteEnd);
		if (retVal != null) return retVal;
		// this case occurs when the client has not explicitly connected
		// if server has joined session as server_only
		 createRPCReturnValueReceiver(aRemoteEnd);
		return nameToRPCReturnValueReceiver.get(aRemoteEnd);		
	}
	//called by sending thread
	@Override
	 public Object returnValueOfRemoteFunctionCall (String aRemoteEndPoint, Object aMessage) {		
		RPCReturnValueQueue rpcReturnValueReceiver = getRPCReturnValueReceiver(aRemoteEndPoint);
		if (rpcReturnValueReceiver == null) {
			Tracer.error("Could not find rpc return value receiver for:" + aRemoteEndPoint  + ". Is the client rpc port connected?");
			return null;
		}
		Object returnValue = rpcReturnValueReceiver.takeReturnValue();
		Tracer.info(this, "took return value:" + returnValue);
		return  returnValue;
		
	}
    // called by receiving thread
	protected void processReturnValue(String source, Object message) {
		RPCReturnValueQueue rpcReturnValueReceiver = getRPCReturnValueReceiver(source);		
//		System.out.println("Putting return value " + rpcReturnValueReceiver);

		rpcReturnValueReceiver.putReturnValue((RPCReturnValue) message);		
	}

	

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		// let us not create this, first call can create it
		// otherwise we create it even when we ourselves join a session
		
		// but uncommenting causes issues I have not resolved
//		createRPCReturnValueReceiver(aRemoteEndName);
		// how about get
		getRPCReturnValueReceiver(aRemoteEndName);
		
	}
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}

}
