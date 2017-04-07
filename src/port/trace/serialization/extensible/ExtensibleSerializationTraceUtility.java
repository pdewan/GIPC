package port.trace.serialization.extensible;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.ReceiveListenerNotified;
import port.trace.ReceiveListenerRegistered;
import port.trace.buffer.TrapperBufferReceived;
import port.trace.buffer.TrapperBufferSendInitiated;
import port.trace.buffer.ClientNameAssociatedWithPort;
import port.trace.buffer.ClientNameLookedUp;
import port.trace.buffer.ClientNameSendInitiated;
import port.trace.nio.SocketChannelAccepted;
import port.trace.nio.SocketChannelConnectFinished;
import port.trace.nio.SocketChannelRead;
import port.trace.nio.SocketChannelWritten;
import port.trace.objects.BufferDeserializationFinished;
import port.trace.objects.BufferDeserializationInitiated;
import port.trace.objects.ObjectSerializationFinished;
import port.trace.objects.ObjectSerializationInitiated;
import port.trace.objects.TrapperObjectReceived;
import port.trace.objects.TrapperObjectSendInitiated;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ExtensibleSerializationTraceUtility implements SimpleCounterClient{
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
		Tracer.setKeywordPrintStatus(ExtensibleBufferDeserializationFinished.class, true);
		Tracer.setKeywordPrintStatus(ExtensibleBufferDeserializationInitiated.class, true);
		Tracer.setKeywordPrintStatus(ObjectSerializationFinished.class, true);
		Tracer.setKeywordPrintStatus(ObjectSerializationInitiated.class, true);
		Tracer.setKeywordPrintStatus(ExtensibleValueSerializationFinished.class, true);
		Tracer.setKeywordPrintStatus(ExtensibleValueSerializationInitiated.class, true);			

	}

}
