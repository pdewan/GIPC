package serialization.examples;

import java.util.ArrayList;
import java.util.List;

public class AnotherIntHistory implements IntHistory {
	List<Integer> contents = new ArrayList();
	public int size() {
		return contents.size();
	}	
	public int get (int index) {
		return contents.get(index);
	}
	public void add(int element) {
		contents.add(element);
	} 	
}
