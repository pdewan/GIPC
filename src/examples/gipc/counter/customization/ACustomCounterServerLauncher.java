package examples.gipc.counter.customization;

public class ACustomCounterServerLauncher {
	public static void main (String[] args) {
		FactorySetterFactory.setSingleton(new ATracingFactorySetter());
		ACustomCounterServer.launch();
	}

}
