package inputport.rpc;

import java.io.Serializable;


public interface SerializableCall extends  Serializable, RemoteCall<String> {
	SerializableMethod getSerializableMethod();
	public void setSerializableMethod(SerializableMethod newVal) ;	
//	public void setTargetObject(String targetObject) ;		
//	public void setArgs(Object[] args) ;
}
