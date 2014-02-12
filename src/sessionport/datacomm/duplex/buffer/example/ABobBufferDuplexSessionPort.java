package sessionport.datacomm.duplex.buffer.example;

import port.ParticipantChoice;

public class ABobBufferDuplexSessionPort {
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}


}
