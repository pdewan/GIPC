package examples.gipc.counter.tracing;

import port.ATracingConnectionListener;
import examples.gipc.counter.simple.ASimpleGIPCCounterClient;

public class ATracingGIPCCounterClient extends ASimpleGIPCCounterClient{
	public static void main(String[] args) {
		init();
//		gipcRegistry.getRPCClientPort().addConnectionListener(new ASimpleConnectListener());
		gipcRegistry.getRPCClientPort().addReceiveListener(new ASimpleReceiveListener());
		//		gipcRegistry.getRPCInputPort(
		doOperations();

	}

}
