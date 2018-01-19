package inputport.nio.manager.factories.selectors;

import inputport.nio.manager.factories.AcceptCommandFactory;
import inputport.nio.manager.factories.classes.AnAcceptCommandFactory;
import util.trace.factories.SelectorFactorySet;

public class AcceptCommandFactorySelector {
	static AcceptCommandFactory factory = new AnAcceptCommandFactory();
	public static AcceptCommandFactory getFactory() {
		return factory;
	}
	public static void setFactory(AcceptCommandFactory factory) {
		SelectorFactorySet.newCase(AcceptCommandFactorySelector.class, factory);
		AcceptCommandFactorySelector.factory = factory;
	}

}
