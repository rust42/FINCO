package project.framework.core.accountdetails.model.account;

import project.framework.core.accountdetails.model.party.IParty;
import project.framework.support.Subject;

import java.util.Set;

public interface IAccount extends Subject {

    public String getUniqueId();

    public void setUniqueId(String uniqueAccId);

    public double getBalance();

    public void setBalance(double balance);

    public IParty getParty();

    void setiParty(IParty iParty);

    double getInterestRate();

    void setInterestRate(double interestRate);

    void addEntry(Entry entry);

    Set<Entry> getEntries();
}
