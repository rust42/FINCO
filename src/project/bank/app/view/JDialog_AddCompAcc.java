package project.bank.app.view;


import project.framework.gui.defaults.dialogs.GenericJDialog_AddOrganizationAcc;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class JDialog_AddCompAcc extends GenericJDialog_AddOrganizationAcc {
    private JFrame parentframe;
    private String accountType;

    public JDialog_AddCompAcc(JFrame parent) {
        super(parent, "Add company account");
        parentframe = parent;

        JRadioButton_Chk.setText("Checkings");
        JRadioButton_Chk.setActionCommand("Checkings");
        getContentPane().add(JRadioButton_Chk);
        JRadioButton_Chk.setBounds(36, 12, 84, 24);

        JRadioButton_Sav.setText("Savings");
        JRadioButton_Sav.setActionCommand("Savings");
        getContentPane().add(JRadioButton_Sav);
        JRadioButton_Sav.setBounds(36, 36, 84, 24);

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