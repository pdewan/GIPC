package sessionport.datacomm.group.object.example;

import port.ParticipantChoice;

public class ACathyObjectGroupSessionPort {
	public static void main(String[] args) {
		AnObjectGroupSessionPortLauncher.launchSessionPartipant( "9093", "Cathy", ParticipantChoice.SYMMETRIC_JOIN);		
	}


}
