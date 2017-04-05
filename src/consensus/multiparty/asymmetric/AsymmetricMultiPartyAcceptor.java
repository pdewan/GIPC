package consensus.multiparty.asymmetric;

import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;
import consensus.Preparer;

public interface AsymmetricMultiPartyAcceptor<StateType> extends Acceptor<StateType>, Learner<StateType> {

}
