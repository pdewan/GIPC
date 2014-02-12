package inputport.rpc.simplex;


public class SimplexSentCallCompleterSelector {
	static SimplexSentCallCompleterFactory 
    simplexSentCallCompleterFactory;

public static SimplexSentCallCompleterFactory getSimplexSentCallCompleterFactory() {
	return simplexSentCallCompleterFactory;
}

public static void setSimplexSentCallCompleterFactory(
		SimplexSentCallCompleterFactory newVal) {
	simplexSentCallCompleterFactory = newVal;
}
public static SimplexSentCallCompleter createSimplexSentCallCompleter() {

	return simplexSentCallCompleterFactory.
	             createSimplexSentCallCompleter();
}
	
	

}
