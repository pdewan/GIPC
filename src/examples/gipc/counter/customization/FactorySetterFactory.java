package examples.gipc.counter.customization;

public class FactorySetterFactory {
	protected static FactorySetter singleton = new ATracingFactorySetter();

	public static FactorySetter getSingleton() {
		return singleton;
	}

	public static void setSingleton(FactorySetter newVal) {
		singleton = newVal;
	}
	

}
