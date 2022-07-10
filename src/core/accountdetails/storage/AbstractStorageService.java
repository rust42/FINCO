package core.accountdetails.storage;

import core.accountdetails.model.account.IAccount;

public interface AbstractStorageService {
    public void store(IAccount account);
    public void getAllAccounts();
    public void getAllParties();
}
