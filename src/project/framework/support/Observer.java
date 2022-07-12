package project.framework.support;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;

public interface Observer
{
    public void onTransactionTrigger(IAccount iAccount, Entry entry);
}
