package examples.mvc.local.duplex;

import examples.mvc.local.simplex.SimplexUpperCaser;

public interface DuplexUpperCaser extends SimplexUpperCaser {
	String toCountedUpperCase(String string);
//	void addListener(Echoer anEchoer);
}
