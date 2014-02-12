package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;

public class AnAliceObjectDuplexSessionPort {
	public static void main(String[] args) {
		AnObjectDuplexSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.MEMBER);		
	}


}
