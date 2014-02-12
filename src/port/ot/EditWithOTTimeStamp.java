package port.ot;

import java.io.Serializable;


public interface EditWithOTTimeStamp extends Serializable{

	public abstract TransformableEdit getTransformableEdit();

	public abstract void setTransformableEdit(TransformableEdit newVal);
	public OTTimeStamp getOTTimeStamp() ;
	public void setOtTimeStamp(OTTimeStamp otTimeStamp) ;

	

}