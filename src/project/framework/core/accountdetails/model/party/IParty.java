package project.framework.core.accountdetails.model.party;

import project.framework.support.Observer;

public interface IParty<A,E> extends Observer<A,E> {

    String getName();
    String getEmail();
}
