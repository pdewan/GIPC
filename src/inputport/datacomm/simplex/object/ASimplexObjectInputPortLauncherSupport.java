package inputport.datacomm.simplex.object;

import inputport.datacomm.simplex.buffer.ASimplexBufferInputPortLauncherSupport;

public class ASimplexObjectInputPortLauncherSupport extends ASimplexBufferInputPortLauncherSupport {	

	public  void setFactories() {
		//make sure the buffer factories are simplex	
		super.setFactories();

		SimplexObjectInputPortSelector.setInputPortFactory(new AnObjectInputPortFactory());
		
	}
	
	
}
