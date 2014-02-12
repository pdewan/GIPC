package examples.mvc.local.duplex;

import java.beans.PropertyChangeListener;

import examples.mvc.local.simplex.SimplexFrostyModel;

import util.models.PropertyListenerRegisterer;

public interface AnotherDuplexFrostyModel  extends SimplexFrostyModel, PropertyListenerRegisterer,  PropertyChangeListener{

	String getOutput();

}
