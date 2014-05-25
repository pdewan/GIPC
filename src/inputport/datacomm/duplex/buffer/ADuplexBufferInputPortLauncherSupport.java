package inputport.datacomm.duplex.buffer;

import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.buffer.nio.AnNIODuplexBufferInputPortFactory;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;

import java.nio.ByteBuffer;


public class ADuplexBufferInputPortLauncherSupport extends ASimplexBufferInputPortLauncherSupport {
	public  void setFactories() {
		DuplexInputPortFactory<ByteBuffer> factory = 
				new AnNIODuplexBufferInputPortFactory();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(factory);		
	}
	
	public  void setTracing() {
//		Tracer.showInfo(true);
//		Tracer.setKeyWordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeyWordStatus(ADuplexBufferInputPortLauncherSupport.class, true);



	}

}
