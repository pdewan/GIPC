package inputport.rpc.duplex;

public class DuplexSentCallCompleterSelector {
	static DuplexSentCallCompleterFactory 
	    duplexSentCallCompleterFactory;

	public static DuplexSentCallCompleterFactory getDuplexSentCallCompleterFactory() {
		return duplexSentCallCompleterFactory;
	}

	public static void setDuplexSentCallCompleterFactory(
			DuplexSentCallCompleterFactory newVal) {
		duplexSentCallCompleterFactory = newVal;
	}
	public static DuplexSentCallCompleter createDuplexSentCallCompleter(
			 DuplexRPCInputPort anInputPort, LocalRemoteReferenceTranslator aTranslator) {
 
		return duplexSentCallCompleterFactory.
		             createDuplexSentCallCompleter(anInputPort, aTranslator);
	}

	

}
