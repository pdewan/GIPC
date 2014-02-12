package port.ot;


public abstract class AnEdit implements Edit{
	String name;
	
	int index;
	char ch;
	public AnEdit (String theName, int theIndex, char theCh) {
		index = theIndex;
		ch = theCh;
		name = theName;
	}
	/* (non-Javadoc)
	 * @see widgets.CharInsertion#getIndex()
	 */
	public int getIndex() {
		return index;
	}
	/* (non-Javadoc)
	 * @see widgets.CharInsertion#setIndex(int)
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/* (non-Javadoc)
	 * @see widgets.CharInsertion#getCh()
	 */
	public char getChar() {
		return ch;
	}
	/* (non-Javadoc)
	 * @see widgets.CharInsertion#setCh(int)
	 */
	public void setChar( char ch) {
		this.ch = ch;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String toString() {
		return "index:" + index + " char:" + ch; 
	}
	
}
