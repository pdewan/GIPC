package examples.gipc.counter.layers;

/**
 * Just calls AMultiLayerCounterClient with a hardwired client name
 *
 */
public class AMultiLayerCounterClient2 {
	public static final String CLIENT2_NAME = "Client2";
	public static void main (String[] args) {
		AMultiLayerCounterClient.launchClient(CLIENT2_NAME);
	}
}
