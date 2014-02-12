package inputport.rpc.simplex;

import util.trace.Tracer;
import inputport.datacomm.simplex.object.ASimplexObjectInputPortLauncherSupport;
import inputport.rpc.AMarshallerFactory;
import inputport.rpc.MarshallerSelector;
import inputport.rpc.RPCRegistrySelector;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;

public class ASimplexRPCInputPortLauncherSupport extends ASimplexObjectInputPortLauncherSupport {	

	public  void setFactories() {
		//make sure the buffer factories are simplex	
		super.setFactories();
		setTrapperFactories();
		setCallInvokerCompleterFactories();
		setMarshallerFactory();
		setRPCRegistryFactory();
		SimplexRPCInputPortSelector.setInputPortFactory(new ASimplexRPCInputPortFactory());
		
	}
	
	protected void setRPCRegistryFactory() {
		setSimplexRPCRegistryFactory();
	}
	
	protected void setTrapperFactories() {
		setSimplexTrapperFactories();
	}
	protected void setCallInvokerCompleterFactories() {
		setSimplexCallInvokerCompleterFactories();
	}
	protected void setMarshallerFactory() {
		setSimplexMarshallerFactory();
	}
	
	public static void setSimplexTrapperFactories() {
		SimplexSerializableCallTrapperSelector.getTrapperSelector().
		setSendTrapperFactory(new ASimplexCallSendTrapperFactory());
		SimplexSerializableCallTrapperSelector.getTrapperSelector().
		setReceiveTrapperFactory(new ASimplexCallReceiveTrapperFactory());

	}
	
	public static void setSimplexCallInvokerCompleterFactories() {	
		MarshallerSelector.setMarshallerFactory(new AMarshallerFactory());
		SimplexSentCallCompleterSelector.setSimplexSentCallCompleterFactory(new ASimplexSentCallCompleterFactory());
	}
	
	public static void setSimplexMarshallerFactory() {
		MarshallerSelector.setMarshallerFactory(new AMarshallerFactory());
	}
	public static void setSimplexRPCRegistryFactory() {
		RPCRegistrySelector.setRPCRegistryFactory(new ASimplexRPCRegistryFactory());
	}
	

}
