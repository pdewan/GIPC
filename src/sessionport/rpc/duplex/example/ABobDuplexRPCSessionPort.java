package sessionport.rpc.duplex.example;

import port.ParticipantChoice;

public class ABobDuplexRPCSessionPort {
	public static void main(String[] args) {
		ADuplexRPCSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}
}
