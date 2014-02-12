package port.ot;



public class ACharInsertion extends AnEdit implements CharInsertion{
//	String name;
//	
//	int index;
//	char ch;
	public ACharInsertion (String theName, int theIndex, char theCh) {
		super(theName, theIndex, theCh);
	}
//	/* (non-Javadoc)
//	 * @see widgets.CharInsertion#getIndex()
//	 */
//	public int getIndex() {
//		return index;
//	}
//	/* (non-Javadoc)
//	 * @see widgets.CharInsertion#setIndex(int)
//	 */
//	public void setIndex(int index) {
//		this.index = index;
//	}
//	/* (non-Javadoc)
//	 * @see widgets.CharInsertion#getCh()
//	 */
//	public char getChar() {
//		return ch;
//	}
//	/* (non-Javadoc)
//	 * @see widgets.CharInsertion#setCh(int)
//	 */
//	public void setChar( char ch) {
//		this.ch = ch;
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
	public Edit copy() {
		return new ACharInsertion(name, index, ch);
	}
}
