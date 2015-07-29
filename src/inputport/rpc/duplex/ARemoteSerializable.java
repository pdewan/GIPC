package inputport.rpc.duplex;

public class ARemoteSerializable implements RemoteSerializable {
	String remoteEndName, type, objectName;

	public ARemoteSerializable(String aRemoteEndName, 
			String aClass,
			String anObjectName) {
		remoteEndName = aRemoteEndName;
		type = aClass;
		objectName = anObjectName;
	}

	@Override
	public String getTypeName() {
		return type;
	}
	public void setTypeName(String newVal) {
		 type = newVal;
	}
	@Override
	public String getObjectName() {
		return objectName;
	}
	@Override
	public String getRemoteEndName() {
		return remoteEndName;
	}
	@Override
	public boolean equals (Object otherObject) {
		if (!(otherObject instanceof RemoteSerializable)) return false;
		RemoteSerializable otherRemoteSerializable = (RemoteSerializable) otherObject;
		return type.equals(otherRemoteSerializable.getTypeName()) &&
				remoteEndName.equals(otherRemoteSerializable.getRemoteEndName()) &&
				objectName.equals(otherRemoteSerializable.getObjectName());						
		
	}	
	
	public ARemoteSerializable() {
	}

	public void setRemoteEndName(String remoteEndName) {
		this.remoteEndName = remoteEndName;
	}

//	public void setInterfaceName(String interfaceName) {
//		this.type = interfaceName;
//	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}
	
	public String toString() {
		return remoteEndName + ":" + type + ":" + objectName;
	}
	public void initSerializedObject() {
		
	}
	

}
