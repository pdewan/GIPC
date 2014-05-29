package sessionport.datacomm.duplex.object.example;

import port.ParticipantChoice;
import sessionport.datacomm.duplex.object.relayed.ARelayingDuplexConnectionsManager;
import util.trace.Tracer;

public class BobObjectDuplexSessionPort {
	public static void main(String[] args) {
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(ARelayingDuplexConnectionsManager.class, true);
		AnObjectDuplexSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}


}
