package examples.mvc.rmi.collaborative;

public class DisplayLibrary {

	public static String toString(Object[] aCounterList) {
		String counterValsToString= "";
		for (int i = 0; i < aCounterList.length; i++) {
			if (i == 0)
				counterValsToString +=  aCounterList[i];
			else
				counterValsToString += "," + aCounterList[i];
		}
		return counterValsToString;
	}

}
