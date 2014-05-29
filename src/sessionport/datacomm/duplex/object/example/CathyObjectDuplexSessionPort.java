package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;

public class CathyObjectDuplexSessionPort {
	public static void main(String[] args) {
		AnObjectDuplexSessionPortLauncher.launchSessionPartipant( "9093", "Cathy", ParticipantChoice.MEMBER);		
	}


}
