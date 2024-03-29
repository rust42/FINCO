package project.bank.app.view;

import project.framework.gui.defaults.dialogs.GenericJDialog_AddPersonalAcc;

import javax.swing.*;

/**
 * A basic implementation of the JDialog class.
 **/

public class JDialog_AddPAcc extends GenericJDialog_AddPersonalAcc {
    private JFrame parentframe;
    private String accountType;

    public JDialog_AddPAcc(JFrame parent) {
        super(parent, "Add personal account");
        parentframe = parent;

        JRadioButton_Chk.setText("Checkings");
        JRadioButton_Chk.setActionCommand("Checkings");
        JRadioButton_Chk.setSelected(true);
        getContentPane().add(JRadioButton_Chk);
        JRadioButton_Chk.setBounds(36, 0, 84, 24);
        JRadioButton_Sav.setText("Savings");
        JRadioButton_Sav.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Sav);
        JRadioButton_Sav.setBounds(36, 24, 84, 24);


        JLabel6.setText("Birthdate");
        getContentPane().add(JLabel6);
        JLabel6.setForeground(java.awt.Color.black);
        JLabel6.setBounds(12, 204, 96, 24);

        //{{REGISTER_LISTENERS
        SymMouse aSymMouse = new SymMouse();
        JRadioButton_Chk.addMouseListener(aSymMouse);
        JRadioButton_Sav.addMouseListener(aSymMouse);

        //}}

        super.getJButton_OK().addActionListener(e -> {
            if (JRadioButton_Chk.isSelected())
                accountType = "CHECKING";
            else
                accountType = "SAVING";
            setNewaccount(true);
            dispose();
        });

    }


    //{{DECLARE_CONTROLS
    javax.swing.JRadioButton JRadioButton_Chk = new javax.swing.JRadioButton();
    javax.swing.JRadioButton JRadioButton_Sav = new javax.swing.JRadioButton();

    javax.swing.JLabel JLabel6 = new javax.swing.JLabel();
    //}}


    class SymMouse extends java.awt.event.MouseAdapter {
        public void mouseClicked(java.awt.event.MouseEvent event) {
            Object object = event.getSource();
            if (object == JRadioButton_Chk)
                JRadioButtonChk_mouseClicked(event);
            else if (object == JRadioButton_Sav)
                JRadioButtonSav_mouseClicked(event);
        }
    }

    void JRadioButtonChk_mouseClicked(java.awt.event.MouseEvent event) {
        //When Checking radio is clicked make this radio on
        //and make Saving account radio off
        JRadioButton_Chk.setSelected(true);
        JRadioButton_Sav.setSelected(false);
    }

    void JRadioButtonSav_mouseClicked(java.awt.event.MouseEvent event) {
        //When Saving radio is clicked make this radio on
        //and make Checking account radio off
        JRadioButton_Chk.setSelected(false);
        JRadioButton_Sav.setSelected(true);

    }

    public String getAccountType() {
        return accountType;
    }
}