package consensus.multiparty.asymmetric;

import consensus.Acceptor;
import consensus.Learner;
import consensus.Preparer;

public interface MultiPartyAcceptor<StateType> extends Acceptor<StateType>, Learner<StateType> {

}
