package port;

import util.trace.Tracer;



public abstract class AnAbstractPortLauncherSupport implements PortLauncherSupport{
	protected  void setFactories() {
		
	}
	protected   void setTracing() {
//		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, false);
//		Tracer.setKeyWordStatus(this, true);		

	}
	public void init() {
		setTracing();
		setFactories();
	}
	public void tracePrints() {
		Tracer.showInfo(true);
		Tracer.setKeyWordDisplayStatus(Tracer.ALL_KEYWORDS, false);
		Tracer.setKeywordPrintStatus(this.getClass(), true);
	}

	
}
