package examples.openmp;

public class PTestExample {
	static int[] a = { 1, 2, 3, 4, 5, 6, 7, 8 };
	static int sum = 0;

public static void main(String[] args) {
		
		

		/* === OMP CONTEXT === */
class OMPContext {
	public int field_a[];
	public int field_sum;
}
final OMPContext ompContext = new OMPContext();
ompContext.field_a = a;
ompContext.field_sum = sum;
final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_GSY = 0; i_GSY < ompContext.field_a.length; i_GSY += 2) {	final int i = i_GSY;
	ompExecutor.execute(new Runnable(){
		@Override
		public void run() {

			

			synchronized(ompContext) {
ompContext.field_sum += ompContext.field_a[i];}

			Thread parentThread = Thread.currentThread();
			System.out.println("Pre_nested:" + parentThread + "i " + i + " " + ompContext.field_sum);
			

			/* === OMP CONTEXT === */
class OMPContext_nA6 {
	public Thread local_parentThread;
}
final OMPContext_nA6 ompContext_VbG = new OMPContext_nA6();
ompContext_VbG.local_parentThread = parentThread;
final org.omp4j.runtime.IOMPExecutor ompExecutor_3DU = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int j_CQz = 0; j_CQz < 5; j_CQz++ ) {	final int j = j_CQz;
	ompExecutor_3DU.execute(new Runnable(){
		@Override
		public void run() {

				ompContext.field_sum += j;
				System.out.println("Parent:" + ompContext_VbG.local_parentThread + Thread.currentThread() + "i " + i + "j " + j + " " + ompContext.field_sum);

				}});
}ompExecutor_3DU.waitForExecution();

			System.out.println("Post_nested:" + Thread.currentThread() + "i " + i + " " + ompContext.field_sum);
			System.out.println(Thread.currentThread() + "i " + i + " " + ompContext.field_sum);



			}});
}ompExecutor.waitForExecution();

		System.out.println(Thread.currentThread() + " " +  sum);

	}
}