package inputport.rpc.duplex;



public class FunctionReturnValueDeterminerSelector {
	static FunctionReturnValueDeterminer functionReturnValueDeterminer = new AFunctionReturnValueDeterminer();

	public static FunctionReturnValueDeterminer getFunctionReturnValueDeterminer() {
		return functionReturnValueDeterminer;
	}

	public static void setFunctionReturnValueDeterminer(
			FunctionReturnValueDeterminer newVal) {
		functionReturnValueDeterminer = newVal;
	}
	
	public static boolean isFunctionCall(Object aMessage) {
		return functionReturnValueDeterminer.isFunctionCall(aMessage);
	}

	public static boolean isReturnValue(Object aMessage) {
		return functionReturnValueDeterminer.isReturnValue(aMessage);
	}

	
	

}
