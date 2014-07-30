package examples.rmi.arithmetic.stateful;

import java.rmi.RemoteException;

public class AStatefulMixedTypeProcessor implements RemoteStatefulMixedTypeProcessor {
	int num1 = 0;
	double num2 = 0;

	@Override
	public int intAdd() {
		int retVal = num1 + (int) num2;
//		System.out.println("The int addition:"+ (num1 + (int) num2));
		return retVal;
	}
	@Override
	public double doubleAdd() {
		double retVal = (double) num1 +  num2;
//		System.out.println("The double addition:"+ ( (double) num1 + num2));
		return retVal;
	}
	@Override
	public int intMultiply() {
		int retVal = num1 * (int) num2;
//		System.out.println("The int multiplication:"+ (num1 * (int) num2));
		return retVal;
	}
	@Override
	public double doubleMultiply() {
		double retVal = (double) num1 *  num2;
//		System.out.println("The double multiplication:"+ ( (double) num1 * num2));
		return retVal;
	}
	@Override
	public void setInt(int newVal) throws RemoteException {
		num1 = newVal;
		
	}
	@Override
	public void setDouble(double newVal) throws RemoteException {
		num2 = newVal;		
	}

}
