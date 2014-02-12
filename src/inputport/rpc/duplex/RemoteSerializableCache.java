package inputport.rpc.duplex;

public interface RemoteSerializableCache {
	Object get (RemoteSerializable aRemoteSerializable);
	Object put (RemoteSerializable aRemoteSerializable, Object proxy);

}
