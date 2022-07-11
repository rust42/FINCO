package project.bank.app.controller;

import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.BankAccount;
import project.bank.app.model.BankCustomer;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;

public class BankFrmController {

    AbstractAccountService abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);

    public BankAccTableModelResponse addBankAccount(BankCustomer bankCustomer, String uniqueAccId, AccountType accountType, OwnerType ownerType) {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setUniqueId(uniqueAccId);
        bankAccount.setBalance(0);
        bankAccount.setiParty(bankCustomer);
        bankAccount.setAccountType(accountType);
        abstractAccountService.addAccount(bankAccount);

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(bankAccount.getUniqueId());
        accTableModelResponse.setName(bankAccount.getParty().getName());
        accTableModelResponse.setCity(bankCustomer.getCity());

        String accType = accountType.equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType("P");
        return accTableModelResponse;
    }

    public BankAccTableModelResponse deposit(String accNr, Double depositAmount) {

        // TODO deposit amount into the account with passed "accNr"
        // TODO get newBalance, prevBalance + depositAmount
        abstractAccountService.depositMoney(accNr, depositAmount);
//        Double newBalance = 100.0 + depositAmount + 100.0;

        // TODO after successful account deposit, populate AccTableModelResponse with new data
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(100);
        return accTableModelResponse;
    }

    public BankAccTableModelResponse withdraw(String accNr, Double withdrawAmount) {

        // TODO withdraw amount from the account with passed "accNr"
        // TODO get newBalance, prevBalance - withdrawAmount
        Double newBalance = 100.0 - withdrawAmount;


        // TODO after successful account withdraw, populate AccTableModelResponse with new data
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(newBalance);
        return accTableModelResponse;
    }

}
