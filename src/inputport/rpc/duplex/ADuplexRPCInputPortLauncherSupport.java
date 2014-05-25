package inputport.rpc.duplex;

import inputport.datacomm.TrapperFactory;
import inputport.rpc.RPCRegistrySelector;
import inputport.rpc.simplex.ASimplexRPCInputPortLauncherSupport;

public class ADuplexRPCInputPortLauncherSupport extends ASimplexRPCInputPortLauncherSupport {	
	
	public  void setFactories() {
		super.setFactories();
		DuplexRPCInputPortSelector.setInputPortFactory(new ADuplexRPCInputPortFactory());
		FunctionReturnValueDeterminerSelector.setFunctionReturnValueDeterminer(new AFunctionReturnValueDeterminer());
		
	}
	@Override
	protected void setTrapperFactories() {
		setDuplexTrapperFactories();
	}
	@Override
	protected void setCallInvokerCompleterFactories() {
		setDuplexCallInvokerCompleterFactories();
	}	
	@Override
	protected void setRPCRegistryFactory() {
		setDuplexRPCRegistryFactory();
	}	
	public static  void  setDuplexTrapperFactories() {
		TrapperFactory<Object, Object> trapperFactory = new ADuplexCallTrapperFactory();

		DuplexServerSerializableCallTrapperSelector.getTrapperSelector().
			setReceiveTrapperFactory(trapperFactory);
		DuplexServerSerializableCallTrapperSelector.getTrapperSelector().
			setSendTrapperFactory(trapperFactory);
		DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
			setReceiveTrapperFactory(trapperFactory);
		DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
			setSendTrapperFactory(trapperFactory);
		
	}
	public static  void  setDuplexCallInvokerCompleterFactories() {
		DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new ADuplexSentCallCompleterFactory());
		LocalRemoteReferenceTranslatorSelector.setTranslatorFactory(new ALocalRemoteReferenceTranslatorFactory());
		SynchronousDuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new ADuplexReceivedCallInvokerFactory());
		DuplexReceivedCallInvokerSelector.setReceivedCallInvokerFactory(new AnAsynchronousDuplexReceivedCallInvokerFactory());
		

	}
	public static void setDuplexRPCRegistryFactory() {
		RPCRegistrySelector.setRPCRegistryFactory(new ADuplexRPCRegistryFactory());
	}
	
	

	
}
