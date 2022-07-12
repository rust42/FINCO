package project.framework.core.accountdetails.model.account;

import java.time.LocalDate;

public class Entry implements IEntry {
    private double txAmount;
    private LocalDate date;
    private TransactionType transactionType;

    public Entry(double txAmount, LocalDate date, TransactionType type) {
        this.txAmount = txAmount;
        this.date = date;
        this.transactionType = type;
    }

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
    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
