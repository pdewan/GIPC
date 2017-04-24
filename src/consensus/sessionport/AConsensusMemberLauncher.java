package consensus.sessionport;

import consensus.ConsensusMechanism;
import examples.gipc.consensus.AMeaningOfLifeConsensusListener;
import port.SessionChoice;
import sessionport.rpc.group.GIPCLocateSessionRegistry;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;

public abstract class AConsensusMemberLauncher  {

	protected  GIPCSessionRegistry gipcRegistry;
	protected  GroupRPCSessionPort groupRPCSessionPort;
	protected  SessionChoice sessionChoice = SessionChoice.P2P;
	

	
	protected abstract short numMembersToWaitFor() ;
	protected abstract void createConsensusMechanisms(
			short anId);
	protected abstract void addListenersAndVetoersToConsensusMechanisms() ;
	public AConsensusMemberLauncher(String aLocalName, int aPortNumber) {
		init(aLocalName, aPortNumber);
	}
	
	protected abstract void customizeConsensusMechanisms();
	protected   void initConsensusMechanisms(short anId) {
		createConsensusMechanisms(anId);
		customizeConsensusMechanisms();
		addListenersAndVetoersToConsensusMechanisms();
	}

	protected Object lookupMulticastProxy(Class aClass, String aName) {
		return gipcRegistry.lookupAll(aClass, aName);
	}
	protected short memberId;
	protected int portNumber;
	public  void init(String aLocalName, int aPortNumber) {
		memberId = Short.parseShort(aLocalName);		;
		portNumber = aPortNumber;
		initConsensusMechanisms(memberId);
	}
	

}
