package examples.openmp;

public class Random {
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
	public static void printRandom() {
		//omp parallel threadNum(2)
		trace(Math.random());
	}
	public static void atomicPrintRandom() {
		//omp parallel threadNum(2) 
		{
		//omp critical
		trace(Math.random());
		}
	}
	public static void atomicPrintRandomForkJoin() {
		trace("Forking");
		atomicPrintRandom();
		trace("Joined");	
	}
}
