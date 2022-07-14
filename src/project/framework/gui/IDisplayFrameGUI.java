package project.framework.gui;

import project.framework.gui.defaults.DefaultGUIComponents;

import javax.swing.*;

public interface IDisplayFrameGUI<T> {

    void setJTableForJScrollPane(GenericJTableModel<T> genericJTableModel);

    void addDataToGenericJTableModel(T t);

    void bindGUIComponents();

    DefaultGUIComponents getDefaultGUIComponents();

    JFrame onCreateJFrame(String frameTitle);

    JFrame getCurrJFrame();

    void onExitApplication();

}
