package examples.mvc.local.duplex;

import examples.mvc.local.simplex.SimplexUpperCaser;
import examples.mvc.rmi.collaborative.relaying.Echoer;

public interface AnotherDuplexUpperCaser extends SimplexUpperCaser {
	void echoUpperCase(String string);
	void addListener(Echoer anEchoer);
}
