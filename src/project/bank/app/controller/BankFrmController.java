package project.bank.app.controller;

import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.BankAccount;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Person;

public class BankFrmController {

    AbstractAccountService<BankAccount> abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);

    public BankAccTableModelResponse addBankPersonalAccount(Person personRequest, String uniqueAccId, AccountType accountType) throws Exception {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setiParty(personRequest);

        bankAccount.setUniqueId(uniqueAccId);
        bankAccount.setBalance(0);
        bankAccount.setAccountType(accountType);
        double interestRate = accountType.equals(AccountType.SAVING.toString()) ? 0.0325 : 0.01;
        bankAccount.setInterestRate(interestRate);
        abstractAccountService.addAccount(bankAccount);

        // preparing response for UI
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(bankAccount.getUniqueId());
        accTableModelResponse.setName(bankAccount.getParty().getName());
        accTableModelResponse.setCity(personRequest.getCity());

        String accType = accountType.equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType("P");

        return accTableModelResponse;
    }

    public BankAccTableModelResponse addBankCompanyAccount(Organization organizationRequest, String uniqueAccId, AccountType accountType) throws Exception {

        BankAccount bankAccount = new BankAccount();
        bankAccount.setiParty(organizationRequest);

        bankAccount.setUniqueId(uniqueAccId);
        bankAccount.setBalance(0);
        bankAccount.setAccountType(accountType);
        double interestRate = accountType.equals(AccountType.SAVING.toString()) ? 0.0325 : 0.01;
        bankAccount.setInterestRate(interestRate);
        abstractAccountService.addAccount(bankAccount);

        // preparing response for UI
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(bankAccount.getUniqueId());
        accTableModelResponse.setName(bankAccount.getParty().getName());
        accTableModelResponse.setCity(organizationRequest.getCity());

        String accType = accountType.equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType("C");

        return accTableModelResponse;
    }

    public BankAccTableModelResponse deposit(String accNr, Double depositAmount) {

        abstractAccountService.depositMoney(accNr, depositAmount);

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;
    }

    public BankAccTableModelResponse withdraw(String accNr, Double withdrawAmount) {

        abstractAccountService.withdrawMoney(accNr, withdrawAmount);

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;
    }

    /**
     * Retrieve all account from the bank and add collective interest rate to all of them.
     */
    public BankAccTableModelResponse addInterest(Double interestRate) {
        abstractAccountService.addInterest(interestRate);

        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        //accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(accNr));
        return accTableModelResponse;

    }


    public String getReport(String accNr) {
        String report = abstractAccountService.generateReport(accNr);
        return report;
    }
}
