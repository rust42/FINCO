package project.bank.app.model;

public class CARequestDTO extends NewAbstractAccountDTO {
    private int noOfEmployees;

    public int getNoOfEmployees() {
        return noOfEmployees;
    }

    public void setNoOfEmployees(int noOfEmployees) {
        this.noOfEmployees = noOfEmployees;
    }
}
