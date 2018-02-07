package util.interactiveMethodInvocation;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import assignments.util.inputParameters.SimulationParametersListener;
/**
 * Uses reflection to automatically define and support syntax of 
 * commands for invoking certain kinds of methods interactively. 
 * These methods must take enum or primitive parameters.
 * @author Dewan
 *
 */
public abstract class ACommandToMethodCallTranslator implements CommandToMethodCallTranslator {
	static Scanner scanner = new Scanner(System.in);
	String[] booleanInputs = {"true", "false"};
	public static String PRINT_COMMAND = "print";
	public static String QUIT_COMMAND = "quit";
	
	
//	public  boolean parseBoolean(String aValue) {
//		return ("true".startsWith(aValue.toLowerCase()));
//	}
	public static String parseString(String aValue) {
		if (aValue.length() < 2 || 
				(aValue.charAt(0) != '"') ||
				aValue.charAt(aValue.length()-1) != '"') {
			System.out.println(aValue + "does not ssurrounding quotes");
			
		}
		return aValue.substring(1, aValue.length()-1);
	}
	@Override
	public <T>  T parseEnum(Class<T> anEnumClass, String aValue) {
		for (T aConstant:anEnumClass.getEnumConstants()) {
			if(aConstant.toString().toLowerCase().startsWith(aValue.toLowerCase())) return aConstant;
		}
		System.err.println (aValue + "does not match one of:" + Arrays.toString(anEnumClass.getEnumConstants()));
		return null;
	}
	
	@Override
	public Object parse(Class aClass, String aValue){
		if (aClass == Boolean.TYPE)
			return Boolean.parseBoolean(aValue);
		if (aClass == Integer.TYPE)
			return Integer.parseInt(aValue);
		if (aClass == Long.TYPE)
			return Long.parseLong(aValue);
		if (aClass == Double.TYPE)
			return Double.parseDouble(aValue);
		if (aClass == Float.TYPE)
			return Float.parseFloat(aValue);
		if (aClass == Character.TYPE)
			return aValue.charAt(0);
		if (aClass.isEnum())
			return parseEnum(aClass, aValue);
		if (aClass == String.class)
			return parseString(aValue);
		System.err.println(aClass.getSimpleName() + " is not a parsable class");
		return null;
	}
	
	@Override
	public Object[] unparseBooleanOrEnum(Class aClass) {
		if (aClass == Boolean.TYPE)
			return booleanInputs;
		if (aClass.isEnum())
			return aClass.getEnumConstants();
		return null;
	}
	
	
	@Override
	public Method parseMethod (Class aClass, String[] aTokens) {
		Method[] aMethods = aClass.getMethods();
		for (Method aMethod:aMethods) {
			Class[] aParameters = aMethod.getParameterTypes();
			if (aTokens.length-1 != aParameters.length) continue;
			if (aMethod.getName().toLowerCase().startsWith(aTokens[0].toLowerCase())) {
				return aMethod;
			}
		}
		System.err.println(aTokens[0] + " does not match a call to one of:" + Arrays.toString(aMethods));
		return null;
	}
	@Override
	public Object[] parseMethodArgs(Method aMethod, String[] aTokens) {
		Class[] aParameterTypes = aMethod.getParameterTypes();
		Object[] result = new Object[aParameterTypes.length];
		for (int i = 0; i < aParameterTypes.length; i++) {
			result[i] = parse(aParameterTypes[i], aTokens[i+1]);
		}
		return result;
	}
	@Override
	public void printMethodCommands(Method aMethod) {
		System.out.print(aMethod.getName());
		for (Class aClass:aMethod.getParameterTypes()) {
			System.out.print(" " + Arrays.toString(unparseBooleanOrEnum(aClass)));
		}
		System.out.println();
	}
	@Override
	public void printClassCommands(Class aClass) {
		for (Method aMethod:aClass.getMethods()) {
			printMethodCommands(aMethod);
		}
	}
	@Override
	public  String[] nextTokens() {
		String nextLine = scanner.nextLine();
		return nextLine.split("\\s+");		
	}
//	Pattern whiteSpace = Pattern.compile("\\s+");
	Pattern whiteSpace = Pattern.compile("\\s|$");

	@Override
	public boolean isPrintCommand(String[] aTokens) {		
		return PRINT_COMMAND.startsWith(aTokens[0].toLowerCase());
	}
	@Override
	public  boolean isQuitCommand(String[] aTokens) {		
		return QUIT_COMMAND.startsWith(aTokens[0].toLowerCase());
	}
	public static int indexOf(Pattern pattern, String s) {
	    Matcher matcher = pattern.matcher(s);
	    return matcher.find() ? matcher.start() : -1;
	}
	protected abstract void callMethod(Method aMethod, Object[] anArgs);
	@Override
	public void processCommands(Class aClass) {
		while (true) {
			System.out.println("Enter (prefixes of) print, quit or a method and its parameters separated by whitespace:");
			String nextLine = scanner.nextLine();
			int aSpaceIndex = indexOf(whiteSpace, nextLine);
			if (aSpaceIndex == -1) {
				System.out.println("Method name missing:" + nextLine);
				continue;
			}
			String aParamString = nextLine.substring(aSpaceIndex);
			String aCommand = nextLine.substring(0, aSpaceIndex);
//			String[] aTokens = nextLine.split("\\s+");
			String[] aParamTokens;
			if (aParamString.trim().isEmpty()) {
				aParamTokens = new String[0];
			} else {
				aParamTokens = aParamString.split(",");
			}
			String[] aTokens = new String[aParamTokens.length+1];
			aTokens[0] = aCommand.trim();
			for (int i = 0; i < aParamTokens.length; i++ ) {
				aTokens[i+1] = aParamTokens[i].trim();
			}
			

			if (isQuitCommand(aTokens)) {
				System.out.println("Exiting");
				System.exit(0);
			}
			if (isPrintCommand(aTokens)) {
				printClassCommands(SimulationParametersListener.class);
				continue;
			}
//			System.out.println(Arrays.toString(aTokens));
			Method aMethod = parseMethod(SimulationParametersListener.class, aTokens);
			if (aMethod == null) {
				continue;
			}
			Object[] anArgs = parseMethodArgs(aMethod, aTokens);
			System.out.println(aMethod.toString() + Arrays.toString(anArgs));
			callMethod(aMethod, anArgs);
		}
		
	}
//	public static void main (String[] args) {
//		processInput();
//		
//	}

}
