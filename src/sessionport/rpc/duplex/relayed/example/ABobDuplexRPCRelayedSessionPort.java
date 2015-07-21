package sessionport.rpc.duplex.relayed.example;

import port.ParticipantChoice;

public class ABobDuplexRPCRelayedSessionPort {
	public static void main(String[] args) {
		ADuplexRPCRelayedSessionPortLauncher.launchSessionPartipant("9092", "Bob", ParticipantChoice.MEMBER);		
	}
}
