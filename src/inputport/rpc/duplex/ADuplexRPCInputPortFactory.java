package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.DuplexObjectInputPortSelector;
import inputport.rpc.simplex.ASimplexRPCInputPortFactory;

public class ADuplexRPCInputPortFactory extends ASimplexRPCInputPortFactory implements DuplexRPCInputPortFactory {
	public ADuplexRPCInputPortFactory() {
		super();
		
	}
//	@Override
//	protected void setTrapperFactories() {
//		setDuplexTrapperFactories();
//	}
//	@Override
//	protected void setCallInvokerCompleterFactories() {
//		setDuplexCallInvokerCompleterFactories();
//	}
//	
//	
//	public static  void  setDuplexTrapperFactories() {
//		TrapperFactory<Object, Object> trapperFactory = new ADuplexCallTrapperFactory();
//		DuplexSerializableCallTrapperSelector.getTrapperSelector().
////		GlobalState.getDuplexSerializableCallTrapper().
//			setReceiveTrapperFactory(trapperFactory);
//		DuplexSerializableCallTrapperSelector.getTrapperSelector().
////		GlobalState.getDuplexSerializableCallTrapper().
//			setSendTrapperFactory(trapperFactory);
//		
//	}
//	public static  void  setDuplexCallInvokerCompleterFactories() {
//		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new ADuplexSentCallCompleterFactory());
//		LocalRemoteReferenceTranslatorSelector.setTranslatorFactory(new ALocalRemoteReferenceTranslatorFactory());
//		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new ADuplexReceivedCallInvokerFactory());
//	}

	@Override
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(String theServerHost, String theServerId,
			String theServerName, String theClientName) {
		DuplexClientInputPort<Object> typedClientInputPort = createDuplexClientInputPort (theServerHost, theServerId, theServerName, theClientName);
		return createDuplexRPCClientInputPort(typedClientInputPort);
	}

	@Override
	public DuplexRPCServerInputPort createDuplexRPCServerInputPort(String theServerId,
			String theServerName) {
		DuplexServerInputPort<Object> typedServerInputPort = createDuplexServerInputPort(theServerId, theServerName);
		return createDuplexRPCServerInputPort(typedServerInputPort);

	}
	
	@Override
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(DuplexClientInputPort<Object> typedClientInputPort) {
		return new ADuplexRPCClientInputPort(typedClientInputPort);
	}

	@Override
	public DuplexRPCServerInputPort createDuplexRPCServerInputPort(DuplexServerInputPort<Object> typedServerInputPort) {
		return new ADuplexRPCServerInputPort(typedServerInputPort);
	}
	protected DuplexServerInputPort<Object> createDuplexServerInputPort(String theServerId,
			String theServerName) {
		return DuplexObjectInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
		
	}
	protected DuplexClientInputPort<Object> createDuplexClientInputPort(String theServerHost, String theServerId,
			String theServerName, String theClientName)  {
		return DuplexObjectInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, theServerName, theClientName);		
	}
	
	

}
