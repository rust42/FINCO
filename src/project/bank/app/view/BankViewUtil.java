package project.bank.app.view;

import project.bank.app.model.BankOrgCustomer;
import project.bank.app.model.BankPersonCustomer;
import project.bank.app.model.helper.OwnerType;

import java.time.LocalDate;

public class BankViewUtil {

    public static BankPersonCustomer createBankCustomerFromInput(String clientName, String street, String city, String state, String zip, String email, LocalDate dateOfBirth) {
        BankPersonCustomer bankCustomer = new BankPersonCustomer();
        bankCustomer.setName(clientName);
        bankCustomer.setStreet(street);
        bankCustomer.setCity(city);
        bankCustomer.setState(state);
        bankCustomer.setZip(zip);
        bankCustomer.setEmail(email);
        bankCustomer.setBirthDate(dateOfBirth);
//        OwnerType ownerType = switch (ownerType) {
//            case ""
//        }
        //bankCustomer.setOwnerType(ownerType);
        return bankCustomer;
    }
    public static BankOrgCustomer createBankOrgCustomerFromInput(String clientName, String street, String city, String state, String zip, String email, LocalDate dateOfBirth) {
        BankOrgCustomer bankOrgCustomer = new BankOrgCustomer();
        bankOrgCustomer.setName(clientName);
        bankOrgCustomer.setStreet(street);
        bankOrgCustomer.setCity(city);
        bankOrgCustomer.setState(state);
        bankOrgCustomer.setZip(zip);
        bankOrgCustomer.setEmail(email);
        //bankOrgCustomer.setBirthDate(dateOfBirth);

        return bankOrgCustomer;
    }
}
