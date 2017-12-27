package inputport.nio;

public class WriteCommandSelector {
	static ConnectCommandFactory factory;

	public static ConnectCommandFactory getFactory() {
		return factory;
	}

	public static void setFactory(ConnectCommandFactory factory) {
		WriteCommandSelector.factory = factory;
	}

}
