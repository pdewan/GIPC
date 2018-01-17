package assignments.util.inputParameters;

import java.lang.reflect.Method;

public interface CommandToMethodCallTranslator {

	public <T> T parseEnum(Class<T> anEnumClass, String aValue);

	public Object parseBooleanOrEnum(Class aClass, String aValue);

	public Object[] unparseBooleanOrEnum(Class aClass);

	public Method parseMethod(Class aClass, String[] aTokens);

	public Object[] parseMethodArgs(Method aMethod, String[] aTokens);

	public void printMethodCommands(Method aMethod);

	public void printClassCommands(Class aClass);

	public String[] nextTokens();

	public boolean isPrintCommand(String[] aTokens);

	public boolean isQuitCommand(String[] aTokens);

	public void processCommands(Class aClass);
	
	

}