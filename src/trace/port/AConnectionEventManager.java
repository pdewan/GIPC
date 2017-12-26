package trace.port;

import inputport.datacomm.AReceiveRegistrarAndNotifier;
import inputport.datacomm.ReceiveListener;
import inputport.datacomm.ReceiveRegistrarAndNotifier;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.SendTrapperSetter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import util.models.Hashcodetable;
import util.trace.Tracer;

public class AConnectionEventManager  implements ConnectionEventListener {
	Set sendingSources = new HashSet();
	Set sendingDestinations = new HashSet();
	Set receivingSources = new HashSet();
	Set receivingDestinations = new HashSet();
	Set receiveOnlySources = new HashSet();
	Set sendOnlySources = new HashSet();

	Hashcodetable<Object, Set> sendSourceToDestinations = new Hashcodetable();
	Hashcodetable<Object, Set> receiveSourceToDestinations = new Hashcodetable();
	List sendChain = new ArrayList();
	List receiveChain = new ArrayList();
	
	public AConnectionEventManager() {
		
	}
	
	
	@Override
	public synchronized void newEvent(ConnectionEvent anEvent) {
		if (anEvent.isSent()) {
			newSentEvent(anEvent);
		} else {
			newReceivedEvent(anEvent);
		}
	}
	
	@Override
	public synchronized void newEvent(ReplaceConnectionEvent anEvent) {
		if (anEvent.isSent()) {
			newSentEvent(anEvent);
		} else {
			newReceivedEvent(anEvent);
		}
	}
	
	void newSentEvent (ReplaceConnectionEvent anEvent) {
		newReplaceEvent(sendSourceToDestinations, anEvent);
		if (anEvent.getOldDestination() != null)
		receivingDestinations.remove(anEvent.getOldDestination());		
	}
	
	void newReceivedEvent (ReplaceConnectionEvent anEvent) {
		newReplaceEvent(receiveSourceToDestinations, anEvent);
		if (anEvent.getOldDestination() != null)
		sendingDestinations.remove(anEvent.getOldDestination());
	}
	
	void newReplaceEvent (Hashcodetable<Object, Set> sourceToDestinations, ReplaceConnectionEvent anEvent) {
		Object oldDestination = anEvent.getOldDestination();
		Object newDestination = anEvent.getNewDestination();
		Object source = anEvent.getSource();
		Set sourceDestinationSet = sourceToDestinations.get(source);
		if (sourceDestinationSet == null) { // this is really a new connection
			newEvent(new AConnectionEvent(anEvent.getSource(), anEvent.getNewDestination(), anEvent.isSent()));
			return;
		} else if (oldDestination == null) {
			if (sourceDestinationSet.size() == 1) {
				sourceDestinationSet.clear();
				sourceDestinationSet.add(oldDestination);
				
			} else {
				Tracer.error("Missing old destination in :" + anEvent);
			}
		} else {
			sourceDestinationSet.remove(oldDestination);
			sourceDestinationSet.add(newDestination);
		}
//			
//			
//			
//			
//		if (sourceDestinationSet != null)  {
//			sourceDestinationSet.remove(oldDestination);
//			sourceDestinationSet.add(newDestination);
//		} else { // this is really a new connection
//			newEvent(new AConnectionEvent(anEvent.getSource(), anEvent.getNewDestination(), anEvent.isSent()));
//			return;
//		}
		if (anEvent.deleteOldDestination())
		sourceToDestinations.remove(oldDestination);		
	}


	
	void newSentEvent (ConnectionEvent anEvent) {
		// do not know if these null cases will actually occur
		if (anEvent.getSource() == null) {
			addSendingSource(anEvent.getDestination());
		} else if (anEvent.getDestination() == null) {
			setSendingDestination(anEvent.getSource());
		} else {
			newRegularSentEvent(anEvent);
		}
	}
	
	void processSendDestination (Object destination, Hashcodetable<Object, Set> derivedSourceDestinationMapping) {
		if (sendSourceToDestinations.containsKey(destination) || derivedSourceDestinationMapping.containsKey(destination))
			return;
		if (destination instanceof SendTrapperSetter) {
			SendTrapperSetter port = (SendTrapperSetter) destination;
			SendTrapper sendTrapper = port.getSendTrapper();
			if (sendTrapper != null) {
//				setSendingDestination(port);
//			} else {

				associateSourceDestination (derivedSourceDestinationMapping, port, sendTrapper);

//			associateSourceDestination (derivedSourceDestinationMapping, port, sendTrapper);
			}
		} 
//		else if (! (destination instanceof SendTrapper)) { //a terminating driver
//			setSendingDestination(destination);
//		}
	}
	
	void newRegularSentEvent (ConnectionEvent anEvent) {
//		Object destination = anEvent.getDestination();
//		if (destination instanceof SendTrapperSetter) {
//			SendTrapperSetter port = (SendTrapperSetter) destination;
//			SendTrapper sendTrapper = port.getSendTrapper();
//			if (sendTrapper == null) {
//				setReceivingDestination(port);
//			} else {
//			sendChain.put(port, sendTrapper);
//			}
//		} else if (! (destination instanceof SendTrapper)) { //a terminating driver
//			setReceivingDestination(destination);
//		}
		associateSourceDestination(sendSourceToDestinations, anEvent.getSource(), anEvent.getDestination());
//		sendSourceToDestinations.put(anEvent.getSource(), anEvent.getDestination());
	}
	
	void associateSourceDestination (Hashcodetable<Object, Set> sourceToDestinations, Object source, Object destination) {
		Tracer.info(this, "Associating source: " + source + " with destination: " + destination);
		Set destinations = sourceToDestinations.get(source);
		if (destinations == null) {
			destinations = new HashSet();
			sourceToDestinations.put(source, destinations);
		}
		destinations.add(destination);
	}
	
	void associatSourceDestination (Hashcodetable<Object, Set> sourceToDestinations, Object source, Set destinations) {
		Set currentDestinations = sourceToDestinations.get(source);
		if (currentDestinations == null) {
			sourceToDestinations.put(source, destinations);
		} else {
			currentDestinations.add(destinations);
		}
	}	
	
   
	
	void setSendingDestination (Object destination) {
		sendingDestinations.add(destination);
//		if (sendingDestination == null) {
//			sendingDestination = destination;
//		} else {
//			Tracer.error("Another sending destination:" + destination + ". Previous:" + sendingDestination);
//		}
	}
	void addSendingSource (Object aSource) {
		sendingSources.add(aSource);

	}
	void removeSendingSource (Object aSource) {
		sendingSources.remove(aSource);
	}
	void addReceivingDestination (Object destination) {
		receivingDestinations.add(destination);
//		if (receivingDestination == null) {
//			Tracer.info(this, "Setting receive destination to:" + destination);
//			receivingDestination = destination;
//		} else {
//			Tracer.error("Another receiving destination:" + destination + ". Previous:" + receivingDestination);
//		}
	}
	void addReceivingSource (Object aSource) {
		receivingSources.add(aSource);
	}
	void removeReceivingSource (Object aSource) {
		receivingSources.remove(aSource);

	}
	void processReceiveDestination(Object destination, 	Hashcodetable<Object, Set> derivedReceiveSourceDestinationMapping) {
		if (receiveSourceToDestinations.containsKey(destination) || derivedReceiveSourceDestinationMapping.containsKey(destination))
			return;
//		if (destination instanceof ReceiveTrapperSetter) {
//			ReceiveTrapperSetter port = (ReceiveTrapperSetter) destination;
//			ReceiveTrapper receiveTrapper = port.getReceiveTrapper();
//			if (receiveTrapper != null) { // a terminating driver
//
//			associateSourceDestination(derivedReceiveSourceDestinationMapping, port, receiveTrapper);
//			}
//		} 

		if (destination instanceof ReceiveRegistrarAndNotifier) {
			ReceiveRegistrarAndNotifier receiveRegistrarAndNotifier = (ReceiveRegistrarAndNotifier) destination;
			List<ReceiveListener> receiveListeners = receiveRegistrarAndNotifier.getReceiveListeners();
			for (Object receiveListener:receiveListeners) {
				associateSourceDestination(derivedReceiveSourceDestinationMapping, destination, receiveListener);
				processReceiveDestination(receiveListener, derivedReceiveSourceDestinationMapping);
			}
		} 
		
	}
	
	
	
	void newRegularReceivedEvent (ConnectionEvent anEvent) {
		Object destination = anEvent.getDestination();	
		associateSourceDestination(receiveSourceToDestinations, anEvent.getSource(), anEvent.getDestination());
	}
	
    void newReceivedEvent (ConnectionEvent anEvent) {
    	if (anEvent.getSource() == null) {
    		addReceivingSource(anEvent.getDestination());
		} else if (anEvent.getDestination() == null) {
			addReceivingDestination(anEvent.getSource());
		}	else {
			newRegularReceivedEvent(anEvent);
		}
	}
    
    void processReceiveDestinations() {
    	Hashcodetable<Object, Set> derivedReceiveSourceToDestination = new Hashcodetable();

    	while (true) {
//    		Collection keyValues = receiveSourceToDestinations.keyValues();
//    	for (Object key:receiveSourceToDestinations.keyValues()) {
        for (Object key:receiveSourceToDestinations.keyValues()) {

    		Set destinations = receiveSourceToDestinations.get(key);
    		if (destinations == null) {
    			Tracer.error("Null destination");
    		} else {
    		for (Object destination:destinations)
    			processReceiveDestination(destination, derivedReceiveSourceToDestination);
    		}
    	}  
    	if (derivedReceiveSourceToDestination.isEmpty())
    		return;
    	for (Object key:derivedReceiveSourceToDestination.keyValues()) {
    		associatSourceDestination(receiveSourceToDestinations, key, derivedReceiveSourceToDestination.get(key));
    	}
    	derivedReceiveSourceToDestination.clear();
    	}
    }
    void processSendDestinations() {
    	Hashcodetable<Object, Set> derivedSendSourceToDestination = new Hashcodetable();
    	while (true) {
    	for (Object key:sendSourceToDestinations.keyValues()) {
    		Set destinations = sendSourceToDestinations.get(key);
    		for (Object destination:destinations) {
    			processSendDestination(destination, derivedSendSourceToDestination);    	
    		}
    	}  
    	if (derivedSendSourceToDestination.isEmpty())
    		return;
    	for (Object key:derivedSendSourceToDestination.keyValues()) {
    		associatSourceDestination (sendSourceToDestinations, key, derivedSendSourceToDestination.get(key));
    	}
    	derivedSendSourceToDestination.clear();
    	}
    }
    
    
//    public Map<Object, Object> getSendChain() {
//    	return sendChain;
//    }
//    
//    public Map<Object, Object> getReceiveChain() {
//    	return receiveChain;
//    }
    synchronized void processDestinations() {
    	processReceiveDestinations();
//    	processSendDestinations();
    }
    public void print () {
    	System.out.println(sendSourceToDestinations);
    	System.out.println(receiveSourceToDestinations);
    }
    
   void add (Set allDestinations, Collection thisLevel) {
	   for (Object destination:thisLevel) {
		   if (destination instanceof Set)
			   add (allDestinations, (Set) destination);
		   else
			   allDestinations.add(destination);		   
	   }
   }
    void findSourceSenders() {
	   	Set destinations = new HashSet();
    	Collection values = sendSourceToDestinations.values();
    	add(destinations, values);
    	for (Object sendSource:sendSourceToDestinations.keyValues()) {
    		if (!destinations.contains(sendSource))
    			addSendingSource(sendSource);
    	}  
    	for (Object receiveOnlySource:receiveOnlySources) {
    		removeSendingSource(receiveOnlySource);
    	}
    	return;
    }
    
    void findSourceReceivers() {
	   	Set destinations = new HashSet();
    	Collection values = receiveSourceToDestinations.values();
    	add(destinations, values);
    	for (Object receiveSource:receiveSourceToDestinations.keyValues()) {
    		if (!destinations.contains(receiveSource))
    			addReceivingSource(receiveSource);
    	} 
    	for (Object sendOnlySource:sendOnlySources) {
    		removeReceivingSource(sendOnlySource);
    	}
    	return;
    }
    
    void findSources() {
    	findSourceSenders();
    	findSourceReceivers();
    }
    
    void printPath (Object currentLevel, int levelNo, Hashcodetable<Object, Set> sourceToDestinations, Set visited) {
    	boolean isRecursive = false;
    	if (currentLevel == null) 
    		return;  
    	if (visited.contains(currentLevel))
//    		return;
    		isRecursive = true;
    	visited.add(currentLevel);
    	if (currentLevel instanceof Set) {
    		Set currentSet = (Set) currentLevel;    		 
        	for (Object object:currentSet)  {
        		printPath (object, levelNo, sourceToDestinations, visited);
        	}
    	} else {
    		if (!(currentLevel instanceof AReceiveRegistrarAndNotifier)) {
    			
    		for (int i = 0; i < levelNo; i++) {
				System.out.print("  ");
			}
    		if (isRecursive) {
    			System.out.print ("(Replicated link)");
    		}
			System.out.println(currentLevel);
    		}
    		if (isRecursive) {
//    			System.out.print ("(Replicated link)");

    			return;
    		}
			Object value = sourceToDestinations.get(currentLevel);
			printPath(value, levelNo + 1, sourceToDestinations, visited);
    	}
    }
    
    public synchronized void printPaths() {
    	processDestinations();
    	findSourceSenders();
    	findSourceReceivers();
    	System.out.println("Print Send Paths");
    	printPath(sendingSources, 0, sendSourceToDestinations, new HashSet());
    	System.out.println("Print Receive Paths");
    	printPath(receivingSources, 0, receiveSourceToDestinations, new HashSet());
    }

	@Override
	public void receiveOnlySource(Object source) {
		receiveOnlySources.add(source);
	}
	@Override
	public void sendOnlySource(Object source) {
		sendOnlySources.add(source);		
	}    

}
