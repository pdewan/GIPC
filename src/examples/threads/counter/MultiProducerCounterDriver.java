package examples.threads.counter;

public class MultiProducerCounterDriver extends SingleThreadedCounterDriver {  
  public static void main (String[] args) {	
	  Thread aProducer1Thread = new Thread(numberProducer);
	  aProducer1Thread.setName("Producer 1");
	  Thread aConsumerThread = new Thread(numberConsumer);
	  aConsumerThread.setName("Consumer");
	  Thread aProducer2Thread = new Thread(numberProducer);
	  aProducer2Thread.setName("Producer 2");

	  aProducer1Thread.start();
	  aProducer2Thread.start();
	  aConsumerThread.start();
  }
}
