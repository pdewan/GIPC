package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.datacomm.TrapperFactory;
import inputport.datacomm.simplex.object.ServerObjectTrapperSelector;
import inputport.rpc.duplex.DuplexClientSerializableCallTrapperSelector;
import replicatedserverport.datacomm.duplex.ADuplexMultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;

public class FixedlResponseReplicatedClientServerSupport {

	

		
		
		public static void setLocalResponseDuplexServerFactories() {
			//		ReceiveTrapperFactory<Object, Object>  aReceiveTrapperFactory = new ASingle();
					
					TrapperFactory<Object, Object>  aLocalReponseFactory = 	new AFixedResponseServerDuplexTrapperFactory();
					ServerObjectTrapperSelector.getTrapperSelector().setSendTrapperFactory(aLocalReponseFactory);
					ServerObjectTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(aLocalReponseFactory);
//					ServerObjectGroupTrapperSelector.getTrapperSelector().setGroupSendTrapperFactory(aSingleReponseFactory);
//					ServerObjectGroupTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(aSingleReponseFactory);		
				}

		
			public static void setLocalResponseClientFactories() {
				// the send call trapper will do that. So we do not have to worry about the server
				// also doing so
				// not sure why this is not being done, used in the factory below 
//				DuplexSentCallCompleterSelector.setDuplexSentCallCompleterFactory(new ASingleReponseDuplexSentCallCompleterFactory());
				TrapperFactory<Object, Object> aLocalResponseFactory = new AFixedResponseClientDuplexCallTrapperFactory();
		
				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
				setReceiveTrapperFactory(aLocalResponseFactory);
		//		GlobalState.getDuplexSerializableCallTrapper().
				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
				setSendTrapperFactory(aLocalResponseFactory);
				
				MultiToReplicatedTrapperFactory multiToReplicatedFactory = new ADuplexMultiToReplicatedTrapperFactory();
//				ReplicatedServerObjectTrapperSelector.
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setReceiveTrapperFactory(multiToReplicatedFactory);
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setUniToGroupSendTrapperFactory(multiToReplicatedFactory);


				
//				ReplicatedServerSessionPortSelector.
//				     setReplicatedServerSessionPortFactory(new AReplicatedServerSessionPortFactory());
			}

}
