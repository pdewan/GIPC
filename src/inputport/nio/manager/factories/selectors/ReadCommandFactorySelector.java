package inputport.nio.manager.factories.selectors;

import util.trace.factories.SelectorFactorySet;
import inputport.nio.manager.factories.ReadCommandFactory;
import inputport.nio.manager.factories.classes.AReadCommandFactory;

public class ReadCommandFactorySelector {
	static ReadCommandFactory factory = new AReadCommandFactory();

	public static ReadCommandFactory getFactory() {
		return factory;
	}

	public static void setFactory(ReadCommandFactory factory) {
		SelectorFactorySet.newCase(ReadCommandFactorySelector.class, factory);

		ReadCommandFactorySelector.factory = factory;
	}

}
