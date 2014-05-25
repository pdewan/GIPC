package examples.mvc.local.duplex;

import util.models.PropertyListenerRegisterer;
import examples.mvc.local.simplex.SimplexFrostyModel;

public interface DuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegisterer{
	String getInput();
	String getOutput();
	Counter getCounter();
}
