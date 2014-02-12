package inputport.rpc.duplex;

import inputport.rpc.DirectedRPCProxyGenerator;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Proxy;
import java.rmi.Remote;
import java.util.Set;

import util.misc.Common;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.misc.RemoteReflectionUtility;
import util.remote.InvocationHandlerWithProperties;
import util.trace.Tracer;



public class ALocalRemoteReferenceTranslator implements LocalRemoteReferenceTranslator {
	// mapping between a serialized reference and a remote proxy object or an exported local object
	IdentityMap<RemoteSerializable, Object> remoteSerializableToRemote = new HashIdentityMap();
	IdentityMap<Object, RemoteSerializable> remoteToRemoteSerializable = new HashIdentityMap(); // each object can map to multiple remote serializables
	IdentityMap<String, Object> nameToRemote = new HashIdentityMap();
	public static final String GENERATED_SUFFIX = "!&";
	int objectId = 0;
	DuplexRPCInputPort duplexRPCInputPort;
	public ALocalRemoteReferenceTranslator(DuplexRPCInputPort aDuplexRPCPort) {
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
		if (remoteSerializableToRemote.size() == 0) return null;
		Set<RemoteSerializable> keySet = remoteSerializableToRemote.keySet();
		// so somehow a straight get does not return the right value
		// I guess this has to do wit hashcode etc being implemented
		// correctly for tables. Maybe a better equals for aRemoteSerializable will do the trick
		for (RemoteSerializable remoteSerializable:keySet) {
			if (remoteSerializable.equals(aRemoteSerializable)) {
				return remoteSerializableToRemote.get(remoteSerializable);
			}			
		}
		return null;		
	}
	@Override
	public RemoteSerializable getRemoteSerializable (Object aRemote) {
		Class remoteClass = aRemote.getClass();
		if (aRemote instanceof Proxy) { 
			InvocationHandlerWithProperties handler =  (InvocationHandlerWithProperties) Proxy.getInvocationHandler((Proxy) aRemote);
			remoteClass = handler.getProxyTargetClass();
		} 
		RemoteSerializable retVal =  remoteToRemoteSerializable.get(aRemote);
		Tracer.info(this, "Connverted:" + aRemote + " to " + retVal);
		return retVal;
		// if null maybe we should check if it os registered under another name
		// that is basically for efficiency and checking has its own overhead.
//		return remoteToRemoteSerializable.get(aRemote);
	}
	@Override
	public void connectRemoteAndRemoteSerializable(Object aRemoteSerializable, Object aRemote) {
		remoteSerializableToRemote.put((RemoteSerializable) aRemoteSerializable, aRemote);
		remoteToRemoteSerializable.put(aRemote, (RemoteSerializable) aRemoteSerializable);
	}
	
	
	@Override
	public Object transformSentReference(
			Object possiblyRemote) {
		// decides if we serialize the object or send a reference
		// if it is not serialiable maybe it is a proxy to a remote object that is not an instance of Reemote
		// can handle such an object
		// even if original object is serializable, proxy is not, so send its reference instead
		if (possiblyRemote == null || possiblyRemote instanceof RemoteSerializable ||
				(possiblyRemote instanceof Serializable  && 
				!(possiblyRemote instanceof Proxy) && !(possiblyRemote instanceof Remote)))
			return deepTransformSentReference(possiblyRemote);
//		return possiblyRemote;

//		Remote remote = (Remote) possiblyRemote;
		// may return a generated reference or a received one
		RemoteSerializable remoteSerializable = remoteToRemoteSerializable.get(possiblyRemote);
		
		
		// assuming this is reference to a local object
		// need to take care of the case when a proxy to a remote object was generated here
		// if it was in an RPC registry it will not be null
		if (remoteSerializable == null) {
			// why not check first if this a locally registered object before registering
			// I suppose client port will not automatically register
			// RPC registry should be able to go from object to name I suppose
			String objectName = duplexRPCInputPort.getLocalName() + GENERATED_SUFFIX + objectId ; 
			objectId++;
			duplexRPCInputPort.register(objectName, possiblyRemote);			
			remoteSerializable = new ARemoteSerializable(duplexRPCInputPort.getLocalName(), 
					possiblyRemote.getClass().getName(), objectName);
			remoteToRemoteSerializable.put(possiblyRemote, remoteSerializable); // should we do this? 
		}
		if (remoteSerializable.getObjectName() == null) {
			Tracer.error("Cannot send proxy:" + possiblyRemote + " as it does not have a name");
		}

		return remoteSerializable;
	}
	@Override
	public void transformSentRemoteReferences(Object[] args) {
		if (args == null) return;
		for (int i = 0; i < args.length; i++) {
			Object oldVal = args[i];
			args[i] = transformSentReference(args[i]);
			Tracer.info(this, "Transformed send reference: " + oldVal + "to: " + args[i]);

		}
		
	}
	
    protected Object deepTransformSentReference(Object original)  {
    	if (original == null || RemoteReflectionUtility.isAtomicClass(original.getClass())) 
    			return original;
    	if (!(original instanceof Serializable)) // cannot deepCopy
    			return original;
    	Object copy;
    	try {
    		copy = Common.deepCopy(original);
    		
    	} catch (Exception e) {
    		return original;
    	}
    	if (copy == original) // could not serialize
    		return original;
    	
    	boolean changed = transformSentFieldsOfSuperTypes(original, copy, original.getClass());
    	if (changed) 
    		return copy;
    	
    	return original;
    }
   
    // true if something was changed in copy
    protected boolean transformSentFieldsOfSuperTypes(Object original, Object copy, Class aClass)  {
    	if (aClass.equals(Object.class) || RemoteReflectionUtility.isAtomicClass(aClass))
    		return false;
    	boolean changed = false;
    	Field[] fields = aClass.getDeclaredFields();
    	for (Field aField:fields) {
    		int  modifier = aField.getModifiers();
    		Class fieldType = aField.getType();
    		if (Modifier.isFinal(modifier) || Modifier.isStatic(modifier) || !(aField.getType().equals(Object.class)))
    				continue;
    		aField.setAccessible(true);
    		try {
    		Object originalFieldValue = aField.get(original);
    		if (originalFieldValue == null) 
    			continue;
    		Object transformedValue = transformSentReference(originalFieldValue);
    	
    		if (originalFieldValue != transformedValue) {
    			changed = true;
    			aField.set(copy, transformedValue);
    			continue;
    		} else {
    			Object copyFieldValue = aField.get(copy);
    			boolean subFieldChanged = transformSentFieldsOfSuperTypes(originalFieldValue, copyFieldValue, originalFieldValue.getClass());
    			changed = changed || subFieldChanged;
    		}
    		} catch (Exception e) {
    			continue;
    		}
    	}
    	boolean superTypeFieldChanged = transformSentFieldsOfSuperTypes(original, copy, aClass.getSuperclass());
    	return changed || superTypeFieldChanged;
    	
    	
    }
    protected Object deepTransformReceivedReference(Object original) {
    	if (original == null) return null;
    	boolean retVal = transformReceivedFieldsOfSuperTypes(original, original.getClass());
    	return original;
    }
 // true if something was changed in copy
    protected boolean transformReceivedFieldsOfSuperTypes(Object aReceivedObject, Class aClass)  {
    	if (aClass.equals(Object.class) || RemoteReflectionUtility.isAtomicClass(aClass))
    		return false;
    	boolean changed = false;
    	Field[] fields = aClass.getDeclaredFields();
    	for (Field aField:fields) {
    		int  modifier = aField.getModifiers();
    		Class fieldType = aField.getType();
    		if (Modifier.isFinal(modifier) || Modifier.isStatic(modifier) || !(aField.getType().equals(Object.class)))
    				continue;
    		aField.setAccessible(true);
    		try {
    		Object originalFieldValue = aField.get(aReceivedObject);
    		if (originalFieldValue == null) 
    			continue;
    		Object transformedValue = transformReceivedReference(originalFieldValue);
    	
    		if (originalFieldValue != transformedValue) {
    			changed = true;
    			aField.set(aReceivedObject, transformedValue);
    			continue;
    		} else {
    			boolean subFieldChanged = transformReceivedFieldsOfSuperTypes(originalFieldValue,  originalFieldValue.getClass());
    			changed = changed || subFieldChanged;
    		}
    		} catch (Exception e) {
    			continue;
    		}
    	}
    	boolean superTypeFieldChanged = transformReceivedFieldsOfSuperTypes(aReceivedObject, aClass.getSuperclass());
    	return changed || superTypeFieldChanged;
    	
    	
    }

@Override
	public Object transformReceivedReference(Object possiblyRemoteSerializable) {
		
		if (!(possiblyRemoteSerializable instanceof RemoteSerializable)) {
			 return deepTransformReceivedReference(possiblyRemoteSerializable);
//			 return possiblyRemoteSerializable;

//			return possiblyRemoteSerializable;
		}
		RemoteSerializable remoteSerializable = (RemoteSerializable) possiblyRemoteSerializable;
		Object localObject = duplexRPCInputPort.getServerObject(remoteSerializable
				.getObjectName()); // in case it is a proxy to a local object
		if (localObject != null)
			return localObject;
		
		Object proxy = getRemote(remoteSerializable);
		if (proxy != null)
			return proxy;
		// so this is a reference given by the sending site to its object.
		// generate a proxy so we can access this reference
		try {
			
				Class remoteInterface = Class.forName(remoteSerializable
						.getTypeName());
				// generating proxy to local object also?
				// it is guranteed to be Remote? If original object was remote then so will this
//				proxy = (Remote) StaticRPCProxyGenerator.generateRPCProxy(
						proxy = DirectedRPCProxyGenerator.generateRPCProxy(
						duplexRPCInputPort, remoteSerializable
								.getRemoteEndName(), remoteInterface,
						remoteSerializable.getObjectName());
				remoteSerializableToRemote.put(remoteSerializable, proxy);
				// with traditional map a call back for hashcode happens
				remoteToRemoteSerializable.put(proxy, remoteSerializable);
				
			return proxy; // this means will
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}

	
// this is the class version
//public Object transformReceivedReference(Object possiblyRemoteSerializable) {
//	if (!(possiblyRemoteSerializable instanceof RemoteSerializable))
//		return possiblyRemoteSerializable;
//	RemoteSerializable remoteSerializable = (RemoteSerializable) possiblyRemoteSerializable;
//	Object localObject = duplexRPCInputPort.getServer(remoteSerializable
//			.getObjectName()); // in case it is a proxy to a local object
//	Object proxy = getProxy(remoteSerializable);
//	try {
//		if (proxy == null) {
//			Class remoteInterface = Class.forName(remoteSerializable
//					.getTypeName());
//			// generating proxy to local object also?
//			// it is guranteed to be Remote? If original object was remote then so will this
//			proxy = (Remote) StaticRPCProxyGenerator.generateRPCProxy(
//					duplexRPCInputPort, remoteSerializable
//							.getRemoteEndName(), remoteInterface,
//					remoteSerializable.getObjectName());
//			serializedProxyToProxy.put(remoteSerializable, proxy);
//			// with traditional map a call back for hashcode happens
//			objectToSerializedProxy.put(proxy, remoteSerializable);
//			if (localObject != null)
//				objectToSerializedProxy.put(localObject,
//						remoteSerializable);
//		}
//		if (localObject != null)
//			return localObject;
//		return proxy; // this means will
//	} catch (Exception e) {
//		e.printStackTrace();
//		return null;
//	}
//}

// this is the first version
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
		Object oldVal = args[i];
		args[i] = transformReceivedReference(args[i]);
		// this calls toString and makes a remote call
		if (args[i] != oldVal) 
			Tracer.info(this, "Transformed received reference: " + System.identityHashCode(oldVal) + "to: " + System.identityHashCode(args[i]));

//		Tracer.info(this, "Transformed received reference: " + oldVal + "to: " + args[i]);

	}

	
}

	

}
