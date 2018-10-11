package util.trace.port.objects;


import examples.rmi.counter.simple.SimpleCounterClient;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.ReceiveListenerNotified;

public class ObjectTraceUtility implements SimpleCounterClient{
	/**
	 * Do not change this, I will keep updating it and you will run into conflicts
	 * Make a copy if you want this changed
	 */
	public static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);	
		Tracer.setKeywordPrintStatus(BufferDeserializationFinished.class, true);
		Tracer.setKeywordPrintStatus(BufferDeserializationInitiated.class, true);
		Tracer.setKeywordPrintStatus(ObjectSerializationFinished.class, true);
		Tracer.setKeywordPrintStatus(ObjectSerializationInitiated.class, true);
		Tracer.setKeywordPrintStatus(ObjectSendInitiated.class, true);
//		Tracer.setKeywordPrintStatus(ReceiveListenerRegistered.class, true);
		Tracer.setKeywordPrintStatus(ReceiveListenerNotified.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageDequeued.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageQueueCreated.class, true);
		Tracer.setKeywordPrintStatus(ReceivedMessageQueued.class, true);
		Tracer.setKeywordPrintStatus(SerializerPoolCreated.class, true);
		Tracer.setKeywordPrintStatus(SerializerReturnedToPool.class, true);
		Tracer.setKeywordPrintStatus(SerializerTakenFromPool.class, true);
		Tracer.setKeywordPrintStatus(TrapperObjectReceived.class, true);	

	}

}
