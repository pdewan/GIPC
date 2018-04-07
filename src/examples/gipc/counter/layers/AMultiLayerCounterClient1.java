package examples.gipc.counter.layers;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.nio.ByteBuffer;

import util.trace.port.objects.ObjectTraceUtility;
import util.trace.port.rpc.RPCTraceUtility;
import examples.gipc.counter.AGIPCCounterClient;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;
/**
 * Just calls AMultiLayerCounterClient with a hardwired client name
 *
 */
public class AMultiLayerCounterClient1 extends ASimpleGIPCCounterClient {
	public static final String CLIENT1_NAME = "Client1";
	public static void main (String[] args) {
		AMultiLayerCounterClient.launchClient(CLIENT1_NAME);
	}
}
