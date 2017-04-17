package consensus;

public enum ProposalFeedbackKind {
	SUCCESS,
	SERVICE_FAULT,
	ACCESS_DENIAL,
	SERVICE_DENIAL,
	CONCURRENCY_CONFLICT,
	NOT_ENOUGH_SUCCESSES // consolidated response
}
