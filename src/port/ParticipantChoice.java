package port;

public enum ParticipantChoice {
	// means two things, in p2p servers will not open connections to clientss,  but may send messages to them
	// in latecomer, servers will not get buffered messages of servers, and vice versa
	// also clients will not be remote end points of servers
	// causes no end of confusion, this overloading, someday remove it
	SERVER_ONLY, 
	CLIENT_ONLY,
	MEMBER,
	SYMMETRIC_JOIN 
}
