package port.delay;
public interface DelayManager {
	public static int DELAY_THRESHOLD = 50;
	int getMinimumDelay(String name);
	void setMinimumDelay(String name, int theDelay);	
	int getDelayVariation();	
	void setDelayVariation(int theVariation);
	int computeDelay(String aDestination);
//	void setDelay(String theName, DelayableSendCommand theCommand);
}
