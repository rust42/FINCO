package core.accountdetails.model.party;

public abstract class Party implements IParty{
    private String name,street,city,state,zip,email;

    @Override
    public void notifyObserver() {

    }
}

