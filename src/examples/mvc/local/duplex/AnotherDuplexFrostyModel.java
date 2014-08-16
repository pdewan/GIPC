package examples.mvc.local.duplex;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegistrar;
import examples.mvc.local.simplex.SimplexFrostyModel;

public interface AnotherDuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegistrar,  PropertyChangeListener{

	String getOutput();

}
