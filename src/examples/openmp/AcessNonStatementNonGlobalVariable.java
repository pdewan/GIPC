package examples.openmp;

public class AcessNonStatementNonGlobalVariable {
	public static void accessNonStatementNonGlobal() {
		int nonStatementNonGlobal = 0;
		//omp parallel threadNum(2) 
		nonStatementNonGlobal++;
	}
}
