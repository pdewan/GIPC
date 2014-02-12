package sessionport.datacomm.group.object.example;

import port.ParticipantChoice;

public class AnAliceObjectGroupSessionPort {
	public static void main(String[] args) {
		AnObjectGroupSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.SYMMETRIC_JOIN);		
	}


}
