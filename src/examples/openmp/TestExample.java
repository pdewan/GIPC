package examples.openmp;

public class TestExample {
	static int[] a = {1, 2, 3, 4, 5, 6, 7, 8};
	static int sum = 0;
	public static void main(String[] args) {
		
		// omp parallel for
		for (int i = 0; i < a.length; i += 2) {
			// omp critical
			sum += a[i];
			Thread parentThread = Thread.currentThread();
			System.out.println("Pre_nested:" + parentThread + "i " + i + " " + sum);
			// omp parallel for
			for (int j = 0; j < 5; j++ ) {
				sum += j;
				System.out.println("Parent:" + parentThread + Thread.currentThread() + "i " + i + "j " + j + " " + sum);

			}
			System.out.println("Post_nested:" + Thread.currentThread() + "i " + i + " " + sum);
			System.out.println(Thread.currentThread() + "i " + i + " " + sum);



		}
		System.out.println(Thread.currentThread() + " " +  sum);

	}
}
