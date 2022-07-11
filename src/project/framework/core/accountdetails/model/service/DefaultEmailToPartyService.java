package project.framework.core.accountdetails.model.service;

import project.framework.core.accountdetails.IEmailPartyService;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.party.IParty;

public class DefaultEmailToPartyService implements IEmailPartyService {
    @Override
    public void onSendEmailToPartyTriggered(IAccount iAccount) {
        // TODO send email logic
        IParty party = iAccount.getParty();
        System.out.println("TODO send email to party (email): " + party.getEmail());
    }
}
