package inputport.rpc.group;

import inputport.InputPort;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.DuplexCallTrapperSharedState;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;

import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Set;

public class AGroupSerializableCallSendTrapper 
        extends AnAbstractGroupSendTrapper<Object, Object> {
	SendTrapper<Object, Object> duplexSendTrapper;
//	protected SerializableCall lastSerializableCall;
//	protected Set<String> lastClientNames;

//	InputPort inputPort;
//	ReturningUniImplicitRPCFunctionHandler waitingUniImplicitRPCFunctionHandler;
	DuplexCallTrapperSharedState sharedSenderReceiverState;
//	protected LocalRemoteReferenceTranslator localRemoteReferenceTranslator;
//
//	DuplexReturnerOfValueOfRemoteFunctionCall waitingUniImplicitRPCFunctionHandler;

	GroupRPCServerInputPort groupRPCInputPort;
	GroupReturnerOfValueOfRemoteFunctionCall groupReturnerOfValueOfRemoteFunctionCall;
	
	public AGroupSerializableCallSendTrapper(InputPort anInputPort, GroupNamingSender<Object>  aDestination) {
		super(aDestination);
		groupRPCInputPort = (GroupRPCServerInputPort) anInputPort;
		duplexSendTrapper = groupRPCInputPort.getSendTrapper();
		sharedSenderReceiverState = (DuplexCallTrapperSharedState) duplexSendTrapper.getSharedSenderReceiverState();
		groupReturnerOfValueOfRemoteFunctionCall = 
			createGroupReturnerOfRemoteFunctionCall(sharedSenderReceiverState.localRemoteReferenceTranslator);
		sharedSenderReceiverState.duplexSentCallCompleter = groupReturnerOfValueOfRemoteFunctionCall;

	}
	@Override
	public void send(Collection<String> clientNames, Object message) {
		SerializableCall serializableCall = (SerializableCall) message;		
//		lastSerializableCall = serializableCall;
//		lastClientNames = clientNames;
		sharedSenderReceiverState.localRemoteReferenceTranslator.transformSentRemoteReferences(
				serializableCall.getArgs(), serializableCall.getMethod().getParameterTypes());
		destination.send(clientNames, message);	
	}
	
//	@Override
//	public void send(String clientName, Object message) {
//		SerializableCall serializableCall = (SerializableCall) message;		
////		lastSerializableCall = serializableCall;
////		lastClientNames = clientNames;
//		sharedSenderReceiverState.localRemoteReferenceTranslator.transformSentRemoteReferences(serializableCall.getArgs());
//		destination.send(clientName, message);	
//	}
	

	protected GroupReturnerOfValueOfRemoteFunctionCall createGroupReturnerOfRemoteFunctionCall(LocalRemoteReferenceTranslator aRemoteHandler) {
//		return new AGroupReturnerOfValueOfRemoteFunctionCall(groupRPCInputPort, aRemoteHandler);
		return GroupReturnerOfValueOfRemoteFunctionCallSelector.createGroupReturnerOfValueOfRemoteFunctionCall(groupRPCInputPort, aRemoteHandler);
	}
//	@Override
//	public Object[] getSendReturnValue(Set<String> aClientNamesSet, Object aMessage) {
//		SerializableCall serializableCall = (SerializableCall) aMessage;
//		Method method = serializableCall.getSerializableMethod().getMethod();
////		if (!method.getReturnType().equals(Void.TYPE)) {
////			return waitingUniImplicitRPCFunctionHandler.handleFunction(lastSerializableCall);
////		}
//		if (!method.getReturnType().equals(Void.TYPE)) {
//			return groupReturnerOfValueOfRemoteFunctionCall.returnValueOfRemoteFunctionCall(aClientNamesSet,  serializableCall);
//		}		
//		return new Object[aClientNamesSet.size()];
//		
//	}
	@Override
	public Object getSendReturnValue(Set<String> aClientNamesSet, Object aMessage) {
		SerializableCall serializableCall = (SerializableCall) aMessage;
		Method method = serializableCall.getSerializableMethod().getMethod();
//		if (!method.getReturnType().equals(Void.TYPE)) {
//			return waitingUniImplicitRPCFunctionHandler.handleFunction(lastSerializableCall);
//		}
		if (!method.getReturnType().equals(Void.TYPE)) {
			return groupReturnerOfValueOfRemoteFunctionCall.returnValueOfRemoteFunctionCall(aClientNamesSet,  serializableCall);
		}		
		return null;
		
	}
	
	
	

}
