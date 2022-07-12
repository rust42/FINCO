package project.ccard.app.view;

import project.ccard.app.model.CreditAccountRequestDTO;
import project.ccard.app.model.TierLevel;

import java.time.LocalDate;

public class CreditViewUtil {

    public static CreditAccountRequestDTO createCreditAccountFromInput(String ccNumber, String clientName, String street, String city, String state, String zip, String email, LocalDate expiryDate, String accountType) {
        CreditAccountRequestDTO creditAccountRequestDTO = new CreditAccountRequestDTO();
        creditAccountRequestDTO.setCcNumber(ccNumber);
        creditAccountRequestDTO.setName(clientName);
        creditAccountRequestDTO.setStreet(street);
        creditAccountRequestDTO.setCity(city);
        creditAccountRequestDTO.setState(state);
        creditAccountRequestDTO.setZip(zip);
        creditAccountRequestDTO.setEmail(email);
        creditAccountRequestDTO.setExpiryDate(expiryDate);

        creditAccountRequestDTO.setTierLevel(switch (accountType) {
            case "Silver" -> TierLevel.SILVER;
            case "Gold" -> TierLevel.GOLD;
            case "Bronze" -> TierLevel.BRONZE;
            default -> TierLevel.BRONZE;
        });
        return creditAccountRequestDTO;
    }
}
