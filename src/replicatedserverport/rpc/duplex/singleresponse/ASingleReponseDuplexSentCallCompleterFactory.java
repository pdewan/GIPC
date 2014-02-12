package replicatedserverport.rpc.duplex.singleresponse;

import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.DuplexSentCallCompleter;
import inputport.rpc.duplex.DuplexSentCallCompleterFactory;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public class ASingleReponseDuplexSentCallCompleterFactory  
             implements DuplexSentCallCompleterFactory {

	@Override
	public DuplexSentCallCompleter createDuplexSentCallCompleter(
			 DuplexRPCInputPort anInputPort, LocalRemoteReferenceTranslator aTranslator) {
 
		return new ASingleResponseDuplexSentCallCompleter(anInputPort, aTranslator);
	}
}


