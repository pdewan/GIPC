package examples.mvc.local.duplex;

import examples.mvc.local.simplex.SimplexFrostyModel;
import util.models.PropertyListenerRegisterer;

public interface DuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegisterer{
	String getInput();
	String getOutput();
	Counter getCounter();
}
