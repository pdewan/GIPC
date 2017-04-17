package consensus;

import inputport.ConnectionRegistrar;
import inputport.ConnectionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import consensus.asynchronous.LearnedKind;
import port.trace.RemoteEndConnected;
import port.trace.RemoteEndDisconnected;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalMade;
import port.trace.consensus.ProposalStateChanged;
import port.trace.consensus.ProposalWaitEnded;
import port.trace.consensus.ProposalWaitStarted;
import port.trace.consensus.WaitedForSuccessfulProposalMessageReceipt;
import port.trace.consensus.WaitingForSuccessfulProposalMessageReceipt;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import util.misc.ThreadSupport;
import util.trace.Tracer;

public class AnAbstractConsensusMechanism<StateType> implements ConsensusMechanism<StateType> {
//	protected ConsensusState<StateType> consensusState;
	protected StateType consensusState;
	protected short myId;
	protected float myPrefix;
	protected final static short MAX_PROPOSALS = 10000;
	protected final static short  MAX_IDS = 1000;
	protected  final static short NUM_DIGITS_IN_ID = (short) ("" + MAX_IDS).length();;
	protected short numProposalsByMe = 0;
	protected long receiptWaitTime = 1000;
//	protected boolean eventualConsistency = false;
	
	protected Float lastProposalNumber;
	protected Float myLastProposalNumber;
	protected Float lastConsensusProposal;
	
	protected StateType myLastState;
	protected Map<String, Integer> proposalCounts = new HashMap<String, Integer>();

	protected List<ConsensusListener<StateType>> consensusListeners = new ArrayList();
	protected List<ProposalVetoer<StateType>> consensusRejectioners = new ArrayList();
	
	

	protected Map<Float, ProposalState> proposalState = new HashMap();
	protected Map<Float, StateType> proposalValue = new HashMap();
	protected String objectName;
	

	protected GroupRPCSessionPort rpcSessionPort;
	protected boolean sendRejectionInformation = true;
	
	protected GIPCSessionRegistry gipcSessionRegistry;
	
	protected short numMaximumMembers;
	protected short numCurrentMembers;
	protected short numInitialMembers;
	
	protected ConcurrencyKind concurrencyKind = ConcurrencyKind.SERIALIZABLE;	
	protected ProposalFeedbackKind proposalRejectionKind = ProposalFeedbackKind.SUCCESS;
	protected ReplicationSynchrony acceptSynchrony = ReplicationSynchrony.ALL_SYNCHRONOUS;
	protected ReplicationSynchrony prepareSynchrony = ReplicationSynchrony.ALL_SYNCHRONOUS;

	

	protected LearnedKind learnedKind = LearnedKind.MESSAGE_TIMEOUT;
	protected boolean allowVeto;
	protected boolean valueSynchrony;
	protected ConsensusMemberSetKind consensusMemberSet = ConsensusMemberSetKind.CURRENT_MEMBERS;
	protected boolean acceptReplyForResolvedProposal = true;
	protected boolean allowConcurrentProposals = false;
	protected boolean isClient;
	protected boolean isServer;
	protected String serverName;
	protected boolean isCentralized;

	
	
	
//	public AnAbstractConsensusMechanism(GroupRPCSessionPort anInputPort, String anObjectName, short aMyId) {
//		myId = aMyId;
//		
////		myPrefix = Float.parseFloat("." + myId);
//		myPrefix = ((float) myId)/ (float) Math.pow(10, NUM_DIGITS_IN_ID);
//		objectName = anObjectName;
//		inputPort = anInputPort;
//		anInputPort.addConnectionListener(this);
//		numMaximumMembers = (short) (inputPort.getConnections().size() + 1);
//		numCurrentMembers = numMaximumMembers;
//		numInitialMembers = numMaximumMembers;
//	}
	public AnAbstractConsensusMechanism(GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		myId = aMyId;
		gipcSessionRegistry = aRegistry;
		
//		myPrefix = Float.parseFloat("." + myId);
		myPrefix = ((float) myId)/ (float) Math.pow(10, NUM_DIGITS_IN_ID);
		objectName = anObjectName;
		rpcSessionPort = aRegistry.getSessionPort();
		rpcSessionPort.addConnectionListener(this);
//		numMaximumMembers = (short) (inputPort.getConnections().size() + 1);
//		numCurrentMembers = numMaximumMembers;
//		numInitialMembers = numMaximumMembers;
	}
//	public String toString() {		
//		return getClass().getSimpleName() + "." + objectName;
//	}
	protected void waitForReceipt(float aProposalNumber, StateType aProposal) {
		long aWaitTime = receiptWaitTime();
		WaitingForSuccessfulProposalMessageReceipt.newCase(this, getObjectName(), aProposalNumber, aProposal, aWaitTime);
		ThreadSupport.sleep(aWaitTime);	
		WaitedForSuccessfulProposalMessageReceipt.newCase(this, getObjectName(), aProposalNumber, aProposal, aWaitTime);

	}
	
	public boolean isValueSynchrony() {
		return valueSynchrony;		
	}
	
	public void setValueSynchrony(boolean newVal) {
		valueSynchrony = newVal;
	}
	
	
	protected long receiptWaitTime() {
		return receiptWaitTime;
	}
	
	protected long receiptTime() {
		return receiptWaitTime;
	}
	
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
	
	protected boolean isEventualConsistency() {
		return acceptSynchrony == ReplicationSynchrony.ASYNCHRONOUS;
	}
	protected boolean isAsynchronousReplication() {
		return acceptSynchrony == ReplicationSynchrony.ASYNCHRONOUS;
	}
	protected boolean isNonAtomic() {
		return concurrencyKind == ConcurrencyKind.NON_ATOMIC;
	}
	protected boolean isSerializable() {
		return concurrencyKind == ConcurrencyKind.SERIALIZABLE;
	}
	protected boolean learnedByTimeout() {
		return learnedKind == LearnedKind.MESSAGE_TIMEOUT;
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
//	public boolean sPending(float aProposalNumber) {
//		return proposalState.get(aProposalNumber) == ProposalState.PROPOSAL_PENDING;
//	}
	protected boolean getAllowConcurrentProposals() {
		return allowConcurrentProposals;
	}
	protected void setAllowConcurrentProposals(boolean newVal) {
		allowConcurrentProposals = newVal;
	}
	protected short wholePart (Float aProposalNumber) {
		if (aProposalNumber == null)
			return 0;
		return (short) ((float) aProposalNumber);
	}
	protected float getAndSetNextProposalNumber(StateType aProposal) {
//		if (lastProposalisPending() && !getAllowConcurrentProposals())
//			return -1;
		lastProposalNumber = wholePart(lastProposalNumber) + 1 + myPrefix;
		myLastProposalNumber = lastProposalNumber;
		myLastState = aProposal;		
		numProposalsByMe++;
		return lastProposalNumber;		
	}
	protected void dispatchPropose(float aProposalNumber, StateType aProposal) {
		localPropose(aProposalNumber, aProposal);
	}

	protected void localPropose(float aProposalNumber, StateType aProposal) {
		
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
	
	protected boolean existsProposal(float aProposalNumber) {
		return proposalState.get(aProposalNumber) != null;
	}
	
	protected void recordProposalState(float aProposalNumber, StateType aProposal) {
		if (existsProposal(aProposalNumber)) {
			return;
		}
		proposalState.put(aProposalNumber, ProposalState.PROPOSAL_PENDING);
		proposalValue.put(aProposalNumber, aProposal);
	}
	@Override
	public float propose(StateType aProposal) {
		if (lastProposalisPending() && !getAllowConcurrentProposals())
			return -1;
		float aProposalNumber = getAndSetNextProposalNumber(aProposal);
		ProposalMade.newCase(this, getObjectName(), aProposalNumber, aProposal);
		recordProposalState(aProposalNumber, aProposal);
		dispatchPropose(aProposalNumber, aProposal);	
		return aProposalNumber;
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
	public void addConsensusVetoers(
			ProposalVetoer<StateType> aConsensusRejectioner) {
		consensusRejectioners.add(aConsensusRejectioner);
	}
	@Override
	public void removeConsensusRejectioner(
			ProposalVetoer<StateType> aConsensusRejectioner) {
		consensusRejectioners.remove(aConsensusRejectioner);		
	}
	@Override
	public StateType getLastConsensusState() {
		return consensusState;
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

	   protected boolean isSuccess(ProposalFeedbackKind aFeedbackKind) {
			return aFeedbackKind == ProposalFeedbackKind.SUCCESS;
		}
   protected synchronized void notifyListeners(float aProposalNumber, StateType aProposal, ProposalState aProposalState ) {
		
		boolean isLocal = isMyProposal(aProposalNumber);
		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
			aConsensusListener.newProposalState(aProposalNumber, aProposal, aProposalState);
			if (aProposalState == ProposalState.PROPOSAL_CONSENSUS) {
				aConsensusListener.newConsensusState(aProposal);
				consensusState = aProposal;
				lastConsensusProposal = aProposalNumber;
			}

			if (isLocal) {
				aConsensusListener.newLocalProposalState(aProposalNumber, aProposal, aProposalState);	
			} else {
				aConsensusListener.newRemoteProposalState(aProposalNumber, aProposal, aProposalState);
			}
		}
		
		

	}
	protected synchronized void notify(float aProposalNumber, StateType aProposal, ProposalState aProposalState ) {
		
//		boolean isLocal = isMyProposal(aProposalNumber);
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			aConsensusListener.newProposalState(aProposalNumber, aProposal, aProposalState);
//			if (aProposalState == ProposalState.PROPOSAL_CONSENSUS) {
//				aConsensusListener.newConsensusState(aProposal);
//				consensusState = aProposal;
//				lastConsensusProposal = aProposalNumber;
//			}
//
//			if (isLocal) {
//				aConsensusListener.newLocalProposalState(aProposalNumber, aProposal, aProposalState);	
//			} else {
//				aConsensusListener.newRemoteProposalState(aProposalNumber, aProposal, aProposalState);
//			}
//		}
		notifyListeners(aProposalNumber, aProposal, aProposalState);
		notifyAll();
//		System.out.println("notify all");
	}
//	protected boolean resolvedProposal(float aProposalNumber) {
//		return (proposalState.get(aProposalNumber) != ProposalState.PROPOSAL_PENDING &&
//				lastProposalNumber != null &&
//				aProposalNumber <= lastProposalNumber); // already resolved or received disconnection
//				
//		
//	}
	protected boolean resolvedProposal(float aProposalNumber) {
		return !isPending(aProposalNumber);
				
		
	}
	protected synchronized void newProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState ) {
		if (aProposal == null) {
			System.out.println ("Null state");
		}
		if (!isPending(aProposalNumber)) {
			return;		
		}
		proposalState.put(aProposalNumber, aProposalState);	
		ProposalStateChanged.newCase(this, getObjectName(), aProposalNumber, aProposal, aProposalState);
		notify(aProposalNumber, aProposal, aProposalState);
	}
//	protected void newLocalProposalState(double aProposalNumber, StateType aProposal, ProposalState aProposalState ) {
//		proposalState.put(aProposalNumber, aProposalState);		
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
//				aConsensusListener.newConsensusState(aProposal);
//			}
//			aConsensusListener.newLocalProposalState(aProposalNumber, aProposal, aProposalState);			
//		}
//		notifyAll();
//	}
//	protected void newRemoteProposalState(float aProposalNumber, StateType aProposal, ProposalState aProposalState ) {
////		proposalState.put(aProposalNumber, aProposalState);		
//		for (ConsensusListener<StateType> aConsensusListener:consensusListeners){
//			aConsensusListener.newRemoteProposalState(aProposalNumber, aProposal, aProposalState);
//			if (aProposalState == ProposalState.PROPOSAL_ACCEPTED) {
//				aConsensusListener.newConsensusState(aProposal);
//			}
//			
//		}
//		notifyAll();
//	}
	@Override
	public synchronized ProposalState waitForConsensus(float aProposalNumber) {		
		ProposalWaitStarted.newCase(this, getObjectName(), aProposalNumber, proposalValue.get(aProposalNumber));
		while (isPending(aProposalNumber)) {
			try {
				wait();
			} catch (InterruptedException e) {
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
	public Float myLastProposalNumber() {
		return myLastProposalNumber;
	}
	@Override
	public Float lastProposalNumber() {
		return lastProposalNumber;
	}
	
	protected void removeProposalsLessThanOrEqualTo(float aProposalNumber) {
		for (Float anExistingProposalNumber:getProposals()) {
			if (anExistingProposalNumber <= aProposalNumber) {
				proposalState.remove(anExistingProposalNumber);
				proposalValue.remove(anExistingProposalNumber);
			}
		}
	}
	
	
	

	protected Set<Float> getProposals() {
		return new HashSet(proposalState.keySet());
	}
	
	@Override
	public Set<Float> getPendingProposals() {
		Set<Float> retVal = new HashSet();
		for (Float aProposal:getProposals()) {
			if (proposalState.get(aProposal) == ProposalState.PROPOSAL_PENDING) {
				retVal.add(aProposal);
			}
		}		
		return retVal;
	}
	protected void supersedePendingProposalsBefore(float aProposalNumber) {
		newProposalState(getPendingProposalsBefore(aProposalNumber),
				ProposalState.PROPOSAL_SUPERSEDED);
	}
	protected String makeKey(Float aProposalNumber, String anAttribute) {
		return aProposalNumber +"." + anAttribute;
	}
	protected boolean isKeyOf(String aKey, Float aProposalNumber) {
		return aKey.startsWith(""+ aProposalNumber);
	}
	protected Integer getCount(Float aProposalNumber, String anAttribute) {
		Integer aRetVal = proposalCounts.get(makeKey(aProposalNumber, anAttribute));
		if (aRetVal == null)
			return 0;
		return aRetVal;
		
	}
	protected void setCount(Float aProposalNumber, String anAttribute, int anIncrement) {
		proposalCounts.put(makeKey(aProposalNumber, anAttribute), anIncrement);
		
	}
	protected short incrementCount(Float aProposalNumber, String anAttribute, int anIncrement) {
		Integer aCount = getCount(aProposalNumber, anAttribute);
		if (aCount == null) {
			aCount = 0;
		}
		int aResult = anIncrement + aCount;
		setCount(aProposalNumber, anAttribute, aResult);	
		return (short) aResult;
	}
	
	protected Set<Float> getPendingProposalsBefore(float aProposalNumber) {
		Set<Float> retVal = new HashSet();
		for (Float anExistingProposalNumber:getProposals()) {
			if (proposalState.get(anExistingProposalNumber) == ProposalState.PROPOSAL_PENDING 
					&& anExistingProposalNumber < aProposalNumber) {
				retVal.add(anExistingProposalNumber);
			}
		}		
		return retVal;
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		if (aConnectionType != ConnectionType.MEMBER_TO_SESSION)
			return;
		// TODO Auto-generated method stub
		numMaximumMembers++;
		numCurrentMembers++;
//		RemoteEndConnected.newCase(this, inputPort.getLocalName(), aRemoteEndName,  aConnectionType);


	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation,
			ConnectionType aConnectionType) {
		// TODO Auto-generated method stub

	}
	
	
	
	protected short numMaximumMembers() {
		return numMaximumMembers;
	}
	protected short numCurrentMembers() {
		return numCurrentMembers;
	}
	protected short numInitialMembers() {
		return numInitialMembers;
	}
	protected short consensusMemberSetSize() {
		return numCurrentMembers;
//		select (getConsensusMemberSet()) {
//			case 
//		}
//		select (getConsensusMemberSet()) {
//			
//		}
	}

	public ConsensusMemberSetKind getConsensusMemberSetKind() {
		return consensusMemberSet;
	}
	public void setConsensusMemberSetKind(ConsensusMemberSetKind consensusMemberSet) {
		this.consensusMemberSet = consensusMemberSet;
	}
	@Override
	public synchronized void disconnected(String aRemoteEndName,
			boolean anExplicitDisconnection, String anExplanation,
			ConnectionType aConnectionType) {
		RemoteEndDisconnected.newCase(this, rpcSessionPort.getLocalName(), aRemoteEndName, anExplicitDisconnection, anExplanation, aConnectionType);
		numCurrentMembers--;
//		newProposalState(getPendingProposals(),
//				ProposalState.PROPOSAL_NOT_COMMUNICATED);
	}

	@Override
	public boolean isPending(float aProposalNumber) {
		return proposalState.get(aProposalNumber) == ProposalState.PROPOSAL_PENDING;
	}
	protected ProposalState toProposalState(ProposalFeedbackKind aFeedbackKind) {
		switch (aFeedbackKind) {
		case CONCURRENCY_CONFLICT:
			return ProposalState.PROPOSAL_CONCURRENT_OPERATION;
		case ACCESS_DENIAL:
			return ProposalState.PROPOSAL_SERVICE_FAULT;
		case SERVICE_DENIAL:
			return ProposalState.PROPOSAL_SERVICE_FAULT;
		case SERVICE_FAULT:
			return ProposalState.PROPOSAL_SERVICE_FAULT;
		default:
			return null;
				

		}
	}
//	protected void setLearnedState(float aProposalNumber, StateType aProposal, ProposalVetoKind aRejectionKind) {
//		if (isAgreement(aRejectionKind))
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
//		else
//			newProposalState(aProposalNumber, aProposal,toProposalState(aRejectionKind));
//	}
//	@Override
//	public synchronized void learn(float aProposalNumber, StateType aProposal, ProposalVetoKind aRejectionKind) {
//		ProposalLearnNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aRejectionKind);
//		addProposal(aProposalNumber, aProposal);
//		setLearnedState(aProposalNumber, aProposal, aRejectionKind);
//
////		if (isAgreement(anAgreement))
////			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
////		else
////			newProposalState(aProposalNumber, aProposal,toProposalState(anAgreement));
//		
//	}
	public Float getLastConsensusProposal() {
		return lastConsensusProposal;
	}
	public void setLastConsensusProposal(Float lastConsensusProposal) {
		this.lastConsensusProposal = lastConsensusProposal;
	}
	public ConcurrencyKind getConcurrencyKind() {
		return concurrencyKind;
	}
	public void setConcurrencyKind(ConcurrencyKind consistencyStrength) {
		this.concurrencyKind = consistencyStrength;
	}
	public ProposalFeedbackKind getProposalVetoKind() {
		return proposalRejectionKind;
	}
	public void setProposalVetoKind(ProposalFeedbackKind proposalRejectionKind) {
		this.proposalRejectionKind = proposalRejectionKind;
	}
	public ReplicationSynchrony getAcceptSynchrony() {
		return acceptSynchrony;
	}
	public void setAcceptSynchrony(ReplicationSynchrony consensusSynchrony) {
		this.acceptSynchrony = consensusSynchrony;
	}
	public void setSendRejectionInformation(boolean newVal) {
		sendRejectionInformation = newVal;
	}
	public boolean isSendRejectionNotification() {
		return sendRejectionInformation;
	}
	public boolean isSynchronous() {
//		return false;
		return getAcceptSynchrony() == ReplicationSynchrony.ALL_SYNCHRONOUS;
	}
	public void setAllowVeto(boolean allowVeto) {
		this.allowVeto = allowVeto;
	}
	public boolean isSendAcceptReplyForResolvedProposal() {
		return acceptReplyForResolvedProposal;
	}
	public void setSendAcceptReplyForResolvedProposal(
			boolean acceptReplyForResolvedProposal) {
		this.acceptReplyForResolvedProposal = acceptReplyForResolvedProposal;
	}
	public boolean isClient() {
		return !isServer();		
	}
	public boolean isServer() {
		return rpcSessionPort.getLocalName().equals(getServerName());
	}
	public boolean isCentralized2PC() {
		return isCentralized;
	}
	public String getServerName() {
		if (serverName == null) {
			List<String> aMembers = 
					new ArrayList(rpcSessionPort.getMemberConnections());
			aMembers.add(rpcSessionPort.getLocalName());
			Collections.sort(aMembers);
			return aMembers.get(0); // at least this one is alive
		}
		return serverName;
	}
//	public void setIsClient(boolean isClient) {
//		this.isClient = isClient;
//	}

	public void setIsServer(boolean isServer) {
		this.isServer = isServer;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setCentralized2PC(boolean isCentralized) {
		this.isCentralized = isCentralized;
	}
	
	protected Object all() {
		return  gipcSessionRegistry.lookupAll(objectName);		
	}
	protected Object allRemote() {
		return gipcSessionRegistry.lookupAllRemote(objectName);		
	}
	protected Object caller() {
		return  gipcSessionRegistry.lookupCaller(objectName);
	}
	protected Object member(String aMemberName) {
		return  gipcSessionRegistry.lookup(aMemberName, objectName);
	}
	
	protected StateType proposal(float aProposalNumber) {
		return proposalValue.get(aProposalNumber);
	}
	public ReplicationSynchrony getPrepareSynchrony() {
		return prepareSynchrony;
	}
	public void setPrepareSynchrony(ReplicationSynchrony prepareSynchrony) {
		this.prepareSynchrony = prepareSynchrony;
	}

}
