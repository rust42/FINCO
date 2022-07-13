package project.framework.gui;

import project.framework.gui.defaults.DefaultGUIComponents;
import project.framework.gui.defaults.DefaultUIAccFormInput;

import javax.swing.*;

public interface IDisplayFrameGUI {

    <T> void setJTableForJScrollPane(GenericJTableModel<T> genericJTableModel);

    boolean isNewAccount();

    void setNewAccount(boolean isNewAccount);

    void  bindGUIComponents(DefaultGUIComponents defaultGUIComponents);

    DefaultUIAccFormInput getDefaultUIAccFormInput();

    void setDefaultUIAccFormInput(DefaultUIAccFormInput uiAccFormInput);

    void setDepositAmount(String amount);

    void setAmountWithdraw(String withdrawAmount);

    void setInterestAmount(String interestRate);

    JFrame createJFrame(String frameTitle);

    JFrame getCurrJFrame();

    void onExitApplication();

}
