package sessionport.rpc.duplex.example;

import port.ParticipantChoice;

public class AnAliceDuplexRPCSessionPort {
	public static void main(String[] args) {
		ADuplexRPCSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.MEMBER);		
	}


}
