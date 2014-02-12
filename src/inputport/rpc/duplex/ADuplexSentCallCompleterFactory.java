package inputport.rpc.duplex;

public class ADuplexSentCallCompleterFactory  
             implements DuplexSentCallCompleterFactory {

	@Override
	public DuplexSentCallCompleter createDuplexSentCallCompleter(
			 DuplexRPCInputPort anInputPort, LocalRemoteReferenceTranslator aTranslator) {
 
		return new ADuplexSentCallCompleter(anInputPort, aTranslator);
	}
}


