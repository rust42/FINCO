package project.framework.core.accountdetails.model.account;

import project.framework.support.Subject;

public interface IAccount extends Subject {
    public void getBalance();
    public void addEntry();
    public void getEntries();
}
