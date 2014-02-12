package extraip;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.rpc.duplex.ADuplexCallSendTrapper;
import inputport.rpc.duplex.DuplexSentCallCompleter;

public class ALocalResponseClientDuplexCallSendTrapper extends ADuplexCallSendTrapper{

	public ALocalResponseClientDuplexCallSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
		super(anInputPort, aDestination);
	}
	// the only extension is that a different call completer is created
	// we do not want to change the factory because that will be a global change
	// affecting also the server
	protected DuplexSentCallCompleter createDuplexSentCallCompleter() {
		return new ALocalResponseDuplexSentCallCompleter(
				duplexRPCInputPort,
				sharedSenderReceiverState().localRemoteReferenceTranslator);
	}

}
