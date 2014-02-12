package serialization.examples;

import java.io.Serializable;

public interface StringHistory  extends Serializable{
	public void add(String element);
	public String get (int index); 
	public int size();
}
