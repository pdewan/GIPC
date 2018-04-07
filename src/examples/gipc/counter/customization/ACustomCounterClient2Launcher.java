package examples.gipc.counter.customization;

import examples.gipc.counter.layers.AMultiLayerCounterClient;
import examples.gipc.counter.layers.AMultiLayerCounterClient1;
import examples.gipc.counter.layers.AMultiLayerCounterClient2;

public class ACustomCounterClient2Launcher extends AMultiLayerCounterClient2 {
	public static void main (String[] args) {
		FactorySetterFactory.setSingleton(new ATracingFactorySetter());
		ACustomCounterClient.launch(CLIENT2_NAME);
	}

}
