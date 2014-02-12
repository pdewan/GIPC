package inputport.rpc.duplex;


public interface LocalRemoteReferenceTranslator {
	Object transformReceivedReference(Object possiblyRemote);
	Object transformSentReference (Object possiblyRemote);
	void transformReceivedReferences(Object[] args);
	void transformSentRemoteReferences(Object[] args);
	Object getRemote(Object remoteSerializable);
	void connectRemoteAndRemoteSerializable(Object remoteSerializable, Object remote);
	Object createRemoteSerializable(String remoteEndName,
			String aClassName, String anObjectName);
	RemoteSerializable getRemoteSerializable(Object aProxy);

}
