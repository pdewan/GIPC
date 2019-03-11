package examples.openmp.samples;

public class Example4 {
	public static void main(String[] args) {
		// omp parallel
		{
			// omp critical
			{
				System.out.println("These lines");
				System.out.println("are always printed");
				System.out.println("in same order.");
			}
		}
	}
}
