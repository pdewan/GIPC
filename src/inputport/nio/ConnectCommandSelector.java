package inputport.nio;

public class ConnectCommandSelector {
	static ConnectCommandFactory factory = new AConnectCommandFactory();
	public static ConnectCommandFactory getFactory() {
		return factory;
	}
	public static void setFactory(ConnectCommandFactory factory) {
		ConnectCommandSelector.factory = factory;
	}

}
