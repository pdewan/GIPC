package port.jitter;


public class TimeStampUtility {
//	public static  Object wrapMessageWithTimeStamp (Object aMessage) {
//		if (aMessage instanceof MessageWithSource)
//			return wrapRelayedMessageWithTimeStamp((MessageWithSource)aMessage);
//		else
//			return wrapApplicationMessageWithTimeStamp(aMessage);
//	}
	
     public static  MessageWithTimeStamp wrapApplicationMessageWithTimeStamp (Object aMessage) {
		return new AMessageWithTimeStamp(aMessage, System.currentTimeMillis());
	}
    
     
//     public static  Object  wrapRelayedMessageWithTimeStamp (MessageWithSource aMessage) {
//    	 Object anUnwrappedMessage = aMessage.getMessage();
//    	 MessageWithTimeStamp aMessageWithTimeStamp = wrapApplicationMessageWithTimeStamp(anUnwrappedMessage);
//    	 aMessage.setMessage(aMessageWithTimeStamp);
//    	 return aMessage; 		
// 	}
     
     
    

}
