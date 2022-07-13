package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;

public interface IEmailPartyService {
    public void onSendEmail(IAccount iAccount, IEntry iEntry);
}
