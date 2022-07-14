package project.framework.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GenericJTableModel<T> {

    private TableModelRowMapper<T> tableModelRowMapper;
    private DefaultTableModel model;
    private JTable jTable;

    public GenericJTableModel(TableModelRowMapper<T> tableModelRowMapper) {
        this.tableModelRowMapper = tableModelRowMapper;
        initDefaults();
    }

    private void initDefaults() {
        model = new DefaultTableModel();
        String[] columns = tableModelRowMapper.getColumns();
        for (String column : columns) {
            model.addColumn(column);
        }
        this.jTable = new JTable(model);
    }

    public void addNewRow(T t) {
        Object[] objects = tableModelRowMapper.mapToRowData(t);
        model.addRow(objects);
    }

    public JTable getjTable() {
        return jTable;
    }

    public DefaultTableModel getModel() {
        return model;
    }

    public TableModelRowMapper<T> getTableModelRowMapper() {
        return tableModelRowMapper;
    }
}
