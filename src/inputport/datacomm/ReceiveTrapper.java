package inputport.datacomm;

public interface ReceiveTrapper<InMessageType, OutMessageType>  extends 
		ReceiveNotifier<InMessageType>, SharedSenderReceiverState<Object>{
//	should parameterize by shared state but no antomatic way of adding type parameter to interface declaration

	ReceiveNotifier<OutMessageType> getDestination();
	void setDestination(ReceiveNotifier<OutMessageType> aDestination);
//	public Object getSharedState();	
//	public void setSharedState(Object newVal);

}
