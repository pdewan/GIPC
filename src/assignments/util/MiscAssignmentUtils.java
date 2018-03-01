package assignments.util;

import java.nio.ByteBuffer;
import java.util.Arrays;

import consensus.ProposalFeedbackKind;

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
	public static ProposalFeedbackKind toProposalFeedbackKind(boolean aBoolean) {
		return aBoolean?ProposalFeedbackKind.SUCCESS:ProposalFeedbackKind.SERVICE_DENIAL;
	}
	public static Boolean toBoolean(ProposalFeedbackKind aProposalFeedbackKind) {
		return  aProposalFeedbackKind == ProposalFeedbackKind.SUCCESS;
	}
}
