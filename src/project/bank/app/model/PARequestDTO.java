package project.bank.app.model;

public class PARequestDTO extends NewAbstractAccountDTO {
    private String birthDate;

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
