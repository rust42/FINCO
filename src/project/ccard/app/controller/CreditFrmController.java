package project.ccard.app.controller;

import project.ccard.app.model.CCAccount;
import project.ccard.app.model.CreditAccTableModelResponse;
import project.ccard.app.model.TierLevel;
import project.framework.context.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.party.Person;
import project.framework.gui.defaults.modal.FormInputUtil;
import project.framework.gui.defaults.modal.PAFormInputModal;

import java.time.LocalDate;

public class CreditFrmController {

    AbstractAccountService<CCAccount> abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);

    public CreditAccTableModelResponse addCreditCardAccount(PAFormInputModal paFormInputModal, String ccNumber, String accType, String expDate) {

        TierLevel tierLevel;
        if (accType.equals(TierLevel.GOLD.toString())) {
            tierLevel = TierLevel.GOLD;
        } else if (accType.equals(TierLevel.SILVER.toString())) {
            tierLevel = TierLevel.SILVER;
        } else {
            tierLevel = TierLevel.BRONZE;
        }

        CCAccount creditAccount = new CCAccount();
        creditAccount.setUniqueId(ccNumber);
        creditAccount.setCcNumber(ccNumber);
        creditAccount.setExpDate(LocalDate.now()); // TODO expDate
        creditAccount.setTierLevel(tierLevel);

        Person person = new Person();
        FormInputUtil.mapAbstractAccFormInputModalToParty(paFormInputModal, person);
        creditAccount.setiParty(person);

        abstractAccountService.addAccount(creditAccount);

        CreditAccTableModelResponse ccTableModelResponse = new CreditAccTableModelResponse();
        ccTableModelResponse.setCcNumber(creditAccount.getCcNumber());
        ccTableModelResponse.setClientName(person.getName());
        ccTableModelResponse.setExpDate(creditAccount.getExpDate().toString());
        return ccTableModelResponse;
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

    public String getReport(String accnr) {
        return abstractAccountService.generateReport(accnr);
    }

}
