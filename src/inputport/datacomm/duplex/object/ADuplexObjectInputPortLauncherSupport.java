package inputport.datacomm.duplex.object;

import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;


public class ADuplexObjectInputPortLauncherSupport extends ADuplexBufferInputPortLauncherSupport {
	public  void setFactories() {
		// make sure the buffer factories are duplex
		super.setFactories();		
		DuplexObjectInputPortSelector.setDuplexInputPortFactory(new ADuplexObjectInputPortFactory());
		
	}
//	static {
//		DuplexInputPortFactory<ByteBuffer> factory = 
//			new AnNIODuplexBufferInputPortFactory();
//
//		BufferDuplexInputPortSelector.setDuplexBufferInputPortFactory(factory);
//	}
//	public static void setTracing() {
//		ASimplexBufferInputPortLauncherSupport.setTracing();
//		Tracer.showInfo(false);
//		Tracer.setImplicitKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
//		Tracer.setMessagePrefixKind(MessagePrefixKind.PACKAGE_NAME);
//		Tracer.setKeyWordStatus(AGenericDuplexBufferClientInputPort.class, true);
//		Tracer.setKeyWordStatus(AScatterGatherSelectionManager.class, true);
//	}
}
