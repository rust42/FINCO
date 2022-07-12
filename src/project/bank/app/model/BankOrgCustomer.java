package project.bank.app.model;

import project.bank.app.model.helper.OwnerType;
import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Person;

public class BankOrgCustomer extends Organization {

    //private IEmailPartyService iEmailPartyService = FactoryServiceRetriever.getService(IEmailPartyService.class);

    private OwnerType ownerType;

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }

    @Override
    public void onTransactionTrigger(IAccount iAccount, Entry entry) {
        if (this.getOwnerType().equals(OwnerType.COMPANY)) {
            System.out.println("Event occurred on : " + entry.getDate() + " Amount: " + entry.getTxAmount() + "Type: " + entry.getTransactionType());
        } else {
            if (iAccount.getBalance() < entry.getTxAmount() || entry.getTxAmount() >= 400) {
                System.out.println("send email to Personal email " + this.getEmail());
                System.out.println("Event occurred on" + entry.getDate() + " Amount: " + entry.getTxAmount() + "Type: " + entry.getTransactionType());

            }
        }

        //iEmailPartyService.onSendEmailToPartyTriggered(this, entry);
    }
}
