package project.framework.gui.helper;

import project.framework.gui.AbstractAccountGUI;
import project.framework.gui.TableModelRowMapper;

public class DefaultAccountGUI<T> extends AbstractAccountGUI<T> {

    public DefaultAccountGUI(TableModelRowMapper<T> tableModelRowMapper, String frameTitle) {
        super(tableModelRowMapper, frameTitle);
    }
}
