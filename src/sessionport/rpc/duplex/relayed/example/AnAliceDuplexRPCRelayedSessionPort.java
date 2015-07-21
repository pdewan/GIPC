package sessionport.rpc.duplex.relayed.example;

import port.ParticipantChoice;

public class AnAliceDuplexRPCRelayedSessionPort {
	public static void main(String[] args) {
		ADuplexRPCRelayedSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.MEMBER);		
	}


}
