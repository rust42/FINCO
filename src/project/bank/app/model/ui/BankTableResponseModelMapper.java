package project.bank.app.model.ui;

import project.framework.gui.TableModelRowMapper;

public class BankTableResponseModelMapper implements TableModelRowMapper<BankAccTableModelResponse> {
    @Override
    public Object[] mapToRowData(BankAccTableModelResponse bankAccTableModelResponse) {
        Object[] mappedRowData = new Object[getColumns().length];
        mappedRowData[0] = bankAccTableModelResponse.getAcctNr();
        mappedRowData[1] = bankAccTableModelResponse.getName();
        mappedRowData[2] = bankAccTableModelResponse.getCity();
        mappedRowData[3] = bankAccTableModelResponse.getOwnerType();
        mappedRowData[4] = bankAccTableModelResponse.getAccountType();
        mappedRowData[5] = bankAccTableModelResponse.getAmount();
        return mappedRowData;
    }

    @Override
    public String[] getColumns() {
        return new String[]{"AccNr", "Name", "City", "P/C", "Ch/S", "Amount"};
    }

    @Override
    public int getUniqueIdIndex() {
        return 0;
    }

    @Override
    public int getDefaultValueIndex() {
        return 5;
    }
}
