package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.datacomm.TrapperFactory;
import inputport.datacomm.simplex.object.ServerObjectTrapperSelector;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;
import inputport.rpc.duplex.DuplexClientSerializableCallTrapperSelector;
import replicatedserverport.datacomm.duplex.ADuplexMultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;
import replicatedserverport.rpc.duplex.ReplicatedDuplexClientSerializableCallTrapperSelector;

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

				//				Looks like this was the old code
//		
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setReceiveTrapperFactory(aLocalResponseFactory);
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setSendTrapperFactory(aLocalResponseFactory);
				
				//				in the new code a diferent selector is used for replicated ports
				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
				setSendTrapperFactory(aLocalResponseFactory);	
				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
				setReceiveTrapperFactory(aLocalResponseFactory);
				
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
