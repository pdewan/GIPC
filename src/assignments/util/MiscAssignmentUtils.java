package assignments.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MiscAssignmentUtils {
	public static ByteBuffer deepDuplicate(ByteBuffer aByteBuffer) {
		byte[] anArrayCopy = Arrays.copyOfRange(aByteBuffer.array(), aByteBuffer.position(), aByteBuffer.limit());
		return ByteBuffer.wrap(anArrayCopy);
	}
	public static void setHeadless(boolean newVal) {
		System.setProperty("java.awt.headless", Boolean.toString(newVal));
		
	}
	public static void setHeadless(String newVal) {
		System.setProperty("java.awt.headless", newVal);		
	}
}
