package project.bank.app.model;

import project.framework.gui.AbstractAccountGUI;
import project.framework.gui.TableModelRowMapper;

public class BankGUI extends AbstractAccountGUI<BankAccTableModelResponse> {

    public BankGUI(TableModelRowMapper<BankAccTableModelResponse> tableModelRowMapper, String frameTitle) {
        super(tableModelRowMapper, frameTitle);
    }
}
