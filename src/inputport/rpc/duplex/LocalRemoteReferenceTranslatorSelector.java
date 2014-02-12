package inputport.rpc.duplex;

public class LocalRemoteReferenceTranslatorSelector {
	static LocalRemoteReferenceTranslatorFactory translatorFactory = new ALocalRemoteReferenceTranslatorFactory();

	public static LocalRemoteReferenceTranslatorFactory getTranslatorFactory() {
		return translatorFactory;
	}

	public static void setTranslatorFactory(
			LocalRemoteReferenceTranslatorFactory newVal) {
		translatorFactory = newVal;
	}
	public static LocalRemoteReferenceTranslator createLocalRemoteReferenceTranslator(DuplexRPCInputPort aDuplexRPCPort) {
		return translatorFactory.createLocalRemoteReferenceTranslator(aDuplexRPCPort);
	}
}
