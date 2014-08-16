package examples.mvc.local.duplex;

import util.models.PropertyListenerRegistrar;
import examples.mvc.local.simplex.SimplexFrostyModel;

public interface DuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegistrar{
	String getInput();
	String getOutput();
	Counter getCounter();
}
