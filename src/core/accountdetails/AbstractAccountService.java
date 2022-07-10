package core.accountdetails;

import core.accountdetails.model.account.IAccount;
import core.accountdetails.model.party.IParty;
import core.accountdetails.storage.AbstractStorageService;

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
