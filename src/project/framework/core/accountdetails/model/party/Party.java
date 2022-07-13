package project.framework.core.accountdetails.model.party;

import project.framework.context.config.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;

public abstract class Party implements IParty<IAccount, IEntry> {

    private String name, street, city, state, zip, email;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public void onUpdate(IAccount iAccount, IEntry entry) {
        if (entry.getTxAmount() >= 400) {
            System.out.println("Send email triggered " + this.getEmail() + " transaction greater than 400");
            AbstractAccountService service = FactoryServiceRetriever.getService(AbstractAccountService.class);
            service.sendEmailToParty(iAccount, entry);
        }
    }

}

