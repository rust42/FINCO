package project.framework.gui.defaults.modal;

import project.framework.core.accountdetails.model.account.Account;
import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Party;
import project.framework.core.accountdetails.model.party.Person;

public class FormInputUtil {

    public static Account createIAccountFromPAFormInputModal(PAFormInputModal paFormInputModal) {
        Account account = new Account();
        account.setUniqueId(paFormInputModal.getAccountnr());
        Person person = new Person();
        mapAbstractAccFormInputModalToParty(paFormInputModal, person);
        account.setiParty(person);
        return account;
    }



    public static Account createIAccountFromOrgFormInputModal(OrgAFormInputModal orgAFormInputModal) {
        Account account = new Account();
        account.setUniqueId(orgAFormInputModal.getAccountnr());
        Organization organization = new Organization();
        mapAbstractAccFormInputModalToParty(orgAFormInputModal, organization);
        organization.setNoOfEmployees(organization.getNoOfEmployees());
        account.setiParty(organization);
        return account;
    }

    public static void mapAbstractAccFormInputModalToParty(AbstractAccFormInputModal abstractAccFormInputModal, Party party) {
        party.setName(abstractAccFormInputModal.getClientName());
        party.setCity(abstractAccFormInputModal.getCity());
        party.setState(abstractAccFormInputModal.getState());
        party.setStreet(abstractAccFormInputModal.getStreet());
        party.setEmail(abstractAccFormInputModal.getEmail());
    }

}
