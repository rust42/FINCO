package project.bank.app.view;

import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Person;
import project.framework.gui.defaults.DefaultUIAccFormInput;

import java.time.LocalDate;

public class BankViewUtil {

    public static Person createBankAccountRequestFromInput(DefaultUIAccFormInput defaultUIAccFormInput, LocalDate dateOfBirth) {
        Person person = new Person();
        person.setName(defaultUIAccFormInput.getClientName());
        person.setStreet(defaultUIAccFormInput.getStreet());
        person.setCity(defaultUIAccFormInput.getCity());
        person.setState(defaultUIAccFormInput.getState());
        person.setZip(defaultUIAccFormInput.getZip());
        person.setEmail(defaultUIAccFormInput.getEmail());
        person.setBirthDate(dateOfBirth);
        return person;
    }

    public static Organization createBankOrganizationAccountRequestFromInput(DefaultUIAccFormInput defaultUIAccFormInput, String noOfEmployees) {
        Organization organization = new Organization();
        organization.setName(defaultUIAccFormInput.getClientName());
        organization.setStreet(defaultUIAccFormInput.getStreet());
        organization.setCity(defaultUIAccFormInput.getCity());
        organization.setState(defaultUIAccFormInput.getState());
        organization.setZip(defaultUIAccFormInput.getZip());
        organization.setEmail(defaultUIAccFormInput.getEmail());
        Integer noOfEmp = 0;
        try {
            noOfEmp = Integer.parseInt(noOfEmployees);
        } catch (Exception e) {
        }
        organization.setNoOfEmployees(noOfEmp);
        return organization;
    }
}
