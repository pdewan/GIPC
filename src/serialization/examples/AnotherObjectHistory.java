package serialization.examples;

import java.util.ArrayList;
import java.util.List;

public class AnotherObjectHistory<ElementType> implements ObjectHistory<ElementType> {
	List<ElementType> contents = new ArrayList();

	public int size() {
		return contents.size();
	}
	
	public ElementType get (int index) {
		return contents.get(index);
	}

	public void add(ElementType element) {
		 contents.add(element);
	} 
	
}
