package project.bank.app.view;

import project.bank.app.controller.BankFrmController;
import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.BankCustomer;
import project.bank.app.model.BankTableResponseModelMapper;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.gui.AbstractFrameworkGUI;
import project.framework.gui.helper.GenericJTableModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

/**
 * A basic JFC based application.
 **/
public class BankFrm extends AbstractFrameworkGUI {
    /****
     * init variables in the object
     ****/
    String accountnr; String clientName; String street; String city; String zip; String state; String accountType; String clientType;
    String amountDeposit = "0.0";
    String interestRate = "0.0";
    boolean newaccount;
    private GenericJTableModel<BankAccTableModelResponse> genericJTableModel;
    private JScrollPane JScrollPane1;
    BankFrm myframe;


    private BankFrmController bankFrmController = new BankFrmController();

    public BankFrm() {
        super("Banking Application");
        super.getCurrJFrame().setVisible(true);
        genericJTableModel = new GenericJTableModel<>(new BankTableResponseModelMapper());
        myframe = this;
        JFrame jFrame = super.getCurrJFrame();

        JPanel1.setLayout(null);
        jFrame.getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 584, 324);
		/*
		/Add five buttons on the pane 
		/for Adding personal account, Adding company account
		/Deposit, Withdraw and Exit from the system
		*/
        JScrollPane1 = new JScrollPane();

        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 442, 160);
        JScrollPane1.getViewport().add(genericJTableModel.getjTable());
        genericJTableModel.getjTable().setBounds(0, 0, 420, 0);

        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);
        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 190, 33);
        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);
        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448, 20, 106, 33);
        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(468, 164, 96, 34);
        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 30);

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        jFrame.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_Addinterest.addActionListener(lSymAction);

    }

    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_PerAC = new javax.swing.JButton();
    javax.swing.JButton JButton_CompAC = new javax.swing.JButton();
    javax.swing.JButton JButton_Deposit = new javax.swing.JButton();
    javax.swing.JButton JButton_Withdraw = new javax.swing.JButton();
    javax.swing.JButton JButton_Addinterest = new javax.swing.JButton();
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
            if (object == BankFrm.this)
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
            else if (object == JButton_PerAC)
                JButtonPerAC_actionPerformed(event);
            else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_Addinterest)
                JButtonAddinterest_actionPerformed(event);

        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    void JButtonPerAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            BankCustomer bankCustomer = BankViewUtil.createBankCustomerFromInput(clientName, street, city, state, zip, "", LocalDate.now());
            AccountType accountTypeEnum = accountType.equals(AccountType.CHECKING) ? AccountType.CHECKING : AccountType.SAVING;
            BankAccTableModelResponse bankAccTableModelResponse = this.bankFrmController.addBankAccount(bankCustomer, accountnr, accountTypeEnum, OwnerType.PERSONAL);
            genericJTableModel.addNewRow(bankAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }


    }

    void JButtonCompAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myframe);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {

            BankCustomer bankCustomer = BankViewUtil.createBankCustomerFromInput(clientName, street, city, state, zip, "", LocalDate.now());
            // TODO fix logic for no of employees
            // bankCustomer.setNoOfEmployees(1);
            AccountType accountTypeEnum = accountType.equals(AccountType.CHECKING.toString()) ? AccountType.CHECKING : AccountType.SAVING;
            BankAccTableModelResponse bankAccTableModelResponse = this.bankFrmController.addBankAccount(bankCustomer, accountnr, accountTypeEnum, OwnerType.COMPANY);

            // add row to table
            genericJTableModel.addNewRow(bankAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            newaccount = false;
        }

    }

    void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            Double deposit = Double.parseDouble(amountDeposit);
            BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.deposit(accnr, deposit);
            Double newamount = bankAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 5);
            amountDeposit = "0";
        }


    }

    void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            Double deposit = Double.parseDouble(amountDeposit);
            BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.withdraw(accnr, deposit);
            Double newamount = bankAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 5);
            if (newamount < 0) {
                JOptionPane.showMessageDialog(JButton_Withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(newamount) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
            }
        }


    }

    void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
        JDialog_AddInterest interest = new JDialog_AddInterest(myframe, null);
        interest.setBounds(430, 15, 275, 140);
        interest.show();

        Double _interest = Double.parseDouble(interestRate);
        BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.addInterest(_interest);


//        JOptionPane.showMessageDialog(JButton_Addinterest,
//                "Add interest to all accounts", "Add interest to all accounts",
//                JOptionPane.WARNING_MESSAGE);

    }
}
