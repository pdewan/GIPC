package inputport.rpc.duplex;

public class ALocalRemoteReferenceTranslatorFactory implements LocalRemoteReferenceTranslatorFactory {

	@Override
	public LocalRemoteReferenceTranslator createLocalRemoteReferenceTranslator(
			DuplexRPCInputPort duplexRPCPort) {
		return new ALocalRemoteReferenceTranslator(duplexRPCPort);
	}

}
