package inputport.datacomm.group.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import inputport.datacomm.group.GroupInputPortFactory;


public class AGroupBufferInputPortLauncherSupport extends ADuplexBufferInputPortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
		GroupInputPortFactory<ByteBuffer> factory = 
				new  ABufferGroupInputPortFactory();;
		BufferGroupInputPortSelector.setGroupInputPortFactory(factory);		
	}
	

}
