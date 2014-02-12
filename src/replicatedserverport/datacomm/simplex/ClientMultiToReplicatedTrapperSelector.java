package replicatedserverport.datacomm.simplex;


public class ClientMultiToReplicatedTrapperSelector {
	static MultiToReplicatedTrapperSelector<Object, Object> trapperSelector = new AMultiToReplicatedTrapperSelector();;


	public static MultiToReplicatedTrapperSelector<Object, Object> getTrapperSelector() {
		return trapperSelector;
	}

	public static void setTrapperSelector(
			MultiToReplicatedTrapperSelector<Object, Object> trapperSelector) {
		ClientMultiToReplicatedTrapperSelector.trapperSelector = trapperSelector;
	}

}
