package port.ot;



public class ATransformableEdit implements TransformableEdit {	
	Edit edit;
	boolean isServer;
	public ATransformableEdit(Edit  theEdit, boolean theIsServer) {
		edit = theEdit;
		isServer = theIsServer;		
	}
	/* (non-Javadoc)
	 * @see ot.TransformableCharInsertion#getCharInsertion()
	 */
	public Edit getEdit() {
		return edit;
	}
	/* (non-Javadoc)
	 * @see ot.TransformableCharInsertion#setCharInsertion(widgets.CharInsertion)
	 */
	public void setEdit(Edit newVal) {
		this.edit = newVal;
	}
	/* (non-Javadoc)
	 * @see ot.TransformableCharInsertion#isServer()
	 */
	public boolean isServer() {
		return isServer;
	}
	/* (non-Javadoc)
	 * @see ot.TransformableCharInsertion#setServer(boolean)
	 */
	public void setServer(boolean isServer) {
		this.isServer = isServer;
	}
	public String toString() {
		return edit.toString() + " isServer:" + isServer;
	}
	public TransformableEdit copy() {
		return new ATransformableEdit(edit.copy(), isServer);
	}
}
