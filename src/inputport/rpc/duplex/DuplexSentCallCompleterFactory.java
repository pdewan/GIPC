package inputport.rpc.duplex;

public interface DuplexSentCallCompleterFactory {
	DuplexSentCallCompleter 
	     createDuplexSentCallCompleter(
	    		 DuplexRPCInputPort anInputPort, LocalRemoteReferenceTranslator aTranslator);

}
