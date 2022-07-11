package project.framework.core.accountdetails.model.party;

public class Organization extends Party implements IOrganization {
    private int noOfEmployees;

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }

    @Override
    public void notifyObserver() {

    }
}
