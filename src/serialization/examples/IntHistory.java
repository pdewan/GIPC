package serialization.examples;

import java.io.Serializable;

public interface IntHistory  extends Serializable{
	public void add(int element);
	public int get (int index); 
	public int size();
}
