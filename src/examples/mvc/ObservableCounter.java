package examples.mvc;

import examples.mvc.local.duplex.Counter;
import util.models.PropertyListenerRegisterer;

public interface ObservableCounter extends Counter, PropertyListenerRegisterer {

}
