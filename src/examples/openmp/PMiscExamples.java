package examples.openmp;

public class PMiscExamples {
	public static String WHITES_SPACE = " ";
	public static void trace(Object... anArgs) {
		int threadNum = 0;
		int numThreads = 0;
//		threadNum = OMP4J_THREAD_NUM;
//		numTHreads = OMP4J_NUM_THREADS;
		if (numThreads > 0) {
			System.out.print(numThreads+":"+threadNum+":");
		}
		System.out.print(Thread.currentThread());
		for (Object anArg:anArgs) {
			System.out.print(":" + anArg);
		}
		System.out.println();
	}
	
	public static void round(Float[] aList) {
		

		/* === OMP CONTEXT === */
class OMPContext {
	public Float[] param_aList;
}
final OMPContext ompContext_atN = new OMPContext();
ompContext_atN.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_7kX = 0; i_7kX < ompContext_atN.param_aList.length; i_7kX++) {	final int i = i_7kX;
	ompExecutor.execute(new Runnable(){
		@Override
		public void run() {

 		ompContext_atN.param_aList[i] = (float) Math.round(ompContext_atN.param_aList[i]);
			trace(ompContext_atN.param_aList[i]);
			}});
}ompExecutor.waitForExecution();

	}
	public static boolean processIteration (int anIndex, int aThreadNum, int aNumThreads) {
		
//		aThreadNum = OMP4J_THREAD_NUM;
//		aNumThreads = OMP4J_NUM_THREADS;
		return aNumThreads == 0 || (anIndex%aNumThreads ==  aThreadNum);
	}
	public static void round(Float[] aList, int aThreadNum, int aNumThreads) {
//		int aThreadNum = OMP4J_THREAD_NUM;
//		int aNumThreads = OMP4J_NUM_THREADS;
		trace("Round Started");
		for (int i = 0; i < aList.length; i++) {		
			if (processIteration(i, aThreadNum, aNumThreads) ) {
			aList[i] = (float) Math.round(aList[i]);
			trace(aList[i]);
			}
		}
	}
	
	public static Float sum(Float[] aList) {
		Float retVal = (float) 0.0;
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace(retVal);
		}
		return retVal;
	}
	
	public static Float sum(Float[] aList, int aThreadNum, int aNumThreads) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		for (int i = 0; i < aList.length; i++) {
			if (processIteration(i, aThreadNum, aNumThreads) ) {
			retVal += aList[i];
			trace(retVal);
			}
		}
		return retVal;
	}
	
	public static void roundSum(Float[] aList) {
		

		/* === OMP CONTEXT === */
class OMPContext_0Wv {
	public Float[] param_aList;
}
final OMPContext_0Wv ompContext_i1p = new OMPContext_0Wv();
ompContext_i1p.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_YTG = new org.omp4j.runtime.DynamicExecutor(2);
/* === /OMP CONTEXT === */
for (int ompI = 0; ompI < 2; ompI++) {
	ompExecutor_YTG.execute(new Runnable(){
		@Override
		public void run() {
{
			int aThreadNum = 0;
			int aNumThreads = 0;
			aThreadNum = ompExecutor_YTG.getThreadNum();
			aNumThreads = ompExecutor_YTG.getNumThreads();
			round(ompContext_i1p.param_aList, aThreadNum, aNumThreads);
			Float aSum = sum(ompContext_i1p.param_aList, aThreadNum, aNumThreads);
			trace("Final Sum:" + aSum);			
		}		}
	});
}
ompExecutor_YTG.waitForExecution();

	}
	
	public static int atomicSum(short[] aList) {
		int retVal = 0;
		

		/* === OMP CONTEXT === */
class OMPContext_KLg {
	public short[] param_aList;
	public int local_retVal;
}
final OMPContext_KLg ompContext = new OMPContext_KLg();
ompContext.param_aList = aList;
ompContext.local_retVal = retVal;
final org.omp4j.runtime.IOMPExecutor ompExecutor_199 = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_Ygr = 0; i_Ygr < ompContext.param_aList.length; i_Ygr++) {	final int i = i_Ygr;
	ompExecutor_199.execute(new Runnable(){
		@Override
		public void run() {

			

			synchronized(ompContext) {
ompContext.local_retVal += ompContext.param_aList[i];}

			trace(ompContext.local_retVal);
			}});
}ompExecutor_199.waitForExecution();

		return retVal;
	}
	public static int reducedSum(short[] aList) {
		int retVal = 0;
		

		/* === OMP CONTEXT === */
class OMPContext_a8d {
	public short[] param_aList;
	public int local_retVal[];
}
final OMPContext_a8d ompContext_Y9z = new OMPContext_a8d();
ompContext_Y9z.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_qYk = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
ompContext_Y9z.local_retVal = new int[Runtime.getRuntime().availableProcessors()];
for (int ompK = 0; ompK < Runtime.getRuntime().availableProcessors(); ompK++) {/*!!!*/
	ompContext_Y9z.local_retVal[ompK]= new Integer(0);
}
for (int i_XFI = 0; i_XFI < ompContext_Y9z.param_aList.length; i_XFI++) {	final int i = i_XFI;
	ompExecutor_qYk.execute(new Runnable(){
		@Override
		public void run() {

			ompContext_Y9z.local_retVal[ompExecutor_qYk.getThreadNum()] += ompContext_Y9z.param_aList[i];
			trace(ompContext_Y9z.local_retVal[ompExecutor_qYk.getThreadNum()]);
			}});
}ompExecutor_qYk.waitForExecution();

		return retVal;
	}
	public static long product(short[] aList) {
		long retVal = 1;
		

		/* === OMP CONTEXT === */
class OMPContext_5Vz {
	public short[] param_aList;
	public long local_retVal;
}
final OMPContext_5Vz ompContext_cHO = new OMPContext_5Vz();
ompContext_cHO.param_aList = aList;
ompContext_cHO.local_retVal = retVal;
final org.omp4j.runtime.IOMPExecutor ompExecutor_5Mi = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_ZEg = 0; i_ZEg < ompContext_cHO.param_aList.length; i_ZEg++) {	final int i = i_ZEg;
	ompExecutor_5Mi.execute(new Runnable(){
		@Override
		public void run() {

			ompContext_cHO.local_retVal *= ompContext_cHO.param_aList[i];
			trace(ompContext_cHO.local_retVal);
			}});
}ompExecutor_5Mi.waitForExecution();

		return retVal;
	}
	public static String concat(String[] aList) {
		String retVal = "";
		

		/* === OMP CONTEXT === */
class OMPContext_Ci6 {
	public String[] param_aList;
	public String local_retVal;
}
final OMPContext_Ci6 ompContext_ANK = new OMPContext_Ci6();
ompContext_ANK.param_aList = aList;
ompContext_ANK.local_retVal = retVal;
final org.omp4j.runtime.IOMPExecutor ompExecutor_S3m = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_kIx = 0; i_kIx < ompContext_ANK.param_aList.length; i_kIx++) {	final int i = i_kIx;
	ompExecutor_S3m.execute(new Runnable(){
		@Override
		public void run() {

			ompContext_ANK.local_retVal += ompContext_ANK.param_aList[i];
			trace(ompContext_ANK.local_retVal);
			}});
}ompExecutor_S3m.waitForExecution();

		return retVal;
	}
	public static short minus(int[] aList) {
		short retVal = 0;
		

		/* === OMP CONTEXT === */
class OMPContext_Bea {
	public int[] param_aList;
	public short local_retVal;
}
final OMPContext_Bea ompContext_J35 = new OMPContext_Bea();
ompContext_J35.param_aList = aList;
ompContext_J35.local_retVal = retVal;
final org.omp4j.runtime.IOMPExecutor ompExecutor_bOp = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_fw0 = 0; i_fw0 < ompContext_J35.param_aList.length; i_fw0++) {	final int i = i_fw0;
	ompExecutor_bOp.execute(new Runnable(){
		@Override
		public void run() {

			ompContext_J35.local_retVal -= ompContext_J35.param_aList[i];
			trace(ompContext_J35.local_retVal);
			}});
}ompExecutor_bOp.waitForExecution();

		return retVal;
	}
	public static void print(String[][] aTable) {
		

		/* === OMP CONTEXT === */
class OMPContext_1jm {
	public String[][] param_aTable;
}
final OMPContext_1jm ompContext_snO = new OMPContext_1jm();
ompContext_snO.param_aTable = aTable;
final org.omp4j.runtime.IOMPExecutor ompExecutor_ukZ = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_FcN=0; i_FcN<ompContext_snO.param_aTable.length;i_FcN++) {	final int i = i_FcN;
	ompExecutor_ukZ.execute(new Runnable(){
		@Override
		public void run() {

			trace(i);
			print(ompContext_snO.param_aTable[i], Thread.currentThread());
			}});
}ompExecutor_ukZ.waitForExecution();

		
	}
	
	public static void print(String[] aList, Thread aParentThread) {
		

		/* === OMP CONTEXT === */
class OMPContext_cjV {
	public String[] param_aList;
}
final OMPContext_cjV ompContext_zyN = new OMPContext_cjV();
ompContext_zyN.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_IY9 = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_yZr=0; i_yZr<ompContext_zyN.param_aList.length;i_yZr++) {	final int i = i_yZr;
	ompExecutor_IY9.execute(new Runnable(){
		@Override
		public void run() {

			trace(i, ompContext_zyN.param_aList[i]);			
			}});
}ompExecutor_IY9.waitForExecution();
		
	}
	public static void atomicPrint(String[] aList, Thread aParentThread) {
		

		/* === OMP CONTEXT === */
class OMPContext_BD9 {
	public String[] param_aList;
}
final OMPContext_BD9 ompContext_9Y2 = new OMPContext_BD9();
ompContext_9Y2.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_U1R = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int i_VhE=0; i_VhE<ompContext_9Y2.param_aList.length;i_VhE++) {	final int i = i_VhE;
	ompExecutor_U1R.execute(new Runnable(){
		@Override
		public void run() {

			

			synchronized(ompContext_9Y2) {
trace(i, ompContext_9Y2.param_aList[i]);}
			
			}});
}ompExecutor_U1R.waitForExecution();
		
	}
	public static void atomicPrint(String[][] aTable) {
		

		/* === OMP CONTEXT === */
class OMPContext_Fdv {
	public String[][] param_aTable;
}
final OMPContext_Fdv ompContext_Gco = new OMPContext_Fdv();
ompContext_Gco.param_aTable = aTable;
final org.omp4j.runtime.IOMPExecutor ompExecutor_CDq = new org.omp4j.runtime.DynamicExecutor(2);
/* === /OMP CONTEXT === */
for (int i_TC7=0; i_TC7<ompContext_Gco.param_aTable.length;i_TC7++) {	final int i = i_TC7;
	ompExecutor_CDq.execute(new Runnable(){
		@Override
		public void run() {

			trace(i);
			print(ompContext_Gco.param_aTable[i], Thread.currentThread());
			}});
}ompExecutor_CDq.waitForExecution();
		
	}
	
}