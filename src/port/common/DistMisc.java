package port.common;

import java.lang.reflect.Proxy;
import java.nio.ByteBuffer;
import java.util.List;

import util.remote.InvocationHandlerWithProperties;

public class DistMisc {
	public static int indexOfReference(List aList, Object anElement) {
		for (int i = 0; i < aList.size(); i++) {
			if (aList.get(i) == anElement)
				return i;
		}
		return -1;
	}

	public static int random(int min, int rangeSize) {

		double random = Math.random();
		long scaledRandom = Math.round(rangeSize * random);
		return min + (int) scaledRandom;
	}
	
	public static String getDestination(Proxy aProxy) {
		return ((InvocationHandlerWithProperties) Proxy.getInvocationHandler(aProxy)).getProxyDestination();
		
	}
	public static String setDestination(Proxy p, String aDestination) {
		return ((InvocationHandlerWithProperties) Proxy.getInvocationHandler(p)).getProxyDestination();
		
	}

	public static String toString(ByteBuffer message) {
		byte[] msgBytes = new byte[message.remaining()];
		int oldPosition = message.position();
		message.get(msgBytes); // will modify position
		message.position(oldPosition); // restore it to make it side effect free
		return new String(msgBytes);
	}
	
}
