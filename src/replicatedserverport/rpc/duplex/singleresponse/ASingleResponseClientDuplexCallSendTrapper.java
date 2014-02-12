package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.rpc.duplex.ADuplexCallSendTrapper;
import inputport.rpc.duplex.DuplexSentCallCompleter;

public class ASingleResponseClientDuplexCallSendTrapper extends ADuplexCallSendTrapper{

	public ASingleResponseClientDuplexCallSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		super(anInputPort, aDestination);
	}
	// the only extension is that a different call completer is created
	// not sure we need a special one, see comment in the call trapper
	// we do not want to change the factory because that will be a global change
	// affecting also the server
	
	// this should reall y be attached to a data port but I guess because of the call
	// completer it is being ttached to an rpc port
	
	// need trappers for both object ports and rpc ports, th eapplication to data ports are
	// not there so let us be content with this for now to reduce the number of trappers
	protected DuplexSentCallCompleter createDuplexSentCallCompleter() {
		return new ASingleResponseDuplexSentCallCompleter(
				duplexRPCInputPort,
				sharedSenderReceiverState().localRemoteReferenceTranslator);
	}

}
