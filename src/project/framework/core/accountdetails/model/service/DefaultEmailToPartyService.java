package project.framework.core.accountdetails.model.service;

import project.framework.core.accountdetails.IEmailPartyService;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;

public class DefaultEmailToPartyService implements IEmailPartyService {
    @Override
    public void onSendEmail(IAccount account, IEntry entry) {
        System.out.println("Email sent to " + account.getParty().getEmail());
        System.out.println("Event occurred on: " + entry.getDate() + ", Amount: " + entry.getTxAmount() + ", Type: " + entry.getTransactionType());
    }
}
