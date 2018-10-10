package oldtypedip;


import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.group.GroupInputPortFactory;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupServerInputPort;



public class AnUntypedGroupInputPortFactory implements GroupInputPortFactory<ByteBuffer> {
//	public static String SELECTING_THREAD_NAME = "Selecting Thread";
//	protected static SelectingRunnable selectingRunnable;		
//	static {
//		selectingRunnable = new AMessagingSelectingRunnable();
//		//selectingRunnable = new ASelectingRunnable();
//		Thread selectingThread = new Thread (selectingRunnable);
//		selectingThread.setName(SELECTING_THREAD_NAME);
//		selectingThread.start();
//	}
	@Override
	public GroupServerInputPort<ByteBuffer> createGroupServerInputPort(String theServerId,
			String theServerName) {
		DuplexServerInputPort<ByteBuffer> duplexPort = DuplexBufferInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
		GroupNamingSender<ByteBuffer> namingSender = new AnUntypedGroupNamingSender(duplexPort);
		return new AnOldGroupServerInputPort<ByteBuffer> (duplexPort, namingSender);
	
	}
//	@Override
//	public GroupServerInputPort<ByteBuffer> createGroupServerInputPort(String theServerId,
//			String theServerName) {
//		DuplexServerInputPort<ByteBuffer> duplexPort = UntypedDuplexInputPortSelector.createDuplexServerInputPort(theServerId, theServerName);
////		GroupNamingSender<ByteBuffer> namingSender = new AnUntypedGroupNamingSender(duplexPort);
//		return new AnUntypedGroupServerInputPort (duplexPort);
//	
//	}
	@Override
	public DuplexClientInputPort createDuplexClientInputPort(String theServerHost,
			String theServerId, String theServerName, String theClientName) {
		return DuplexBufferInputPortSelector.createDuplexClientInputPort(theServerHost, theServerId, theServerName, theClientName);
		
	}
}
