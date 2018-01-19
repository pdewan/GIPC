package inputport.nio.manager;

import util.trace.port.nio.NIOTraceUtility;

public class NIOManagerFactory {
	static NIOManager singleton;

	public static NIOManager getSingleton() {
		if (singleton == null) {
			singleton = new AnNIOManager();
		}
		return singleton;
	}

	public static void setSingleton(
			NIOManager newValue) {
		singleton = newValue;
	}
	
//	static {
//		NIOTraceUtility.setTracing();
//	}

}
