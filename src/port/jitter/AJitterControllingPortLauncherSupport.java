package port.jitter;

import port.AnAbstractPortLauncherSupport;
import port.PortLauncherSupport;

public class AJitterControllingPortLauncherSupport extends AnAbstractPortLauncherSupport implements PortLauncherSupport{

	
	protected void setTracing() {
//		Tracer.showInfo(true);
//		Tracer.setKeyWordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeywordPrintStatus(this, true);	
	}

	protected  void setFactories() {
		JitterUtility.setSingleServerJitterFactories();
		JitterUtility.setMultipleServerJitterFactories();
		JitterUtility.setRelayerJitterFactories();
		JitterUtility.setGroupServerJitterFactories();
	}
     
}
