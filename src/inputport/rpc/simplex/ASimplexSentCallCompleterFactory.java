package inputport.rpc.simplex;

public class ASimplexSentCallCompleterFactory  
             implements SimplexSentCallCompleterFactory {

	@Override
	public SimplexSentCallCompleter createSimplexSentCallCompleter() {
		return new ASimplexSentCallCompleter();
	}


}


