package examples.gipc.counter.customization;

import examples.gipc.counter.layers.AMultiLayerCounterClient;
import examples.gipc.counter.layers.AMultiLayerCounterClient1;

public class ACustomCounterClient1Launcher extends AMultiLayerCounterClient1 {
	public static void main (String[] args) {
		FactorySetterFactory.setSingleton(new ATracingFactorySetter());
		ACustomCounterClient.launch(CLIENT1_NAME);
	}

}
