package project.ccard.app.controller;

import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.BankAccount;
import project.bank.app.model.BankCustomer;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.ccard.app.model.CCCustomer;
import project.ccard.app.model.CreditAccTableModelResponse;
import project.ccard.app.model.CCAccount;
import project.ccard.app.model.CreditAccountRequestDTO;
import project.ccard.app.view.CCViewUtil;
import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.party.IParty;
import project.framework.core.accountdetails.model.party.Party;

public class CreditFrmController {

    AbstractAccountService<BankAccount> abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);

    public CreditAccTableModelResponse addCreditCardAccount(IParty customer, CreditAccountRequestDTO creditAccountRequestDTO) {
        CCAccount creditAccount = new CCAccount();
        creditAccount.setUniqueId(creditAccountRequestDTO.getCcNumber());
        creditAccount.setCcNumber(creditAccountRequestDTO.getCcNumber());
        creditAccount.setExpDate(creditAccountRequestDTO.getExpiryDate());
        creditAccount.setTierLevel(creditAccountRequestDTO.getTierLevel());
        creditAccount.setiParty(customer);

        abstractAccountService.addAccount(creditAccount);

        CreditAccTableModelResponse ccTableModelResponse = new CreditAccTableModelResponse();
        ccTableModelResponse.setCcNumber(creditAccountRequestDTO.getCcNumber());
        ccTableModelResponse.setClientName(customer.getName());
        ccTableModelResponse.setExpDate(creditAccountRequestDTO.getExpiryDate().toString());
        return ccTableModelResponse;
    }

    public String generateMonthlyBills(String ccNumber) {

        return null;
    }

    public CreditAccTableModelResponse deposit(String ccNumber, Double depositAmount) {
        abstractAccountService.depositMoney(ccNumber, depositAmount);
        CreditAccTableModelResponse accTableModelResponse = new CreditAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(ccNumber));
        return accTableModelResponse;
    }

    public CreditAccTableModelResponse charge(String ccNumber, Double chargeAmount) {
        abstractAccountService.withdrawMoney(ccNumber, chargeAmount);
        CreditAccTableModelResponse accTableModelResponse = new CreditAccTableModelResponse();
        accTableModelResponse.setAmount(abstractAccountService.getCurrentBalance(ccNumber));
        return accTableModelResponse;
    }
}
