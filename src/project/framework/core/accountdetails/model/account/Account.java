package project.framework.core.accountdetails.model.account;

import project.framework.core.accountdetails.model.party.IParty;
import project.framework.support.Observer;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Account implements IAccount<Entry> {

    List<Observer> listOfObservers = new ArrayList<>();

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
        return this.balance;
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
    public List<Observer> getListOfObserversToNotify() {
        return this.listOfObservers;
    }

    @Override
    public void notifyObserver(Entry entry) {
        for (Observer observer : getListOfObserversToNotify()) {
            observer.onUpdate(this, entry);
        }
    }

}
