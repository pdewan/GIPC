package inputport.datacomm.simplex.object;

import util.trace.Tracer;
import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.simplex.ASimplexRPCInputPortLauncherSupport;

public class ASimplexObjectInputPortLauncherSupport extends ASimplexBufferInputPortLauncherSupport {	

	public  void setFactories() {
		//make sure the buffer factories are simplex	
		super.setFactories();

		SimplexObjectInputPortSelector.setInputPortFactory(new AnObjectInputPortFactory());
		
	}
	
	
}
