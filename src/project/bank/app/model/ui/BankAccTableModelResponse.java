package project.bank.app.model.ui;

public class BankAccTableModelResponse {

    private String acctNr;
    private String name;
    private String city;
    private String ownerType; // Personal/Company
    private String accountType; // Checking/Saving
    private double amount;

    public String getAcctNr() {
        return acctNr;
    }

    public void setAcctNr(String acctNr) {
        this.acctNr = acctNr;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
