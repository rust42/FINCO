package core.accountdetails.model.party;

import java.util.Date;

public class Person implements IPerson{
    private Date birthDate;

    public Person(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public void notifyObserver() {

    }
}
