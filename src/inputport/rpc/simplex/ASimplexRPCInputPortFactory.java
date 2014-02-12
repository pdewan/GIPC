package inputport.rpc.simplex;



import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.object.SimplexObjectInputPortSelector;

public class ASimplexRPCInputPortFactory implements SimplexRPCInputPortFactory {
	public ASimplexRPCInputPortFactory() {
//		setTrapperFactories();
//		setCallInvokerCompleterFactories();
//		setMarshallerFactory();

	}
	
//	protected void setTrapperFactories() {
//		setSimplexTrapperFactories();
//	}
//	protected void setCallInvokerCompleterFactories() {
//		setSimplexCallInvokerCompleterFactories();
//	}
//	protected void setMarshallerFactory() {
//		setSimplexMarshallerfactory();
//	}
//	
//	public static void setSimplexTrapperFactories() {
//		SimplexSerializableCallTrapperSelector.getTrapperSelector().
//		setSendTrapperFactory(new ASimplexCallSendTrapperFactory());
//		SimplexSerializableCallTrapperSelector.getTrapperSelector().
//		setReceiveTrapperFactory(new ASimplexCallReceiveTrapperFactory());
//
//	}
//	
//	public static void setSimplexCallInvokerCompleterFactories() {	
//		MarshallerSelector.setMarshallerFactory(new AMarshallerFactory());
//		SimplexSentCallCompleterSelector.setSimplexSentCallCompleterFactory(new ASimplexSentCallCompleterFactory());
//		RPCRegistrySelector.setRPCRegistryFactory(new AnRPCRegistryFactory());
//	}
//	
//	public static void setSimplexMarshallerfactory() {
//		MarshallerSelector.setMarshallerFactory(new AMarshallerFactory());
//	}

	@Override
	public SimplexRPCClientInputPort createRPCClientInputPort(String theServerHost, String theServerId,
			String theServerName, String theClientName) {
		SimplexClientInputPort<Object> typedClientInputPort = SimplexObjectInputPortSelector.createSimplexClientInputPort(theServerHost, theServerId, theServerName, theClientName);
		return createRPCClientInputPort(typedClientInputPort);

	}
	@Override
	public SimplexRPCClientInputPort createRPCClientInputPort(SimplexClientInputPort<Object> objectClientInputPort) {
		return new ASimplexRPCClientInputPort(objectClientInputPort);
	}

	@Override
	public SimplexRPCServerInputPort createRPCServerInputPort(String theServerId,
			String theServerName) {
		SimplexServerInputPort<Object> objectServerInputPort = SimplexObjectInputPortSelector.createSimplexServerInputPort(theServerId, theServerName);
		return createRPCServerInputPort(objectServerInputPort);

	}
	
	@Override
	public SimplexRPCServerInputPort createRPCServerInputPort(SimplexServerInputPort<Object> objectServerInputPort) {
		return new ASimplexRPCServerInputPort(objectServerInputPort);
	}
	
	

}
