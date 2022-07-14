package project.framework.gui;

import javax.swing.*;

public interface IDisplayFrameGUI<T,U> {

    JFrame onCreateJFrame(String frameTitle);

    JFrame getCurrJFrame();

    void setJTableForJScrollPane(GenericJTableModel<T> genericJTableModel);

    void addDataToGenericJTableModel(T t);

    void bindGUIComponents();

    U getDefaultGUIComponents();

    void onExitApplication();

}
