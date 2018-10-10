package inputport.nio.manager.factories.selectors;

import inputport.nio.manager.factories.ConnectCommandFactory;
import inputport.nio.manager.factories.classes.AConnectCommandFactory;
import util.trace.factories.SelectorFactorySet;

/**
 * Chooses the factory that creates the connect commmand. 
 * The default factory can be changed using the setter. 
 * @author Dewan
 *
 */
public class ConnectCommandFactorySelector {
	static ConnectCommandFactory factory = new AConnectCommandFactory();
	public static ConnectCommandFactory getFactory() {
		return factory;
	}
	public static void setFactory(ConnectCommandFactory factory) {
		SelectorFactorySet.newCase(ConnectCommandFactorySelector.class, factory);
		ConnectCommandFactorySelector.factory = factory;
	}

}
