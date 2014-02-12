package sessionport.datacomm.duplex.buffer.example;

import port.ParticipantChoice;

public class AnAliceBufferDuplexSessionPort {
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.MEMBER);		
	}


}
