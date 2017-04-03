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
	protected short myId;
	protected float myPrefix;
	protected final static short MAX_PROPOSALS = 10000;
	protected final static short  MAX_IDS = 1000;
	protected  final static short NUM_DIGITS_IN_ID = (short) ("" + MAX_IDS).length();;
	protected short numProposalsByMe = 0;
	protected Float lastProposalNumber;
	protected Float myLastProposalNumber;
	protected StateType myLastState;

	protected List<ConsensusListener<StateType>> consensusListeners = new ArrayList();
	protected Map<Float, ProposalState> proposalState = new HashMap();
	protected Map<Float, StateType> proposalValue = new HashMap();
	protected String objectName;
	public AnAbstractConsensusMechanism(ConnectionRegistrar anInputPort, String anObjectName, short aMyId) {
		myId = aMyId;
		
//		myPrefix = Float.parseFloat("." + myId);
		myPrefix = ((float) myId)/ (float) Math.pow(10, NUM_DIGITS_IN_ID);
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
	public boolean someProposalIsPending() {
		return getPendingProposals().size() > 0;
	}
	protected boolean allowConcurrentProposals() {
		return true;
	}
	protected short wholePart (Float aProposalNumber) {
		if (aProposalNumber == null)
			return 0;
		return (short) ((float) aProposalNumber);
	}
	protected float getAndSetNextProposalNumber(StateType aState) {
		if (lastProposalisPending() && !allowConcurrentProposals())
			return -1;
		lastProposalNumber = wholePart(lastProposalNumber) + 1 + myPrefix;
		myLastProposalNumber = lastProposalNumber;
		myLastState = aState;		
		numProposalsByMe++;
		return lastProposalNumber;		
	}
	protected void propose(float aProposalNumber, StateType aProposal) {
		
	}
//	protected void remotePropose(double aProposalNumber, StateType aProposal) {
//		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_PENDING);
//		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);
//		
//	}
//	protected void remoteAccept(double aProposalNumber, StateType aProposal) {
//		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_CONSENSUS);
//		lastProposalNumber = Math.max(aProposalNumber, lastProposalNumber);		
//	}
	
	protected void addProposal(float aProposalNumber, StateType aProposal) {
		if (proposalState.get(aProposalNumber) != null) {
			return;
		}
		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_PENDING);
		proposalValue.put(aProposalNumber, aProposal);
	}
	@Override
	public float propose(StateType aProposal) {
		float myProposalNumber = getAndSetNextProposalNumber(aProposal);
		if (myProposalNumber == -1) {
			return myProposalNumber;
		}
		addProposal(myProposalNumber, aProposal);
		proposalState.put(myProposalNumber, ProposalState.PROPOSAL_PENDING);
//		proposalValue.put(myProposalNumber, aProposal);
//		propose(myProposalNumber, aProposal);	
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
		if (someProposalIsPending())
			return null;
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
	protected boolean isMyProposal(float aProposalNumber) {
		float aRemainder = aProposalNumber - (short) aProposalNumber;
		return aRemainder == myPrefix;
//		short anId =  (short) (aRemainder * Math.pow(10, NUM_DIGITS_IN_ID));
//		float aDiv = aProposalNumber/myPrefix;
//		return aDiv == 1 && aRemainder < MAX_PROPOSALS;
	}
	protected void newProposalState(Set<Float> aProposalNumbers,  ProposalState aProposalState ) {
		for (Float aProposalNumber:aProposalNumbers) {
			newProposalState(aProposalNumber, proposalValue.get(aProposalNumber), aProposalState);
		}		
	}
	protected synchronized void newProposalState(float aProposalNumber, StateType aState, ProposalState aProposalState ) {
		if (aState == null) {
			System.out.println (" Null state");
		}
		ProposalStateChanged.newCase(this, getObjectName(), aProposalNumber, aState, aProposalState);
		boolean isLocal = isMyProposal(aProposalNumber);
		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
			
			if (aProposalState == ProposalState.PROPOSAL_CONSENSUS) {
				aConsensusListener.newConsensusState(aState);
			}
			proposalState.put(aProposalNumber, aProposalState);	

			if (isLocal) {
				aConsensusListener.newLocalProposalState(aProposalNumber, aState, aProposalState);	
			} else {
				aConsensusListener.newRemoteProposalState(aProposalNumber, aState, aProposalState);
			}
		}
		notifyAll();
//		System.out.println("notify all");

	}
//	protected void newLocalProposalState(double aProposalNumber, StateType aState, ProposalState aProposalState ) {
//		proposalState.put(aProposalNumber, aProposalState);		
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
//				aConsensusListener.newConsensusState(aState);
//			}
//			aConsensusListener.newLocalProposalState(aProposalNumber, aState, aProposalState);			
//		}
//		notifyAll();
//	}
//	protected void newRemoteProposalState(float aProposalNumber, StateType aState, ProposalState aProposalState ) {
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
	public synchronized ProposalState waitForStateChange(float aProposalNumber) {
		
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
	public ProposalState getProposalState(float aProposalNumber) {
		// TODO Auto-generated method stub
		return proposalState.get(aProposalNumber);
	}
	@Override
	public Float getMyLastProposalNumber() {
		return myLastProposalNumber;
	}
	@Override
	public Float getLastProposalNumber() {
		return lastProposalNumber;
	}
	
	protected void removeProposalsLessThanOrEqualTo(float aProposalNumber) {
		for (Float anExistingProposalNumber:getMyProposals()) {
			if (anExistingProposalNumber <= aProposalNumber) {
				proposalState.remove(anExistingProposalNumber);
				proposalValue.remove(anExistingProposalNumber);
			}
		}
	}
	

	protected Set<Float> getMyProposals() {
		return new HashSet(proposalState.keySet());
	}
	
	@Override
	public Set<Float> getPendingProposals() {
		Set<Float> retVal = new HashSet();
		for (Float aProposal:getMyProposals()) {
			if (proposalState.get(aProposal) == ProposalState.PROPOSAL_PENDING) {
				retVal.add(aProposal);
			}
		}		
		return retVal;
	}
	
	
	protected Set<Float> getMyPendingProposalsBefore(float aProposalNumber) {
		Set<Float> retVal = new HashSet();
		for (Float anExistingProposalNumber:getMyProposals()) {
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
		newProposalState(getPendingProposals(),
				ProposalState.PROPOSAL_NOT_COMMUNICATED);
	}
}
