package project.framework.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public interface IAccountGUI<T> {

    String getFrameTitle();

    DefaultTableModel getTableModel();

    JTable createJTable();

    void addNewRow(T t);

}
