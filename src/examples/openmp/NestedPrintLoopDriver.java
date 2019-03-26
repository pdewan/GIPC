package examples.openmp;

public class NestedPrintLoopDriver {
	public static void main(String[] args) {
		String[][] aTable = {{"A11", "A12", "A13"}, {"A21", "A22"}, {"A31"}};
		System.out.println("Single-Threaded");
		MiscExamples.atomicPrint(aTable);
		System.out.println("Multi-Threaded");
		ParallelMiscExamples.atomicPrint(aTable);
	}

}
