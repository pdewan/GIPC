package extraip;

import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

public interface PureInternalDuplexRPCInputPort {
	LocalRemoteReferenceTranslator getRemoteHandler();
	void setRemoteHandler(LocalRemoteReferenceTranslator aHandler);
	
}
