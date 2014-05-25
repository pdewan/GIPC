package inputport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.buffer.nio.AnNIOSimplexBufferInputPortFactory;

import java.nio.ByteBuffer;

import port.AnAbstractPortLauncherSupport;
import port.PortLauncherSupport;


public class ASimplexBufferInputPortLauncherSupport extends AnAbstractPortLauncherSupport implements PortLauncherSupport{
	public  void setFactories() {
		SimplexInputPortFactory<ByteBuffer> factory = 
				new AnNIOSimplexBufferInputPortFactory();
		SimplexBufferInputPortSelector.setSimplexBufferInputPortFactory(factory);		
		
	}	
	public  void setTracing() {
//		Tracer.showInfo(true);
//		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeyWordStatus(ADuplexBufferInputPortLauncherSupport.class, true);
//		Tracer.setKeyWordStatus(ASelectionManager.class, true);
	}
}
