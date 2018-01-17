package assignments.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

public class MiscAssignmentUtils {
	public static ByteBuffer deepDuplicate(ByteBuffer aByteBuffer) {
		byte[] anArrayCopy = Arrays.copyOfRange(aByteBuffer.array(), aByteBuffer.position(), aByteBuffer.limit());
		return ByteBuffer.wrap(anArrayCopy);
	}
}
