package inputport.nio.manager;

public class AcceptCommandSelector {
	static AcceptCommandFactory factory = new AnAcceptCommandFactory();
	public static AcceptCommandFactory getFactory() {
		return factory;
	}
	public static void setFactory(AcceptCommandFactory factory) {
		AcceptCommandSelector.factory = factory;
	}

}
