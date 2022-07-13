package project.framework.gui;

import project.framework.core.accountdetails.model.account.Account;
import project.framework.gui.defaults.DefaultGUIComponents;
import project.framework.gui.defaults.DefaultUIAccFormInput;
import project.framework.gui.defaults.FincoAccountModelResponseMapper;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractDefaultFrameworkGUI implements IDisplayFrameGUI {

    /**
     * Default input states: defaultUIAccFormInput, amountDeposit, interestRate, newaccount
     */
    DefaultUIAccFormInput defaultUIAccFormInput;
    String amountDeposit = "0.0";
    String amountWithdraw = "0.0";
    String interestRate = "0.0";
    boolean newaccount;
    private JFrame jFrame;

    private JScrollPane defaultJScrollPane;

    private GenericJTableModel<Account> genericAccountJTableModel = new GenericJTableModel<>(new FincoAccountModelResponseMapper<>());
    ;
    private DefaultGUIComponents defaultGUIComponents;

    public AbstractDefaultFrameworkGUI(String frameTitle) {
        this.createJFrame(frameTitle);

        // setting some defaults
        defaultGUIComponents = new DefaultGUIComponents(this);
        this.bindGUIComponents(defaultGUIComponents);
    }

    @Override
    public <T> void setJTableForJScrollPane(GenericJTableModel<T> genericJTableModel) {
        this.defaultJScrollPane = new JScrollPane();
        defaultGUIComponents.setGenericJTableForJScrollPane(this.defaultJScrollPane, genericJTableModel);
    }

    @Override
    public void bindGUIComponents(DefaultGUIComponents defaultGUIComponents) {
        AbstractDefaultFrameworkGUI.SymWindow aSymWindow = new AbstractDefaultFrameworkGUI.SymWindow();
        jFrame.addWindowListener(aSymWindow);
        AbstractDefaultFrameworkGUI.SymAction lSymAction = new AbstractDefaultFrameworkGUI.SymAction();
        defaultGUIComponents.getJButton_Exit().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_PersonalAC().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_OrganizationAC().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_Deposit().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_Withdraw().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_Addinterest().addActionListener(lSymAction);
        defaultGUIComponents.getJButton_Report().addActionListener(lSymAction);

    }

    @Override
    public boolean isNewAccount() {
        return this.newaccount;
    }

    @Override
    public void setNewAccount(boolean isNewAccount) {
        this.newaccount = isNewAccount;
    }

    @Override
    public DefaultUIAccFormInput getDefaultUIAccFormInput() {
        return this.defaultUIAccFormInput;
    }

    @Override
    public void setDefaultUIAccFormInput(DefaultUIAccFormInput defaultUIAccFormInput) {
        this.defaultUIAccFormInput = defaultUIAccFormInput;
    }

    public String getAmountDeposit() {
        return amountDeposit;
    }

    public String getInterestRate() {
        return interestRate;
    }

    @Override
    public void setDepositAmount(String amount) {
        this.amountDeposit = amount;
    }

    public String getAmountWithdraw() {
        return amountWithdraw;
    }

    public void setAmountWithdraw(String amountWithdraw) {
        this.amountWithdraw = amountWithdraw;
    }

    @Override
    public void setInterestAmount(String interestRate) {
        this.interestRate = interestRate;
    }

    public DefaultGUIComponents getDefaultGUIComponents() {
        return defaultGUIComponents;
    }

    @Override
    public JFrame createJFrame(String frameTitle) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle(frameTitle);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        jFrame.setSize(675, 410);
        jFrame.setVisible(false);
        this.jFrame = jFrame;
        return jFrame;
    }

    public void onExitApplication() {
        try {
            this.jFrame.setVisible(false);    // hide the Frame
            this.jFrame.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    @Override
    public JFrame getCurrJFrame() {
        return this.jFrame;
    }


    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == AbstractDefaultFrameworkGUI.this)
                AbstractFrm_windowClosing(event);
        }
    }

    void AbstractFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        AbstractFrm_windowClosing_Interaction1(event);
    }

    void AbstractFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.onExitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == defaultGUIComponents.getJButton_Exit())
                JButtonExit_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_PersonalAC())
                JButtonPersonalAC_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_OrganizationAC())
                JButtonOrganizationAC_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_Report())
                JButtonGenerateReport_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_Deposit())
                JButtonDeposit_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_Withdraw())
                JButtonWithdraw_actionPerformed(event);
            else if (object == defaultGUIComponents.getJButton_Addinterest())
                JButtonAddinterest_actionPerformed(event);

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    protected void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    protected abstract void JButtonPersonalAC_actionPerformed(java.awt.event.ActionEvent event);

    protected abstract void JButtonOrganizationAC_actionPerformed(java.awt.event.ActionEvent event);

    protected abstract void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event);

    protected abstract void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event);

    protected abstract void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event);

    protected abstract void JButtonGenerateReport_actionPerformed(java.awt.event.ActionEvent event);

}
