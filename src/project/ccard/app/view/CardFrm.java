package project.ccard.app.view;

import project.ccard.app.controller.CreditFrmController;
import project.ccard.app.model.CreditAccTableModelResponse;
import project.ccard.app.model.CreditAccountRequestDTO;
import project.ccard.app.model.CreditTableResponseModelMapper;
import project.framework.core.accountdetails.model.party.Party;
import project.framework.gui.AbstractFrameworkGUI;
import project.framework.gui.helper.GenericJTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * A basic JFC based application.
 **/
public class CardFrm extends AbstractFrameworkGUI {
    /****
     * init variables in the object
     ****/
    String clientName, street, city, zip, state, accountType, amountDeposit, expdate, ccnumber;
    boolean newaccount;
    private GenericJTableModel<CreditAccTableModelResponse> genericJTableModel;

    private JScrollPane JScrollPane1;
    CardFrm thisframe;


    CreditFrmController creditFrmController = new CreditFrmController();

    public CardFrm() {
        super("Credit Card Processing Application.");
        genericJTableModel = new GenericJTableModel<>(new CreditTableResponseModelMapper());
        thisframe = this;

        JFrame jFrame = super.getCurrJFrame();
        JPanel1.setLayout(null);
        jFrame.getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();

        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 440, 160);
        JScrollPane1.getViewport().add(genericJTableModel.getjTable());
        genericJTableModel.getjTable().setBounds(0, 0, 420, 0);

        JButton_NewCCAccount.setText("Add Credit-card account");
        JPanel1.add(JButton_NewCCAccount);
        JButton_NewCCAccount.setBounds(24, 20, 192, 33);
        JButton_GenBill.setText("Generate Monthly bills");
        JButton_GenBill.setActionCommand("jbutton");
        JPanel1.add(JButton_GenBill);
        JButton_GenBill.setBounds(240, 20, 192, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 164, 96, 33);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 30);


        JButton_GenBill.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        jFrame.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_NewCCAccount.addActionListener(lSymAction);
        JButton_GenBill.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);

    }


    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_NewCCAccount = new javax.swing.JButton();
    javax.swing.JButton JButton_GenBill = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();


    void exitApplication() {
        try {
            this.getCurrJFrame().setVisible(false);    // hide the Frame
            this.getCurrJFrame().dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == CardFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_NewCCAccount)
                JButtonNewCCAC_actionPerformed(event);
            else if (object == JButton_GenBill)
                JButtonGenerateBill_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(thisframe);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();

        if (newaccount) {

            CreditAccountRequestDTO creditAccountRequestDTO = CreditViewUtil.createCreditAccountFromInput(ccnumber, clientName, street, city, state, zip, "", LocalDate.now());
            Party customer = CCViewUtil.createCCCustomerFromInput(creditAccountRequestDTO);

            CreditAccTableModelResponse creditAccTableModelResponse = this.creditFrmController.addCreditCardAccount(customer, creditAccountRequestDTO);
            genericJTableModel.addNewRow(creditAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }
    }

    void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event) {
        JDialogGenBill billFrm = new JDialogGenBill();
        billFrm.setBounds(450, 20, 400, 350);
        // TODO refactor generate Bills
//		billFrm.billstring = creditFrmController.generateMonthlyBills(ccnumber);
        billFrm.show();

    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(thisframe, name);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            String ccNumber = (String) genericJTableModel.getModel().getValueAt(selection, 1);
            Double deposit = Double.parseDouble(amountDeposit);
            CreditAccTableModelResponse creditAccTableModelResponse = creditFrmController.deposit(ccNumber, deposit);

            Double newamount = creditAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 4);
        }


    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(thisframe, name);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount

            String ccNumber = (String) genericJTableModel.getModel().getValueAt(selection, 1);
            Double deposit = Double.parseDouble(amountDeposit);
            CreditAccTableModelResponse creditAccTableModelResponse = creditFrmController.charge(ccNumber, deposit);

            Double newamount = creditAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 4);
            if (newamount < 0) {
                JOptionPane.showMessageDialog(JButton_Withdraw, " " + name + " Your balance is negative: $" + String.valueOf(newamount) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
            }
        }


    }

}
