package examples.mvc.local.duplex;

import java.beans.PropertyChangeListener;

import util.models.PropertyListenerRegisterer;
import examples.mvc.local.simplex.SimplexFrostyModel;

public interface AnotherDuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegisterer,  PropertyChangeListener{

	String getOutput();

}
