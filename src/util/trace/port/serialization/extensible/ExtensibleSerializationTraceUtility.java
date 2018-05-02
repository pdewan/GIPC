package util.trace.port.serialization.extensible;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import util.trace.port.ReceiveListenerNotified;
import util.trace.port.ReceiveListenerRegistered;
import util.trace.port.buffer.ClientNameAssociatedWithPort;
import util.trace.port.buffer.ClientNameLookedUp;
import util.trace.port.buffer.ClientNameSendInitiated;
import util.trace.port.buffer.TrapperBufferReceived;
import util.trace.port.buffer.TrapperBufferSendInitiated;
import util.trace.port.nio.SocketChannelAccepted;
import util.trace.port.nio.SocketChannelConnected;
import util.trace.port.nio.SocketChannelRead;
import util.trace.port.nio.SocketChannelWritten;
import util.trace.port.objects.BufferDeserializationFinished;
import util.trace.port.objects.BufferDeserializationInitiated;
import util.trace.port.objects.ObjectSerializationFinished;
import util.trace.port.objects.ObjectSerializationInitiated;
import util.trace.port.objects.TrapperObjectReceived;
import util.trace.port.objects.TrapperObjectSendInitiated;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class ExtensibleSerializationTraceUtility implements SimpleCounterClient{
	/**
	 * Do not change this, I will keep updating it and you will run into conflicts
	 * Make a copy if you want this changed
	 */
	public static void setTracing(boolean newVal) {
		Tracer.showInfo(true);
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);	
		
		Tracer.setKeywordPrintStatus(BufferDeserializationFinished.class, newVal);
		Tracer.setKeywordPrintStatus(BufferDeserializationInitiated.class, newVal);
		Tracer.setKeywordPrintStatus(ExtensibleBufferDeserializationFinished.class, newVal);
		Tracer.setKeywordPrintStatus(ExtensibleBufferDeserializationInitiated.class, newVal);
		Tracer.setKeywordPrintStatus(ObjectSerializationFinished.class, newVal);
		Tracer.setKeywordPrintStatus(ObjectSerializationInitiated.class, newVal);
		Tracer.setKeywordPrintStatus(ExtensibleValueSerializationFinished.class, newVal);
		Tracer.setKeywordPrintStatus(ExtensibleValueSerializationInitiated.class, newVal);			

	}
	public static void setTracing() {
		setTracing(true);	

	}

}
