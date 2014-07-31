package examples.rmi.arithmetic.stateful;

import java.rmi.RemoteException;

public class AStatefulMixedTypeProcessor implements RemoteStatefulMixedTypeProcessor {
	Integer num1;
	Double num2;
	
	boolean notInitialized() {
		return num1 == null || num2 == null;
	}

	@Override
	public Integer intAdd() {
		if (notInitialized()) return null;
		int retVal = num1 + (int) num2.intValue();
//		System.out.println("The int addition:"+ (num1 + (int) num2));
		return retVal;
	}
	@Override
	public Double doubleAdd() {
		if (notInitialized()) return null;
		double retVal = (double) num1 +  num2;
//		System.out.println("The double addition:"+ ( (double) num1 + num2));
		return retVal;
	}
	@Override
	public Integer intMultiply() {
		if (notInitialized()) return null;
		int retVal = num1 * (int) num2.intValue();
//		System.out.println("The int multiplication:"+ (num1 * (int) num2));
		return retVal;
	}
	@Override
	public Double doubleMultiply() {
		if (notInitialized()) return null;
		double retVal = (double) num1 *  num2;
//		System.out.println("The double multiplication:"+ ( (double) num1 * num2));
		return retVal;
	}
	@Override
	public void setInt(Integer newVal) throws RemoteException {
		num1 = newVal;
		
	}
	@Override
	public void setDouble(Double newVal) throws RemoteException {
		num2 = newVal;		
	}

}
