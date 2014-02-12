package serialization.examples;

import java.io.Serializable;

public interface ObjectHistory<ElementType>  extends Serializable{
	public void add(ElementType element);
	public ElementType get (int index); 
	public int size();
}
