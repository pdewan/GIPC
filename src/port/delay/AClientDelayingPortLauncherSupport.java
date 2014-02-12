package port.delay;

import port.AnAbstractPortLauncherSupport;
import util.trace.Tracer;

public class AClientDelayingPortLauncherSupport extends AnAbstractPortLauncherSupport {
	protected   void setTracing() {
		Tracer.showInfo(true);
		Tracer.setKeywordPrintStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeywordPrintStatus(this, true);	
	}

	protected  void setFactories() {
		DelayUtlity.setDelayClientBufferSends();

	}


	
}
