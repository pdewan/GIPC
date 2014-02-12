package port.ot;

public class AnEditWithOTTimeStamp implements EditWithOTTimeStamp {
	TransformableEdit edit;
	OTTimeStamp otTimeStamp;
	public AnEditWithOTTimeStamp(TransformableEdit theEdit, OTTimeStamp theTimeStamp ) {
		edit = theEdit;
		otTimeStamp = theTimeStamp;
	}
	public  TransformableEdit getTransformableEdit() {
		return edit;
	}
	public  void setTransformableEdit(TransformableEdit theCharInsertion) {
		edit = theCharInsertion;
	}
//	public TransformableEdit getCharInsertion() {
//		return edit;
//	}
//	public void setCharInsertion(TransformableEdit charInsertion) {
//		this.edit = charInsertion;
//	}
	public OTTimeStamp getOTTimeStamp() {
		return otTimeStamp;
	}
	public void setOtTimeStamp(OTTimeStamp otTimeStamp) {
		this.otTimeStamp = otTimeStamp;
	}
	public String toString() {
		return edit.toString() + " TS:" + otTimeStamp;
	}
	
}
