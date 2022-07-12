package project.framework.core.accountdetails.model.party;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;

public class Organization extends Party implements IOrganization {
    private int noOfEmployees;

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }


    @Override
    public void onTransactionTrigger(IAccount iAccount, Entry entry){
        System.out.println("Sending email to ");
        System.out.println(this.getEmail());
        System.out.println("Event occurred on" + entry.getDate() + " Amount: " + entry.getTxAmount() + "Type: "+ entry.getTransactionType());
    }
}
