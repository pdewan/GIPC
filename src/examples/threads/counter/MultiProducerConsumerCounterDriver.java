package examples.threads.counter;

public class MultiProducerConsumerCounterDriver extends SingleThreadedCounterDriver {  
  public static void main (String[] args) {	
	  System.out.println (Runtime.getRuntime().availableProcessors());
	  Thread aProducer1Thread = new Thread(numberProducer);
	  aProducer1Thread.setName("Producer 1");
	  Thread aConsumer1Thread = new Thread(numberConsumer);
	  aConsumer1Thread.setName("Consumer 1");
	  Thread aProducer2Thread = new Thread(numberProducer);
	  aProducer2Thread.setName("Producer 2");
	  Thread aConsumer2Thread = new Thread(numberConsumer);
	  aConsumer2Thread.setName("Consumer 2");

	  aProducer1Thread.start();
	  aProducer2Thread.start();
	  aConsumer1Thread.start();	
	  aConsumer2Thread.start();	  

  }
}
