package project.framework.support;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;

public interface Observer<A, E>
{
    public void onUpdate(A a, E e);
}
