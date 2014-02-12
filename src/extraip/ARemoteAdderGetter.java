package extraip;


public class ARemoteAdderGetter implements RemoteAdderGetter {
	RemoteAdder remoteAdder;
	public ARemoteAdderGetter() {
		remoteAdder = new ARemoteAdder();		
	}
	@Override
	public RemoteAdder getRemoteAdder() {
		return remoteAdder;
	}

}
