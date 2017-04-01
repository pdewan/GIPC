package consensus.twoparty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inputport.ConnectionType;
import consensus.ConsensusListener;
import consensus.ConsensusResult;
import consensus.ConsensusState;

public class ATwoPartyConsensusMechanism<StateType> implements TwoPartyConsensusMechanism<StateType> {
	protected ConsensusState<StateType> consensusState;
	protected List<ConsensusListener<StateType>> consensusListeners = new ArrayList();
	protected Map<Integer, ConsensusResult> proposalState = new HashMap();
	public ATwoPartyConsensusMechanism(ConsensusState<StateType> aConsensusState) {
		
	}
	@Override
	public void propose(int aProposalNumber, StateType aProposal) {
		
	}

	@Override
	public void addConsensusListener(
			ConsensusListener<StateType> aConsensusListener) {
		
	}

	@Override
	public StateType getConsensusState() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConsensusState(StateType newVal) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void accepted(int aProposalNumber, StateType aProposal, boolean newVal) {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void accept(int aProposalNumber, StateType aProposal) {
		
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		
		
	}
	@Override
	public void removeConsensusListener(
			ConsensusListener<StateType> aConsensusListener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ConsensusResult waitForConsensus(int aProposalNumber) {
		// TODO Auto-generated method stub
		return null;
	}

}
