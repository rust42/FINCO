package project.framework.core.accountdetails.model.account;

import java.time.LocalDate;

public class Entry implements IEntry {
    private double txAmount;
    private LocalDate date;
    private String entryType;

    @Override
    public double getTxAmount() {
        return txAmount;
    }

    public void setTxAmount(double txAmount) {
        this.txAmount = txAmount;
    }

    @Override
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String getEntryType() {
        return entryType;
    }

    public void setEntryType(String entryType) {
        this.entryType = entryType;
    }
}
