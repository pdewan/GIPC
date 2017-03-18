package inputport.rpc.duplex;

import inputport.rpc.simplex.ASimplexSentCallCompleter;



public abstract class AnAbstractDuplexSentCallCompleter extends ASimplexSentCallCompleter implements DuplexSentCallCompleter {

	protected boolean isReturnValue(Object message) {
		return message instanceof RPCReturnValue;
	}	
	protected abstract void returnValueReceived(String source, Object message);	
	protected boolean returnValueIsNotified() {
		return false;
	}
	@Override
	/*
	 * Returns false if message should be sent to notifier
	 * Side effect processes return value
	 */
	public boolean maybeProcessReturnValue(String source, Object message) {		
		if (isReturnValue(message)) {
			returnValueReceived(source, (RPCReturnValue) message);
			return !returnValueIsNotified();
		} else
			return false;
	}	

}
