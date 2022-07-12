package project.ccard.app.model;

import project.framework.core.accountdetails.model.account.Account;

import java.time.LocalDate;

public class CCAccount extends Account  {

    private String ccNumber;
    private LocalDate expDate;
    private double lastMonthBalance;
    private TierLevel tierLevel;

    public String getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    public LocalDate getExpDate() {
        return expDate;
    }

    public void setExpDate(LocalDate expDate) {
        this.expDate = expDate;
    }

    public double getLastMonthBalance() {
        return lastMonthBalance;
    }

    public void setLastMonthBalance(double lastMonthBalance) {
        this.lastMonthBalance = lastMonthBalance;
    }

    public TierLevel getTierLevel() {
        return tierLevel;
    }

    public void setTierLevel(TierLevel tierLevel) {
        this.tierLevel = tierLevel;
    }
}
