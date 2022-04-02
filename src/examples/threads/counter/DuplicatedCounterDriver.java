package examples.threads.counter;

public class DuplicatedCounterDriver extends SingleThreadedCounterDriver {
	  static DuplicatedCounter counter1 = new ADuplicatedCounter();
	  static DuplicatedCounter counter2 = new ADuplicatedCounter();
	  static NumberProducer numberProducer1 = new ADuplicatedNumberProducer(counter1, NUM_INCREMENTS);
	  static NumberProducer numberProducer2 = new ADuplicatedNumberProducer(counter2, NUM_INCREMENTS);

  public static void main (String[] args) {	
	  counter1.setPeer(counter2);
	  counter2.setPeer(counter1);
	  Thread aProducer1Thread = new Thread(numberProducer1);
	  aProducer1Thread.setName("Producer1");
	  Thread aProducer2Thread = new Thread(numberProducer2);
	  aProducer2Thread.setName("Producer2");	
	  aProducer1Thread.start();
	  aProducer2Thread.start();	
}
}
