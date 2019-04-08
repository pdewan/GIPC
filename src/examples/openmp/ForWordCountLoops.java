package examples.openmp;

import java.util.HashMap;
import java.util.Map;

public class ForWordCountLoops {
	protected static boolean trace = true;

	public static boolean isTrace() {
		return trace;
	}

	public static void setTrace(boolean newVal) {
		trace = newVal;
	}

	public static void trace(Object... anArgs) {

		if (isTrace())
			System.out.print(Thread.currentThread());
		for (Object anArg : anArgs) {
			System.out.print(" " + anArg);
		}
		System.out.println();
	}

	
	public static void add (Map<String, Integer> aMap, String aKey, Integer aValue) {
		Integer anOriginalValue = aMap.get(aKey);
		Integer aNewValue = aValue + (anOriginalValue == null?0:anOriginalValue);
		aMap.put(aKey, aNewValue); 
	}
	// omp declare reduction \
	// (mapAdd:java.util.Map<String, Integer>:omp_out=mapPlus(omp_out,omp_in)) \
	// initializer(omp_priv= new java.util.HashMap())
	public static void mapPlus (Map<String, Integer> aMap, Map<String, Integer> anAddition) {
		for (String aKey:anAddition.keySet()) {
			add(aMap, aKey,anAddition.get(aKey));
		}
	}
	public static Map<String, Integer> wordCount(String[] aWords) {
		Map<String, Integer> aWordCount = new HashMap();
		for (int i = 0; i < aWords.length; i++) {
			add (aWordCount, aWords[i], 1);
		}		
		return aWordCount;
	}
	public static Map<String, Integer> reducableWordCount(String[] aWords) {
		Map<String, Integer> aWordCount = new HashMap();
		// omp parallel for threadNum(aNumThreads) reduction(mapAdd:aWordCount)
		for (int i = 0; i < aWords.length; i++) {
			Map<String, Integer> anAddition = new HashMap();
			anAddition.put(aWords[i], 1);
			mapPlus(aWordCount, anAddition);
		}		
		return aWordCount;
	}
	
	
}
