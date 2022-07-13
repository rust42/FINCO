package project.framework.core.accountdetails.model.party;

import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;

public class Organization extends Party implements IOrganization<IAccount, IEntry> {
    private int noOfEmployees;

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }


    @Override
    public void onUpdate(IAccount iAccount, IEntry entry) {
        AbstractAccountService service = FactoryServiceRetriever.getService(AbstractAccountService.class);
        service.sendEmailToParty(iAccount, entry);
    }
}
