package core.accountdetails.model.account;

import support.Subject;

public interface IAccount extends Subject {
    public void getBalance();
    public void addEntry();
    public void getEntries();
}
