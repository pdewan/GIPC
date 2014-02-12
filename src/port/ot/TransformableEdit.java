package port.ot;

import java.io.Serializable;

public interface TransformableEdit extends Serializable {

	public abstract Edit getEdit();

	public abstract void setEdit(Edit newVal);

	public abstract boolean isServer();

	public abstract void setServer(boolean isServer);
	
	public TransformableEdit copy();

}