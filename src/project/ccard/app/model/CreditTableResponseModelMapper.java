package project.ccard.app.model;

import project.framework.gui.TableModelRowMapper;

public class CreditTableResponseModelMapper implements TableModelRowMapper<CreditAccTableModelResponse> {
    @Override
    public Object[] mapToRowData(CreditAccTableModelResponse creditAccTableModelResponse) {
        Object[] mappedRowData = new Object[getColumns().length];
        mappedRowData[0] = creditAccTableModelResponse.getClientName();
        mappedRowData[1] = creditAccTableModelResponse.getCcNumber();
        mappedRowData[2] = creditAccTableModelResponse.getExpDate();
        mappedRowData[3] = creditAccTableModelResponse.getAccountType();
        mappedRowData[4] = creditAccTableModelResponse.getAmount();
        return mappedRowData;
    }

    @Override
    public String[] getColumns() {
        return new String[]{"Name", "CC Number",  "Exp Date", "Account type", "Amount"};
    }

    @Override
    public int getUniqueIdIndex() {
        return 1;
    }

    @Override
    public int getDefaultValueIndex() {
        return 4;
    }

}
