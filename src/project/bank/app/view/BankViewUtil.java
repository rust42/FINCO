package project.bank.app.view;

import project.bank.app.model.BankCustomer;

import java.time.LocalDate;

public class BankViewUtil {

    public static BankCustomer createBankCustomerFromInput(String clientName, String street, String city, String state, String zip, String email, LocalDate dateOfBirth) {
        BankCustomer bankCustomer = new BankCustomer();
        bankCustomer.setName(clientName);
        bankCustomer.setStreet(street);
        bankCustomer.setCity(city);
        bankCustomer.setState(state);
        bankCustomer.setZip(zip);
        bankCustomer.setEmail(email);
        bankCustomer.setBirthDate(dateOfBirth);
        return bankCustomer;
    }
}
