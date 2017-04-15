package consensus;

import consensus.synchronous.ASynchronousConsensusMechanismFactory;
import port.SessionChoice;

public class ConsensusMechanismSelector {
	static ConsensusMechanismFactory consensusMechanismFactory = new ASynchronousConsensusMechanismFactory();
	public static ConsensusMechanismFactory getConsensusMechanismFactory() {
		return consensusMechanismFactory;
	}
	public static void setConsensusMechanismFactory(
			ConsensusMechanismFactory newVal) {
		consensusMechanismFactory = newVal;
	}
	public static ConsensusMechanism createConsensusMechanism(String aSessionServerHost, 
			String aSessionName,
			short aMemberId,
			int aPortNumber, String anObjectName, SessionChoice aSessionChoice,
			short aNumMembersToWaitFor) {
		return consensusMechanismFactory.createConsensusMechanism(aSessionServerHost, aSessionName, aMemberId, aPortNumber, anObjectName, aSessionChoice, aNumMembersToWaitFor);
	}
	
	

}
;