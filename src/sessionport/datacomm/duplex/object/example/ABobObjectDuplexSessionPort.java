package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;

public class ABobObjectDuplexSessionPort {
	public static void main(String[] args) {
		AnObjectDuplexSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}


}
