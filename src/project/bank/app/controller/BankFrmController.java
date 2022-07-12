package project.bank.app.controller;

import project.bank.app.model.*;

import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Party;
import project.framework.core.accountdetails.model.party.Person;

import java.time.LocalDate;

public class BankFrmController {

    AbstractAccountService<BankAccount> abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);

    public BankAccTableModelResponse addBankPersonalAccount(Person personRequest, String uniqueAccId, AccountType accountType, OwnerType ownerType) throws Exception{

        BankAccount bankAccount = new BankAccount();
        bankAccount.setiParty(personRequest);

        bankAccount.setUniqueId(uniqueAccId);
        bankAccount.setBalance(0);
        bankAccount.setAccountType(accountType);
        abstractAccountService.addAccount(bankAccount);

        // preparing response for UI
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(bankAccount.getUniqueId());
        accTableModelResponse.setName(bankAccount.getParty().getName());
        accTableModelResponse.setCity(personRequest.getCity());

        String accType = accountType.equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType(ownerType.equals(OwnerType.COMPANY) ? "C": "P");

        return accTableModelResponse;
    }

    public BankAccTableModelResponse addBankCompanyAccount(Organization organizationRequest, String uniqueAccId, AccountType accountType, OwnerType ownerType) throws Exception{

        BankAccount bankAccount = new BankAccount();
        bankAccount.setiParty(organizationRequest);

        bankAccount.setUniqueId(uniqueAccId);
        bankAccount.setBalance(0);
        bankAccount.setAccountType(accountType);
        abstractAccountService.addAccount(bankAccount);

        // preparing response for UI
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(bankAccount.getUniqueId());
        accTableModelResponse.setName(bankAccount.getParty().getName());
        accTableModelResponse.setCity(organizationRequest.getCity());

        String accType = accountType.equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType(ownerType.equals(OwnerType.COMPANY) ? "C": "P");

        return accTableModelResponse;
    }

    public BankAccTableModelResponse deposit(String accNr, Double depositAmount) {

        // TODO deposit amount into the account with passed "accNr"
        // TODO get newBalance, prevBalance + depositAmount
        abstractAccountService.depositMoney(accNr, depositAmount);
//        Double newBalance = 100.0 + depositAmount + 100.0;

        // TODO after successful account deposit, populate AccTableModelResponse with new data

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;
    }

    public BankAccTableModelResponse withdraw(String accNr, Double withdrawAmount) {

        // TODO withdraw amount from the account with passed "accNr"
        // TODO get newBalance, prevBalance - withdrawAmount
        //Double newBalance = 100.0 - withdrawAmount;
        abstractAccountService.withdrawMoney(accNr, withdrawAmount);


        // TODO after successful account withdraw, populate AccTableModelResponse with new data
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;
    }

    /**
     * Retrieve all account from the bank and add collective interest rate to all of them.
     * */
    public BankAccTableModelResponse addInterest(Double interestRate){
        abstractAccountService.addInterest(interestRate);

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        //accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;

    }


}
