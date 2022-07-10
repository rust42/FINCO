package core.accountdetails.model.party;

public class Organization implements IOrganization{
    private int noOfEmployees;

    public Organization(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    @Override
    public void notifyObserver() {

    }
}
