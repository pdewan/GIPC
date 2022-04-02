package examples.threads.counter;

public class SingleThreadedCounterDriver {
  public static final int NUM_INCREMENTS= 6;
  static Counter counter = CounterFactory.getCounter();
  static NumberProducer numberProducer = new ANumberProducer(counter, NUM_INCREMENTS);
  static NumberConsumer numberConsumer = new ANumberConsumer(counter, NUM_INCREMENTS);  
  public static void main (String[] args) {	  
	 numberProducer.produceNumbers();
	 numberConsumer.consumeNumbers();	  
  }
}
