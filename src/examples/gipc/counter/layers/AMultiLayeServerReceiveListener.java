package examples.gipc.counter.layers;

import java.nio.ByteBuffer;
import java.rmi.RemoteException;

import examples.gipc.counter.simple.ASimpleGIPCRegistryAndCounterServer;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.simplex.buffer.AGenericSimplexBufferServerInputPort;
/**
 * Example of a GIPC receive listener, which is like the NIO manager
 * listener except it listens to objects, which may be byte buffers
 *
 */
public class AMultiLayeServerReceiveListener extends
		ASimpleGIPCRegistryAndCounterServer implements ReceiveListener {
	protected DistributedRMICounter counter;

	public AMultiLayeServerReceiveListener(DistributedRMICounter aCounter) {
		counter = aCounter;	}

	@Override
	public void messageReceived(String aSourceName, Object aMessage) {
		try {
			if (aMessage instanceof ByteBuffer) {
				Integer anInt = Integer
						.parseInt(AGenericSimplexBufferServerInputPort
								.extractString((ByteBuffer) aMessage));
				counter.increment(anInt);

			} else {
				counter.increment((Integer) aMessage);
			}
		} catch (RemoteException e) {
			e.printStackTrace();
		}

	}

}
