package consensus;

import port.SessionChoice;

public interface ConsensusMechanismFactory<StateType> {
	ConsensusMechanism<StateType> createConsensusMechanism(
			String aSessionServerHost,
			String aSessionName,
			short aMemberId, 
			int aPortNumber,
			String anObjectName,
			SessionChoice aSessionChoice,
			short aNumMembersToWaitFor);
	public ConsensusMechanism createConsensusMechanism(
			String aSessionName,
			short aMemberId, String anObjectName);
			
			

}
