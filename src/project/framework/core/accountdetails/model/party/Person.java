package project.framework.core.accountdetails.model.party;

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
    public void notifyObserver() {

    }
}
