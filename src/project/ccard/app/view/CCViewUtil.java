package project.ccard.app.view;

import project.ccard.app.model.CCCustomer;
import project.ccard.app.model.CreditAccountRequestDTO;

public class CCViewUtil {
    public static CCCustomer createCCCustomerFromInput(CreditAccountRequestDTO input) {
        CCCustomer customer = new CCCustomer();
        customer.setName(input.getName());
        customer.setStreet(input.getStreet());
        customer.setCity(input.getCity());
        customer.setState(input.getState());
        customer.setZip(input.getZip());
        customer.setEmail(input.getEmail());
        return customer;
    }
}