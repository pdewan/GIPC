package sessionport.datacomm.duplex.buffer.example;

import port.ParticipantChoice;

public class ACathyBufferDuplexSessionPort {
	public static void main(String[] args) {
		ABufferDuplexSessionPortLauncher.launchSessionPartipant( "9093", "Cathy", ParticipantChoice.MEMBER);		
	}


}
