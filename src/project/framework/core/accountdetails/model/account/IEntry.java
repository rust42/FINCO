package project.framework.core.accountdetails.model.account;

import java.time.LocalDate;

public interface IEntry {

    public double getTxAmount();

    public LocalDate getDate();

    public String getEntryType();
}
