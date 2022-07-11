package project.bank.app.controller;

import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.AccountType;
import project.bank.app.model.CARequestDTO;
import project.bank.app.model.PARequestDTO;

public class BankFrmController {

    public BankAccTableModelResponse addNewPersonalAccount(PARequestDTO paRequestDTO) {

        // TODO save PersonalAccount request data, paRequestDTO

        // TODO from saved data, populate AccTableModelResponse and return
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(paRequestDTO.getAccNr());
        accTableModelResponse.setName(paRequestDTO.getName());
        accTableModelResponse.setCity(paRequestDTO.getCity());

        String accType = paRequestDTO.getAccountType().equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType("P");
        return accTableModelResponse;
    }

    public BankAccTableModelResponse addNewCompanyAccount(CARequestDTO caRequestDTO) {

        // TODO save CompanyAccount request data, paRequestDTO

        // TODO from saved data, populate AccTableModelResponse and return
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAcctNr(caRequestDTO.getAccNr());
        accTableModelResponse.setName(caRequestDTO.getName());
        accTableModelResponse.setCity(caRequestDTO.getCity());

        String accType = caRequestDTO.getAccountType().equals(AccountType.CHECKING) ? "C" : "S";
        accTableModelResponse.setAccountType(accType);
        accTableModelResponse.setOwnerType("C");
        return accTableModelResponse;
    }

    public BankAccTableModelResponse deposit(String accNr, Double depositAmount) {

        // TODO deposit amount into the account with passed "accNr"
        // TODO get newBalance, prevBalance + depositAmount
        Double newBalance = 100.0 + depositAmount + 100.0;

        // TODO after successful account deposit, populate AccTableModelResponse with new data
        BankAccTableModelResponse accTableModelResponse = new BankAccTableModelResponse();
        accTableModelResponse.setAmount(newBalance);
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
