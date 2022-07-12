package project.framework.core.accountdetails.model.party;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;

import java.time.LocalDate;

public class Person extends Party implements IPerson {
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public void onTransactionTrigger(IAccount iAccount, Entry entry){
        if(entry.getTxAmount() > 400) {
            System.out.println("Sending email to "+ this.getEmail() + " transaction greater than 400");
        }
    }
}
