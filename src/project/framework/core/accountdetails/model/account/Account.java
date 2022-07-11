package project.framework.core.accountdetails.model.account;

import project.framework.core.accountdetails.model.party.IParty;
import project.framework.support.Observer;

import java.util.HashSet;
import java.util.Set;

public class Account implements IAccount {

    private String accNumber;
    private IParty iParty;
    private Set<Entry> entrySet = new HashSet<>();
    private double balance;
    private double interestRate;

    @Override
    public String getUniqueId() {
        return this.accNumber;
    }

    @Override
    public void setUniqueId(String uniqueAccId) {
        this.accNumber = uniqueAccId;
    }

    @Override
    public double getBalance() {
        return this.getBalance();
    }

    @Override
    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public IParty getParty() {
        return this.iParty;
    }

    @Override
    public void setiParty(IParty iParty) {
        this.iParty = iParty;
    }

    @Override
    public double getInterestRate() {
        return interestRate;
    }

    @Override
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    @Override
    public void addEntry(Entry entry) {
        this.entrySet.add(entry);
    }

    @Override
    public Set<Entry> getEntries() {
        return this.entrySet;
    }

    @Override
    public void attach(Observer observer) {

    }

    @Override
    public void detach(Observer observer) {

    }

    @Override
    public void notifyAllObservers() {

    }
}
