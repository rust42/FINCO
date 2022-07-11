package project.bank.app.model;

import project.bank.app.model.helper.OwnerType;
import project.framework.core.accountdetails.model.party.Person;

public class BankCustomer extends Person {

    private OwnerType ownerType;

    public OwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(OwnerType ownerType) {
        this.ownerType = ownerType;
    }
}
