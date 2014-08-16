package examples.mvc.rmi.collaborative.relaying;

import util.models.PropertyListenerRegistrar;

public interface Echoer extends PropertyListenerRegistrar {
	void echo(String value);
}
