package inputport.nio.manager.factories.selectors;

import inputport.nio.manager.factories.WriteCommandFactory;
import inputport.nio.manager.factories.classes.AWriteCommandFactory;
import util.trace.factories.SelectorFactorySet;

public class WriteCommandFactorySelector {
	static WriteCommandFactory factory = new AWriteCommandFactory();

	public static WriteCommandFactory getFactory() {
		return factory;
	}

	public static void setFactory(WriteCommandFactory factory) {
		SelectorFactorySet.newCase(WriteCommandFactorySelector.class, factory);

		WriteCommandFactorySelector.factory = factory;
	}

}
