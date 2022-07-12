package project.framework.support;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.party.IParty;

import java.util.List;

public interface Subject {
    default void attach(IParty iParty) {
        getListOfObserversToNotify().add(iParty);
    }
//    public void detach(Observer observer);

    List<IParty> getListOfObserversToNotify();
    public void notifyPartyOnTxEntry(Entry entry);

}
