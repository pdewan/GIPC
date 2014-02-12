package inputport.rpc.duplex;

import inputport.rpc.DirectedRPCProxyGenerator;

import java.rmi.Remote;
import java.util.Set;

import util.misc.HashIdentityMap;
import util.misc.IdentityMap;



public class ASavedWorkingLocalRemoteReferenceTranslator implements LocalRemoteReferenceTranslator {
	IdentityMap<RemoteSerializable, Object> serializedProxyToProxy = new HashIdentityMap();
	IdentityMap<Object, RemoteSerializable> objectToSerializedProxy = new HashIdentityMap();
	IdentityMap<String, Object> nameToRemote = new HashIdentityMap();
	public static final String GENERATED_SUFFIX = "!&";
	int objectId = 0;
	DuplexRPCInputPort duplexRPCInputPort;
	public ASavedWorkingLocalRemoteReferenceTranslator(DuplexRPCInputPort aDuplexRPCPort) {
		duplexRPCInputPort = aDuplexRPCPort;		
	}
	@Override
	public Object createRemoteSerializable(
			    String aRemoteEndName, 
				String aClass,
				String anObjectName) {
		return new ARemoteSerializable(aRemoteEndName, aClass, anObjectName);
	}
	@Override
	public Object getRemote(Object aRemoteSerializable) {
		if (serializedProxyToProxy.size() == 0) return null;
		Set<RemoteSerializable> keySet = serializedProxyToProxy.keySet();
		// so somehow a straight get does not return the right value
		// I guess this has to do wit hashcode etc being implemented
		// correctly for tables. Maybe a better equals for aRemoteSerializable will do the trick
		for (RemoteSerializable remoteSerializable:keySet) {
			if (remoteSerializable.equals(aRemoteSerializable)) {
				return serializedProxyToProxy.get(remoteSerializable);
			}			
		}
		return null;		
	}
	@Override
	public void connectRemoteAndRemoteSerializable(Object aRemoteSerializable, Object aRemote) {
		serializedProxyToProxy.put((RemoteSerializable) aRemoteSerializable, aRemote);
		objectToSerializedProxy.put(aRemote, (RemoteSerializable) aRemoteSerializable);
	}
	
	
	@Override
	public Object transformSentReference(
			Object possiblyRemote) {
		if (!(possiblyRemote instanceof Remote))
			return possiblyRemote;
		Remote remote = (Remote) possiblyRemote;
		// may return a generated reference or a received one
		RemoteSerializable remoteSerializable = objectToSerializedProxy.get(remote);
		
		// assuming this is reference to a local object
		// need to take care of the case when a proxy to a remote object was generated here
		if (remoteSerializable == null) {
			// why not check first if this a locally registered object before registering
			// I suppose client port will not automatically register
			// RPC registry should be able to go from object to name I suppose
			String objectName = GENERATED_SUFFIX + objectId;
			objectId++;
			duplexRPCInputPort.register(objectName, remote);
			remoteSerializable = new ARemoteSerializable(duplexRPCInputPort.getLocalName(), 
					remote.getClass().getName(), objectName);
			objectToSerializedProxy.put(remote, remoteSerializable);
		}
		return remoteSerializable;
	}
	@Override
	public void transformSentRemoteReferences(Object[] args) {
		if (args == null) return;
		for (int i = 0; i < args.length; i++) {
			args[i] = transformSentReference(args[i]);
		}
		
	}

@Override
	public Object transformReceivedReference(Object possiblyRemoteSerializable) {
		if (!(possiblyRemoteSerializable instanceof RemoteSerializable))
			return possiblyRemoteSerializable;
		RemoteSerializable remoteSerializable = (RemoteSerializable) possiblyRemoteSerializable;
		Object localObject = duplexRPCInputPort.getServerObject(remoteSerializable
				.getObjectName()); // in case it is a proxy to a local object
		Object proxy = getRemote(remoteSerializable);
		try {
			if (proxy == null) {
				Class remoteInterface = Class.forName(remoteSerializable
						.getTypeName());
				// generating proxy to local object also?
				// it is guranteed to be Remote? If original object was remote then so will this
				proxy = (Remote) DirectedRPCProxyGenerator.generateRPCProxy(
						duplexRPCInputPort, remoteSerializable
								.getRemoteEndName(), remoteInterface,
						remoteSerializable.getObjectName());
				serializedProxyToProxy.put(remoteSerializable, proxy);
				// with traditional map a call back for hashcode happens
				objectToSerializedProxy.put(proxy, remoteSerializable);
				if (localObject != null)
					objectToSerializedProxy.put(localObject,
							remoteSerializable);
			}
			if (localObject != null)
				return localObject;
			return proxy; // this means will
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//@Override
//public Object transformReceivedReference(Object possiblyRemoteSerializable) {
//	if (!(possiblyRemoteSerializable instanceof RemoteSerializable))
//		return possiblyRemoteSerializable;
//	RemoteSerializable remoteSerializable = (RemoteSerializable) possiblyRemoteSerializable;
//	Object sentRemote = duplexRPCInputPort.getServer(remoteSerializable.getObjectName());
//	// check if previously sent object 
//	if (sentRemote != null)
//		return sentRemote;
//	// check if previously received object
//    Remote retVal =  (Remote) getProxy(remoteSerializable);
//	try {
//	if (retVal == null) {		
//		Class remoteInterface = Class.forName(remoteSerializable.getTypeName());
//		retVal = (Remote) StaticRPCProxyGenerator.generateRPCProxy(duplexRPCInputPort, remoteSerializable.getRemoteEndName(), remoteInterface, remoteSerializable.getObjectName());
//		
//		remoteSerializableToProxy.put(remoteSerializable, retVal);
//		// for further propagation
//		// with traditional map a call back for hashcode happens
//		proxyToRemoteSerializable.put(retVal, remoteSerializable); 		
//	}
//	return retVal;
//	} catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	}
//}
@Override
public void transformReceivedReferences(Object[] args) {
	if (args == null) return;
	for (int i = 0; i < args.length; i++) {
		args[i] = transformReceivedReference(args[i]);
	}
	
}
@Override
public RemoteSerializable getRemoteSerializable(Object aProxy) {
	// TODO Auto-generated method stub
	return null;
}

	

}
