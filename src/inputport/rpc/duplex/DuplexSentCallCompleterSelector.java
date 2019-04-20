package inputport.rpc.duplex;

public class DuplexSentCallCompleterSelector {
	static DuplexSentCallCompleterFactory 
	    duplexSentCallCompleterFactory;

	public static DuplexSentCallCompleterFactory getDuplexSentCallCompleterFactory() {
		return duplexSentCallCompleterFactory;
	}

	public static void setDuplexSentCallCompleterFactory(
			DuplexSentCallCompleterFactory newVal) {
		if (newVal == null) {
			System.err.println("Null factory set in " + DuplexSentCallCompleterSelector.class);
		}
		duplexSentCallCompleterFactory = newVal;
	}
	
	public static void setUninitializedDuplexSentCallCompleterFactory(
			DuplexSentCallCompleterFactory newVal) {
		if (duplexSentCallCompleterFactory == null)
		duplexSentCallCompleterFactory = newVal;
	}
	public static DuplexSentCallCompleter createDuplexSentCallCompleter(
			 DuplexRPCInputPort anInputPort, LocalRemoteReferenceTranslator aTranslator) {
 
		return duplexSentCallCompleterFactory.
		             createDuplexSentCallCompleter(anInputPort, aTranslator);
	}

	

}
