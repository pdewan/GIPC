package extraip;

import port.trace.ReturnValueQueued;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.AnRPCReturnValueQueue;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;
import inputport.rpc.duplex.RPCReturnValue;
import inputport.rpc.duplex.RPCReturnValueQueue;



public class AnInheritingDuplexRPCClientInputPort extends ADuplexRPCClientInputPort implements DuplexRPCClientInputPort, ReceiveListener<Object> {
	RPCReturnValueQueue rpcReturnValueReceiver; 

	public AnInheritingDuplexRPCClientInputPort(DuplexClientInputPort<Object> anObjectClientInputPort) {
		super(anObjectClientInputPort);
		rpcReturnValueReceiver =  createRPCReturnValueReceiver(remoteHandler); 

	}
	
	protected RPCReturnValueQueue createRPCReturnValueReceiver(LocalRemoteReferenceTranslator aRemoteHandler) {
		return  new AnRPCReturnValueQueue(aRemoteHandler, null);
	}
	

	
	
	protected Object handleFunction(SerializableCall aCall) {
		try {
		return rpcReturnValueReceiver.takeReturnValue();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	

	@Override
	public void messageReceived(String remoteClientName, Object message) {
		if (message instanceof RPCReturnValue) {
			ReturnValueQueued.newCase(this, rpcReturnValueReceiver, remoteClientName, message);
			rpcReturnValueReceiver.putReturnValue((RPCReturnValue) message);
		} else  {
			super.messageReceived(remoteClientName, message);
		}
	}


}
