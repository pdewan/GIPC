package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface ConsensusMechanism<StateType> extends LocalConsensusMechanism<StateType>, 
		 ConsensusCustomization {




}
