package inputport.rpc.duplex;

// used by latecomer module
public interface FunctionReturnValueDeterminer {
	public boolean isFunctionCall(Object aMessage);
	public boolean isReturnValue(Object aMessage);

}
