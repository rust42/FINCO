package project.framework.gui.defaults;

import project.framework.gui.GenericJTableModel;
import project.framework.gui.IDisplayFrameGUI;

import javax.swing.*;
import java.awt.*;

public class DefaultGUIComponents {

    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_PersonalAC = new javax.swing.JButton();
    javax.swing.JButton JButton_OrganizationAC = new javax.swing.JButton();
    javax.swing.JButton JButton_Report = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();

    private IDisplayFrameGUI iDisplayFrameGUI;

    public DefaultGUIComponents(IDisplayFrameGUI iDisplayFrameGUI) {
        this.iDisplayFrameGUI = iDisplayFrameGUI;
        initDefaultButtonBehaviour();
    }

    private void initDefaultButtonBehaviour() {

        JPanel1.setLayout(null);
        iDisplayFrameGUI.getCurrJFrame().getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 584, 324);
		/*
		/Add five buttons on the pane
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/

        JButton_PersonalAC.setText("Add personal account");
        JPanel1.add(JButton_PersonalAC);
        JButton_PersonalAC.setBounds(24, 20, 192, 33);

        JButton_OrganizationAC.setText("Add company account");
        JButton_OrganizationAC.setActionCommand("jbutton");
        JPanel1.add(JButton_OrganizationAC);
        JButton_OrganizationAC.setBounds(240, 20, 190, 33);

        JButton_Report.setText("Generate report");
        JButton_Report.setActionCommand("jbutton");
        JPanel1.add(JButton_Report);
        JButton_Report.setBounds(468, 20, 150, 30);

        JButton_Addinterest.setBounds(468, 70, 150, 30);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);

        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 150, 150, 30);

        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 190, 150, 30);


        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 270, 150, 30);

        JButton_PersonalAC.setActionCommand("jbutton");

    }

    public <T> void setGenericJTableForJScrollPane(JScrollPane jScrollPane, GenericJTableModel<T> genericJTableModel) {
        JPanel1.add(jScrollPane);
        jScrollPane.setBounds(12, 92, 442, 160);
        jScrollPane.getViewport().add(genericJTableModel.getjTable());
        genericJTableModel.getjTable().setBounds(0, 0, 420, 0);
    }


    public JPanel getJPanel1() {
        return JPanel1;
    }

    public void setJPanel1(JPanel JPanel1) {
        this.JPanel1 = JPanel1;
    }

    public JButton getJButton_PersonalAC() {
        return JButton_PersonalAC;
    }

    public void setJButton_PersonalAC(JButton JButton_PersonalAC) {
        this.JButton_PersonalAC = JButton_PersonalAC;
    }

    public JButton getJButton_OrganizationAC() {
        return JButton_OrganizationAC;
    }

    public void setJButton_OrganizationAC(JButton JButton_OrganizationAC) {
        this.JButton_OrganizationAC = JButton_OrganizationAC;
    }

    public JButton getJButton_Report() {
        return JButton_Report;
    }

    public void setJButton_Report(JButton JButton_Report) {
        this.JButton_Report = JButton_Report;
    }

    public JButton getJButton_Deposit() {
        return JButton_Deposit;
    }

    public void setJButton_Deposit(JButton JButton_Deposit) {
        this.JButton_Deposit = JButton_Deposit;
    }

    public JButton getJButton_Withdraw() {
        return JButton_Withdraw;
    }

    public void setJButton_Withdraw(JButton JButton_Withdraw) {
        this.JButton_Withdraw = JButton_Withdraw;
    }

    public JButton getJButton_Addinterest() {
        return JButton_Addinterest;
    }

    public void setJButton_Addinterest(JButton JButton_Addinterest) {
        this.JButton_Addinterest = JButton_Addinterest;
    }

    public JButton getJButton_Exit() {
        return JButton_Exit;
    }

    public void setJButton_Exit(JButton JButton_Exit) {
        this.JButton_Exit = JButton_Exit;
    }
}
