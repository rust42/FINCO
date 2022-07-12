package project.ccard.app.model;

import project.framework.gui.TableModelRowMapper;

public class CreditTableResponseModelMapper implements TableModelRowMapper<CreditAccTableModelResponse> {
    @Override
    public Object[] mapToRowData(CreditAccTableModelResponse creditAccTableModelResponse) {
        Object[] mappedRowData = new Object[getColumns().length];
        mappedRowData[0] = creditAccTableModelResponse.getCcNumber();
        mappedRowData[1] = creditAccTableModelResponse.getClientName();
        mappedRowData[2] = creditAccTableModelResponse.getAccountType();
        mappedRowData[3] = creditAccTableModelResponse.getExpDate();
        mappedRowData[4] = creditAccTableModelResponse.getAmount();
        return mappedRowData;
    }

    @Override
    public String[] getColumns() {
        return new String[]{"CCNumber", "Name", "Account type", "Expiry Date", "Amount"};
    }

}
