package inputport.rpc.duplex;

import inputport.rpc.simplex.ASimplexSentCallCompleter;



public abstract class AnAbstractDuplexSentCallCompleter extends ASimplexSentCallCompleter implements DuplexSentCallCompleter {

	protected boolean isReturnValue(Object message) {
		return message instanceof RPCReturnValue;
	}	
	protected abstract void processReturnValue(String source, Object message);	
	@Override
	public boolean maybeProcessReturnValue(String source, Object message) {		
		if (isReturnValue(message)) {
			processReturnValue(source, (RPCReturnValue) message);
			return true;
		} else
			return false;
	}	

}
