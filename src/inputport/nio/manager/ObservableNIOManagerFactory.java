package inputport.nio.manager;

public class ObservableNIOManagerFactory {
	static ObservableNIOManager singleton;

	public static ObservableNIOManager getSingleton() {
		if (singleton == null) {
			singleton = new AnObservableNIOManager();
		}
		return singleton;
	}

	public static void setSingleton(
			ObservableNIOManager newValue) {
		singleton = newValue;
	}

}
