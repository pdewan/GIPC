package consensus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import inputport.ConnectionType;
import consensus.ConsensusListener;
import consensus.ProposalState;
import consensus.ConsensusState;

public class AnAbstractConsensusMechanism<StateType> implements ConsensusMechanism<StateType> {
//	protected ConsensusState<StateType> consensusState;
	protected StateType consensusState;
	protected int myId;
	protected int myPrefix;
	protected final static int MAX_PROPOSALS = 10000;
	protected int numProposalsByMe = 0;
	protected Integer lastProposalNumber;
	protected Integer myLastProposalNumber;
	protected StateType myLastState;

	protected List<ConsensusListener<StateType>> consensusListeners = new ArrayList();
	protected Map<Integer, ProposalState> proposalState = new HashMap();
//	protected Map<Integer, StateType> proposalValue = new HashMap();
	public AnAbstractConsensusMechanism(int aMyId) {
		myId = aMyId;
		myPrefix = MAX_PROPOSALS*10*myId;
	}
	
	protected ProposalState getLastProposalState() {
		if (lastProposalNumber == null)
			return null;
		return getProposalState(lastProposalNumber);
	}
	protected ProposalState getMyLastProposalState() {
		if (myLastProposalNumber == null) {
			return null;
		}
		return proposalState.get(myLastProposalNumber);
	}
	
	@Override
	public boolean myLastProposalIsPending() {
		ProposalState aLastProposalState = getMyLastProposalState();
		if (aLastProposalState == null)
			return false;
		return aLastProposalState == ProposalState.PROPOSAL_PENDING;
	}
	public boolean lastProposalisPending() {
		ProposalState aLastProposalState = getLastProposalState();
		if (aLastProposalState == null)
			return false;
		return aLastProposalState == ProposalState.PROPOSAL_PENDING;
	}
	protected boolean allowConcurrentProposals() {
		return false;
	}
	int getAndSetNextProposalNumber(StateType aState) {
		if (lastProposalisPending() && !allowConcurrentProposals())
			return -1;
		myLastProposalNumber = myPrefix + numProposalsByMe;
		myLastState = aState;
		if (lastProposalNumber == null ) {
			lastProposalNumber = myLastProposalNumber;
		} else {
			lastProposalNumber = Math.max(myLastProposalNumber, lastProposalNumber);
		}
		numProposalsByMe++;
		return myLastProposalNumber;
		
	}
	protected void propose(int aProposalNumber, StateType aProposal) {
		
	}
	protected void remotePropose(int aProposalNumber, StateType aProposal) {
		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_PENDING);
		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);
		
	}
	protected void remoteAccept(int aProposalNumber, StateType aProposal) {
		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_ACCEPTED);
		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);		
	}
	@Override
	public int propose(StateType aProposal) {
		int myProposalNumber = getAndSetNextProposalNumber(aProposal);
		if (myProposalNumber == -1) {
			return myProposalNumber;
		}
		proposalState.put(myProposalNumber, ProposalState.PROPOSAL_PENDING);
		propose(myProposalNumber, aProposal);	
		return myProposalNumber;
	}
	@Override
	public void addConsensusListener(
			ConsensusListener<StateType> aConsensusListener) {
		consensusListeners.add(aConsensusListener);
	}
	@Override
	public void removeConsensusListener(
			ConsensusListener<StateType> aConsensusListener) {
		consensusListeners.remove(aConsensusListener);		
	}
	@Override
	public StateType getConsensusState() {
		return consensusState;
	}

//	@Override
//	public void setConsensusState(StateType newVal) {
//		
//	}

	
//	@Override
//	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void notConnected(String aRemoteEndName, String anExplanation,
//			ConnectionType aConnectionType) {
//		// TODO Auto-generated method stub
//		
//	}
//	@Override
//	public void disconnected(String aRemoteEndName,
//			boolean anExplicitDsconnection, String anExplanation,
//			ConnectionType aConnectionType) {		
//		
//		
//	}
	protected Long getWaitTimeOut() {
		return null;
	}
	protected void newProposalState(int aProposalNumber, StateType aState, ProposalState aProposalState ) {
		proposalState.put(aProposalNumber, aProposalState);		
		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
			aConsensusListener.newProposalState(aProposalNumber, aState, aProposalState);
			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
				aConsensusListener.newConsensusState(aState);
			}
			if (aProposalNumber == myLastProposalNumber) {
				aConsensusListener.myLastProposalState(aState, aProposalState);
			}
		}
		notifyAll();
	}
	@Override
	public synchronized ProposalState waitForConsensus(int aProposalNumber) {
		while (proposalState.get(aProposalNumber) != ProposalState.PROPOSAL_PENDING) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return proposalState.get(aProposalNumber);
	}
	@Override
	public ProposalState getProposalState(int aProposalNumber) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Integer getMyLastProposalNumber() {
		return myLastProposalNumber;
	}
	@Override
	public Integer getLastProposalNumber() {
		return lastProposalNumber;
	}
	
	
}
