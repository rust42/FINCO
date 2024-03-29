package project.ccard.app.view;


import project.framework.gui.defaults.dialogs.GenericJDialog_AddPersonalAcc;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JDialog_AddCCAccount extends GenericJDialog_AddPersonalAcc {
    private JFrame parentframe;

    private String ccnumber;
    private String expdate;
    private String accountType;

    public JDialog_AddCCAccount(JFrame parent) {
        super(parent, "Add credit card account");
        parentframe = parent;

        // This code is automatically generated by Visual Cafe when you add
        // components to the visual environment. It instantiates and initializes
        // the components. To modify the code, only use code syntax that matches
        // what Visual Cafe can generate, or Visual Cafe may be unable to back
        // parses your Java file into its visual envirenment.
        //{{ INIT_CONTROLS

        getJTextField_ACNR().setVisible(false);
        getJLabel8().setVisible(false);

        JRadioButton_Gold.setText("Gold");
        JRadioButton_Gold.setActionCommand("Checkings");
        JRadioButton_Gold.setSelected(true);  // Select Gold by default
        getContentPane().add(JRadioButton_Gold);
        JRadioButton_Gold.setBounds(36, 12, 84, 24);

        JRadioButton_Silver.setText("Silver");
        JRadioButton_Silver.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Silver);
        JRadioButton_Silver.setBounds(36, 36, 84, 24);

        JRadioButton_Bronze.setText("Bronze");
        JRadioButton_Bronze.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Bronze);
        JRadioButton_Bronze.setBounds(36, 60, 84, 24);

        JLabel6.setText("CC number");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(java.awt.Color.black);
        JLabel6.setBounds(12, 252, 96, 24);
        Label7.setText("Exp. Date");
        getContentPane().add(Label7);
        Label7.setForeground(java.awt.Color.black);
        Label7.setBounds(12, 276, 72, 24);

        getContentPane().add(JTextField_CCNR);
        JTextField_CCNR.setBounds(84, 252, 156, 20);
        getContentPane().add(JTextField_ExpDate);
        JTextField_ExpDate.setBounds(84, 276, 156, 20);

        // customizing ok and cancel bounds
        getJButton_OK().setBounds(48, 300, 84, 24);
        getJButton_Cancel().setBounds(156, 300, 84, 24);

        getJButton_OK().addActionListener(e -> {
            JButtonOK_actionPerformed(e);
        });

        //{{REGISTER_LISTENERS
        SymMouse aSymMouse = new SymMouse();
        JRadioButton_Gold.addMouseListener(aSymMouse);
        JRadioButton_Silver.addMouseListener(aSymMouse);
        JRadioButton_Bronze.addMouseListener(aSymMouse);
        //}}
    }


    //{{DECLARE_CONTROLS
    javax.swing.JRadioButton JRadioButton_Gold = new javax.swing.JRadioButton();
    javax.swing.JRadioButton JRadioButton_Silver = new javax.swing.JRadioButton();
    javax.swing.JLabel JLabel6 = new javax.swing.JLabel();
    javax.swing.JLabel Label7 = new javax.swing.JLabel();
    javax.swing.JTextField JTextField_CCNR = new javax.swing.JTextField();
    javax.swing.JTextField JTextField_ExpDate = new javax.swing.JTextField();
    javax.swing.JRadioButton JRadioButton_Bronze = new javax.swing.JRadioButton();
    //}}


    class SymMouse extends java.awt.event.MouseAdapter {
        public void mouseClicked(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == JRadioButton_Gold)
                JRadioButtonChk_mouseClicked(event);
            else if (object == JRadioButton_Silver)
                JRadioButtonSav_mouseClicked(event);
            else if (object == JRadioButton_Bronze)
                JRadioButtonBronze_mouseClicked(event);


        }
    }

    void JRadioButtonChk_mouseClicked(java.awt.event.MouseEvent event) {
        JRadioButton_Gold.setSelected(true);
        JRadioButton_Silver.setSelected(false);
        JRadioButton_Bronze.setSelected(false);
    }

    void JRadioButtonSav_mouseClicked(java.awt.event.MouseEvent event) {
        JRadioButton_Gold.setSelected(false);
        JRadioButton_Silver.setSelected(true);
        JRadioButton_Bronze.setSelected(false);

    }

    void JRadioButtonBronze_mouseClicked(java.awt.event.MouseEvent event) {
        JRadioButton_Gold.setSelected(false);
        JRadioButton_Silver.setSelected(false);
        JRadioButton_Bronze.setSelected(true);

    }

    public void JButtonOK_actionPerformed(ActionEvent event) {
        ccnumber = JTextField_CCNR.getText();
        expdate = JTextField_ExpDate.getText();
        if (JRadioButton_Gold.isSelected())
            accountType = "Gold";
        else {
            if (JRadioButton_Silver.isSelected())
                accountType = "Silver";
            else
                accountType = "Bronze";
        }

        setNewaccount(true);
        dispose();
    }

    public String getCcnumber() {
        return ccnumber;
    }

    public String getExpdate() {
        return expdate;
    }

    public String getAccountType() {
        return accountType;
    }
}