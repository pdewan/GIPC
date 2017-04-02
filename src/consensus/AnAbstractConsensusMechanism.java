package consensus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import port.trace.consensus.ProposalStateChanged;
import port.trace.consensus.ProposalWaitEnded;
import port.trace.consensus.ProposalWaitStarted;
import inputport.ConnectionRegistrar;
import inputport.ConnectionType;
import inputport.InputPort;
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
	protected Map<Integer, StateType> proposalValue = new HashMap();
	protected String objectName;
	public AnAbstractConsensusMechanism(ConnectionRegistrar anInputPort, String anObjectName, int aMyId) {
		myId = aMyId;
		myPrefix = MAX_PROPOSALS*10*myId;
		objectName = anObjectName;
		anInputPort.addConnectionListener(this);
	}
//	public String toString() {		
//		return getClass().getSimpleName() + "." + objectName;
//	}
	
	protected String getObjectName() {
		return objectName;
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
		return true;
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
//	protected void remotePropose(int aProposalNumber, StateType aProposal) {
//		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_PENDING);
//		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);
//		
//	}
//	protected void remoteAccept(int aProposalNumber, StateType aProposal) {
//		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_CONSENSUS);
//		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);		
//	}
	@Override
	public int propose(StateType aProposal) {
		int myProposalNumber = getAndSetNextProposalNumber(aProposal);
		if (myProposalNumber == -1) {
			return myProposalNumber;
		}
		proposalState.put(myProposalNumber, ProposalState.PROPOSAL_PENDING);
		proposalValue.put(myProposalNumber, aProposal);
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
	protected boolean isMyProposal(int aProposalNumber) {
		int aRemainder = aProposalNumber%myPrefix;
		int aDiv = aProposalNumber/myPrefix;
		return aDiv == 1 && aRemainder < MAX_PROPOSALS;
	}
	protected void newProposalState(Set<Integer> aProposalNumbers,  ProposalState aProposalState ) {
		for (Integer aProposalNumber:aProposalNumbers) {
			newProposalState(aProposalNumber, proposalValue.get(aProposalNumber), aProposalState);
		}		
	}
	protected synchronized void newProposalState(int aProposalNumber, StateType aState, ProposalState aProposalState ) {
		if (aState == null) {
			System.out.println (" Null state");
		}
		ProposalStateChanged.newCase(this, getObjectName(), aProposalNumber, aState, aProposalState);
		boolean isLocal = isMyProposal(aProposalNumber);
		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
			
			if (aProposalState == ProposalState.PROPOSAL_CONSENSUS) {
				aConsensusListener.newConsensusState(aState);
			}
			if (isLocal) {
				proposalState.put(aProposalNumber, aProposalState);	
				aConsensusListener.newLocalProposalState(aProposalNumber, aState, aProposalState);	
			} else {
				aConsensusListener.newRemoteProposalState(aProposalNumber, aState, aProposalState);
			}
		}
		notifyAll();
//		System.out.println("notify all");

	}
//	protected void newLocalProposalState(int aProposalNumber, StateType aState, ProposalState aProposalState ) {
//		proposalState.put(aProposalNumber, aProposalState);		
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
//				aConsensusListener.newConsensusState(aState);
//			}
//			aConsensusListener.newLocalProposalState(aProposalNumber, aState, aProposalState);			
//		}
//		notifyAll();
//	}
//	protected void newRemoteProposalState(int aProposalNumber, StateType aState, ProposalState aProposalState ) {
////		proposalState.put(aProposalNumber, aProposalState);		
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			aConsensusListener.newRemoteProposalState(aProposalNumber, aState, aProposalState);
//			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
//				aConsensusListener.newConsensusState(aState);
//			}
//			
//		}
//		notifyAll();
//	}
	@Override
	public synchronized ProposalState waitForStateChange(int aProposalNumber) {
		
		ProposalWaitStarted.newCase(this, getObjectName(), aProposalNumber, proposalValue.get(aProposalNumber));
		while (proposalState.get(aProposalNumber) == ProposalState.PROPOSAL_PENDING) {
			try {
				wait();
//				System.out.println ("wait ended");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ProposalState returnValue = proposalState.get(aProposalNumber);
		ProposalWaitEnded.newCase(this, getObjectName(), aProposalNumber, proposalValue.get(aProposalNumber), returnValue);
		return returnValue;
	}
	@Override
	public ProposalState getProposalState(int aProposalNumber) {
		// TODO Auto-generated method stub
		return proposalState.get(aProposalNumber);
	}
	@Override
	public Integer getMyLastProposalNumber() {
		return myLastProposalNumber;
	}
	@Override
	public Integer getLastProposalNumber() {
		return lastProposalNumber;
	}
	
	protected void removeProposalsLessThanOrEqualTo(int aProposalNumber) {
		for (Integer anExistingProposalNumber:getMyProposals()) {
			if (anExistingProposalNumber <= aProposalNumber) {
				proposalState.remove(anExistingProposalNumber);
				proposalValue.remove(anExistingProposalNumber);
			}
		}
	}
	

	protected Set<Integer> getMyProposals() {
		return new HashSet(proposalState.keySet());
	}
	
	@Override
	public Set<Integer> getMyPendingProposals() {
		Set<Integer> retVal = new HashSet();
		for (Integer aProposal:getMyProposals()) {
			if (proposalState.get(aProposal) == ProposalState.PROPOSAL_PENDING) {
				retVal.add(aProposal);
			}
		}		
		return retVal;
	}
	
	
	protected Set<Integer> getMyPendingProposalsBefore(int aProposalNumber) {
		Set<Integer> retVal = new HashSet();
		for (Integer anExistingProposalNumber:getMyProposals()) {
			if (proposalState.get(anExistingProposalNumber) == ProposalState.PROPOSAL_PENDING 
					&& anExistingProposalNumber < aProposalNumber) {
				retVal.add(anExistingProposalNumber);
			}
		}		
		return retVal;
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
	public synchronized void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation,
			ConnectionType aConnectionType) {
		newProposalState(getMyPendingProposals(),
				ProposalState.PROPOSAL_NOT_COMMUNICATED);
	}
}
