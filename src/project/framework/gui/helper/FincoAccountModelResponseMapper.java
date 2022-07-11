package project.framework.gui.helper;

import project.framework.core.accountdetails.model.account.Account;
import project.framework.gui.TableModelRowMapper;

public class FincoAccountModelResponseMapper implements TableModelRowMapper<Account> {
    @Override
    public Object[] mapToRowData(Account account) {
        Object[] mappedRowData = new Object[getColumns().length];
        mappedRowData[0] = account.getUniqueId();
        mappedRowData[1] = account.getParty().getName();
        mappedRowData[2] = account.getParty().getEmail();
        mappedRowData[3] = account.getBalance();
        return mappedRowData;
    }

    @Override
    public String[] getColumns() {
        return new String[]{"AccNr", "Name", "Email", "Amount"};
    }
}
