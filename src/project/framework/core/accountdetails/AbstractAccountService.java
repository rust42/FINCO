package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.party.IParty;
import project.framework.core.accountdetails.storage.AbstractStorageService;

public abstract class AbstractAccountService {

    private IInterestCalculationStrategy iInterestCalculationStrategy;
    private IEmailPartyService iEmailPartyService;
    private AbstractStorageService abstractStorageService;
    private IReportingStrategy iReportingStrategy;

    public void addAccount(IAccount iAccount, IParty iParty){

    }

    public void withdrawMoney(){

    }

    public void depositMoney(){

    }

    public void addInterest(){

    }

    public void calculateInterest(){

    }

    public void addEntry(){

    }

    public void getCurrentBalance(){

    }

    public void sendEmailToParty(){

    }

    public void removeAccount(String accNumber, IParty party){

    }

    public void generateReport(){

    }


}
