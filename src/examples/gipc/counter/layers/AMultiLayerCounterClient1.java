package examples.gipc.counter.layers;

/**
 * Just calls AMultiLayerCounterClient with a hardwired client name
 *
 */
public class AMultiLayerCounterClient1  {
	public static final String CLIENT1_NAME = "Client1";
	public static void main (String[] args) {
//		AMultiLayerCounterServer.setTracing();
		AMultiLayerCounterClient.launchClient(CLIENT1_NAME);
	}
}
