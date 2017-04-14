package consensus;

public enum ProposalRejectionKind {
	ACCEPTED,
	SERVICE_FAULT,
	ACCESS_DENIAL,
	SERVICE_DENIAL,
	CONCURRENT_OPERATION,
	NOT_ENOUGH_ACCEPTS // consolidated response
}
