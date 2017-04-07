package examples.serialization;

import java.util.ArrayList;
import java.util.List;

public class AnotherStringHistory implements StringHistory {
	List<String> contents = new ArrayList();

	public int size() {
		return contents.size();
	}
	
	public String get (int index) {
		return contents.get(index);
	}

	public void add(String element) {
		contents.add(element);
	} 
	public String toString() {
		String retVal =  super.toString() + "(";
		for (int i = 0; i < size(); i++) {
			if (i != 0)
				retVal += ",";
			retVal += get(i);
		}
		return retVal + ")";
	}
}
