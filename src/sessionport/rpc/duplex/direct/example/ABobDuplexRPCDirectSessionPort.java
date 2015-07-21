package sessionport.rpc.duplex.direct.example;

import port.ParticipantChoice;

public class ABobDuplexRPCDirectSessionPort {
	public static void main(String[] args) {
		ADuplexRPCDirectSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}
}
