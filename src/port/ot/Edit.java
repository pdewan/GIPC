package port.ot;

import java.io.Serializable;

public interface Edit extends Serializable {

	public abstract int getIndex();

	public abstract void setIndex(int index);

	public abstract char getChar();

	public abstract void setChar(char ch);
	public String getName();
	public void setName(String name);
	public Edit copy();
}