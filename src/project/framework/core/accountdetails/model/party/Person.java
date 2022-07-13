package project.framework.core.accountdetails.model.party;

import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.account.IEntry;

import java.time.LocalDate;

public class Person extends Party implements IPerson<IAccount, IEntry> {
    private LocalDate birthDate;

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}
