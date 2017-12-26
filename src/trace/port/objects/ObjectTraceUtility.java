package trace.port.objects;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import trace.port.ReceiveListenerNotified;
import trace.port.ReceiveListenerRegistered;
import trace.port.buffer.ClientNameAssociatedWithPort;
import trace.port.buffer.ClientNameLookedUp;
import trace.port.buffer.ClientNameSendInitiated;
import trace.port.buffer.TrapperBufferReceived;
import trace.port.buffer.TrapperBufferSendInitiated;
import trace.port.nio.SocketChannelAccepted;
import trace.port.nio.SocketChannelConnectFinished;
import trace.port.nio.SocketChannelRead;
import trace.port.nio.SocketChannelWritten;
import trace.port.objects.TrapperObjectReceived;
import trace.port.objects.TrapperObjectSendInitiated;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

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
