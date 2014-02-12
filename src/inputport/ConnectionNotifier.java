package inputport;

//methods common to both parties of a connection and names ensure we can use this in a session
public interface ConnectionNotifier extends ConnectNotifier, DisconnectNotifier{	
	

}
