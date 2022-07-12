package project.bank.app.model;

import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.core.accountdetails.model.account.Account;

public class BankAccount extends Account {
    private AccountType accountType;
//    private OwnerType ownerType;


    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

//    public OwnerType getOwnerType() {
//        return ownerType;
//    }
//
//    public void setOwnerType(OwnerType ownerType) {
//        this.ownerType = ownerType;
//    }
}
