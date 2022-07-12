package project.ccard.app.view;

import project.ccard.app.model.CreditAccountRequestDTO;

import java.time.LocalDate;

public class CreditViewUtil {

    public static CreditAccountRequestDTO createCreditAccountFromInput(String ccNumber, String clientName, String street, String city, String state, String zip, String email, LocalDate expiryDate) {
        CreditAccountRequestDTO creditAccountRequestDTO = new CreditAccountRequestDTO();
        creditAccountRequestDTO.setCcNumber(ccNumber);
        creditAccountRequestDTO.setName(clientName);
        creditAccountRequestDTO.setStreet(street);
        creditAccountRequestDTO.setCity(city);
        creditAccountRequestDTO.setState(state);
        creditAccountRequestDTO.setZip(zip);
        creditAccountRequestDTO.setEmail(email);
        creditAccountRequestDTO.setExpiryDate(expiryDate);
        return creditAccountRequestDTO;
    }

}
