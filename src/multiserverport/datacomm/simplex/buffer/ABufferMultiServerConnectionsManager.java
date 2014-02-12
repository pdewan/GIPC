package multiserverport.datacomm.simplex.buffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;

import java.io.Serializable;
import java.nio.ByteBuffer;

import port.ParticipantChoice;

import variableserverport.SimplexVariableServerClientPort;
import variableserverport.datacomm.simplex.buffer.AnAbstractSimplexBufferVariableServerConnectionsManager;


// jus implements the abstract method for create port, so why change from multi server to variable server
// the names should be integrated
// this is simplex, so why create duplex client port below?
// no implementation of receive listener
// so this must be simplex
// this port has probably not been used in any test

public class ABufferMultiServerConnectionsManager extends AnAbstractSimplexBufferVariableServerConnectionsManager
	{
	
	public ABufferMultiServerConnectionsManager(SimplexVariableServerClientPort<ByteBuffer> aBroadcastPort/*,
										 ServerPortDescription aServerPortDescription*/, ParticipantChoice aParticipantChoice) {
		super(aBroadcastPort, aParticipantChoice);
	
	}
	
	// why is this duplex?
	@Override
	protected DuplexClientInputPort<ByteBuffer>  createClientInputPort(String aHost, String aServerId, String aServerName, String aClientName){
		DuplexClientInputPort<ByteBuffer> clientInputPort = DuplexBufferInputPortSelector.createDuplexClientInputPort(aHost, 
				aServerId, aServerName, aClientName);
//		clientInputPort.addReceiveListener(this); // ouch, side effect
		return clientInputPort;
	}

	@Override
	public void newState(Serializable newState) {
		// TODO Auto-generated method stub
		
	}



}
