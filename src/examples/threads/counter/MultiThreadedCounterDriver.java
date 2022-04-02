package examples.threads.counter;

public class MultiThreadedCounterDriver extends SingleThreadedCounterDriver {
  
  public static void main (String[] args) {	
	  Thread aProducerThread = new Thread(numberProducer);
	  aProducerThread.setName("Producer");
	  Thread aConsumerThread = new Thread(numberConsumer);
	  aConsumerThread.setName("Consumer");
	  aProducerThread.start();
	  aConsumerThread.start();	  
  }
}
