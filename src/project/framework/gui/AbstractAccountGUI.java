package project.framework.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public abstract class AbstractAccountGUI<T> implements IAccountGUI<T> {

    private TableModelRowMapper<T> tableModelRowMapper;

    public AbstractAccountGUI(TableModelRowMapper<T> tableModelRowMapper, String frameTitle) {
        this.tableModelRowMapper = tableModelRowMapper;
        this.frameTitle = frameTitle;
        initDefaults();
    }

    private JTable jTable;

    private DefaultTableModel model = new DefaultTableModel();

    private String frameTitle;

    private void initDefaults() {
        String[] columns = tableModelRowMapper.getColumns();
        for (String column : columns) {
            model.addColumn(column);
        }
    }

    @Override
    public String getFrameTitle() {
        return this.frameTitle;
    }

    @Override
    public DefaultTableModel getTableModel() {
        return model;
    }

    @Override
    public void addNewRow(T t) {
        Object[] objects = tableModelRowMapper.mapToRowData(t);
        getTableModel().addRow(objects);
    }

    @Override
    public JTable createJTable() {
        JTable jTable = new JTable(getTableModel());
        this.jTable = jTable;
        return jTable;
    }
}
