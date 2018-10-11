package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;

public class BobObjectDuplexSessionPort {
	public static void main(String[] args) {
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(ARelayingDuplexConnectionsManager.class, true);
		AnObjectDuplexSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}


}
