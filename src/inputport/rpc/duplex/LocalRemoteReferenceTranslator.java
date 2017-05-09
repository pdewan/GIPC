package inputport.rpc.duplex;

import inputport.rpc.duplex.referencetranslator.VisitedObjects;

public interface LocalRemoteReferenceTranslator {
	Object transformReceivedReference(Object possiblyRemote);
	Object transformReceivedReference(Object possiblyRemote, VisitedObjects visited);
	Object transformSentReference (Object possiblyRemote, Class aType);
	Object transformSentReference (Object possiblyRemote, Class aType, VisitedObjects visited);
	void transformReceivedReferences(Object[] args);
	void transformSentRemoteReferences(Object[] args, Class[] aTypes);
	Object getRemote(Object remoteSerializable);
	void connectRemoteAndRemoteSerializable(Object remoteSerializable, Object remote);
	Object createRemoteSerializable(String remoteEndName,
			String aClassName, String anObjectName);
	RemoteSerializable getRemoteSerializable(Object aProxy);

}
