package sessionport.rpc.duplex.direct.example;

import port.ParticipantChoice;

public class AnAliceDuplexRPCDirectSessionPort {
	public static void main(String[] args) {
		ADuplexRPCDirectSessionPortLauncher.launchSessionPartipant( "9091", "Alice", ParticipantChoice.MEMBER);		
	}


}
