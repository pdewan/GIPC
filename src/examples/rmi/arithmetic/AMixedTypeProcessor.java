package examples.rmi.arithmetic;

public class AMixedTypeProcessor implements RemoteMixedTypeProcessor {

	@Override
	public int intAdd(int num1, double num2) {
		int retVal = num1 + (int) num2;
		System.out.println("The int addition:"+ (num1 + (int) num2));
		return retVal;
	}
	@Override
	public double doubleAdd(int num1, double num2) {
		double retVal = (double) num1 +  num2;
		System.out.println("The double addition:"+ ( (double) num1 + num2));
		return retVal;
	}
	@Override
	public int intMultiply(int num1, double num2) {
		int retVal = num1 * (int) num2;
		System.out.println("The int multiplication:"+ (num1 * (int) num2));
		return retVal;
	}
	@Override
	public double doubleMultiply(int num1, double num2) {
		double retVal = (double) num1 *  num2;
		System.out.println("The double multiplication:"+ ( (double) num1 * num2));
		return retVal;
	}

}
