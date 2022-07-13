package project.bank.app.model;

import project.bank.app.model.helper.AccountType;
import project.framework.core.accountdetails.model.account.Account;

public class BankAccount extends Account {
    private AccountType accountType;

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

}
