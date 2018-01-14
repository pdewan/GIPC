package extraip;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.AnRPCReturnValueQueue;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;
import inputport.rpc.duplex.RPCReturnValueQueue;
import util.trace.port.rpc.ReturnValueQueuedOld;



public class AnInheritingDuplexRPCClientInputPort extends ADuplexRPCClientInputPort implements DuplexRPCClientInputPort, ReceiveListener<Object> {
	RPCReturnValueQueue rpcReturnValueReceiver; 

	public AnInheritingDuplexRPCClientInputPort(DuplexClientInputPort<Object> anObjectClientInputPort) {
		super(anObjectClientInputPort);
		rpcReturnValueReceiver =  createRPCReturnValueReceiver(remoteHandler); 

	}
	
	protected RPCReturnValueQueue createRPCReturnValueReceiver(LocalRemoteReferenceTranslator aRemoteHandler) {
		return  new AnRPCReturnValueQueue(aRemoteHandler, null, null);
	}
	

	
	
	protected Object handleFunction(SerializableCall aCall) {
		try {
			// value not being translated
		return rpcReturnValueReceiver.takeReturnValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Override
	public void messageReceived(String remoteClientName, Object message) {
		if (message instanceof RPCReturnValue) {
			ReturnValueQueuedOld.newCase(this, rpcReturnValueReceiver, remoteClientName, message);
			rpcReturnValueReceiver.putReturnValue((RPCReturnValue) message);
		} else  {
			super.messageReceived(remoteClientName, message);
		}
	}


}
