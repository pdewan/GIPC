package sessionport.rpc.duplex.example;

import port.ParticipantChoice;

public class ACathyDuplexRPCSessionPort {
	public static void main(String[] args) {
		ADuplexRPCSessionPortLauncher.launchSessionPartipant( "9093", "Cathy", ParticipantChoice.MEMBER);		
	}


}
