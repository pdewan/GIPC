package inputport.datacomm.group.object;

import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.buffer.AGroupBufferInputPortLauncherSupport;


public class AGroupObjectInputPortLauncherSupport extends AGroupBufferInputPortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		GroupInputPortFactory<Object> factory = 
				new  AGroupObjectInputPortFactory();;
		GroupObjectInputPortSelector.setGroupInputPortFactory(factory);	
		setGroupTrappers();
//		ReceiveTrapperFactory<Object, Object>  aReceiveTrapperFactory = new AReceiveMessageForwarderFactory();
//		GroupSendTrapperFactory<Object, Object>  aSendTrapperFactory = 	new AGroupSendMessageForwarderFactory();
//		ServerObjectGroupTrapperSelector.getTrapperSelector().setGroupSendTrapperFactory(aSendTrapperFactory);
//		ServerObjectGroupTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(aReceiveTrapperFactory);

	}
	
	public static void setGroupTrappers() {
		// no need for these as these are set by default
//		ReceiveTrapperFactory<Object, Object>  aReceiveTrapperFactory = new AReceiveMessageForwarderFactory();
//		GroupSendTrapperFactory<Object, Object>  aSendTrapperFactory = 	new AGroupSendMessageForwarderFactory();
//		ServerObjectGroupTrapperSelector.getTrapperSelector().setGroupSendTrapperFactory(aSendTrapperFactory);
//		ServerObjectGroupTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(aSendTrapperFactory);
	}
	
//	static {
//		DuplexInputPortFactory<ByteBuffer> factory = 
//			new AnNIODuplexBufferInputPortFactory();
////		DuplexInputPortFactory<ByteBuffer> factory = 
////			new ASocketDuplexInputPortFactory();
//		BufferDuplexInputPortSelector.setDuplexBufferInputPortFactory(factory);
//	}
//	public static void setTracing() {
////		ASimplexBufferInputPortLauncher.setTracing();
////		Tracer.showInfo(true);
////		Tracer.setImplicitKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
////		Tracer.setMessagePrefixKind(MessagePrefixKind.PACKAGE_NAME);
////		Tracer.setKeyWordStatus(AGenericDuplexBufferClientInputPort.class, true);
////		Tracer.setKeyWordStatus(AScatterGatherSelectionManager.class, true);
//	}
}
