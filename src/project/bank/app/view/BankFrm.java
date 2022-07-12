package project.bank.app.view;

import project.bank.app.controller.BankFrmController;
import project.bank.app.model.BankAccTableModelResponse;
import project.bank.app.model.BankTableResponseModelMapper;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.helper.OwnerType;
import project.framework.core.accountdetails.model.party.Organization;
import project.framework.core.accountdetails.model.party.Person;
import project.framework.gui.AbstractDefaultFrameworkGUI;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.DefaultUIAccFormInput;
import project.framework.gui.defaults.dialogs.GenericJDialog;
import project.framework.gui.defaults.dialogs.GenericJDialog_AddInterest;
import project.framework.gui.defaults.dialogs.GenericJDialog_Deposit;
import project.framework.gui.defaults.dialogs.GenericJDialog_Withdraw;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.time.LocalDate;

/**
 * A basic JFC based application.
 **/
public class BankFrm extends AbstractDefaultFrameworkGUI {
    String accountType;
    private GenericJTableModel<BankAccTableModelResponse> genericJTableModel;
    private BankFrmController bankFrmController = new BankFrmController();

    public BankFrm() {
        super("Banking Application");
        genericJTableModel = new GenericJTableModel<>(new BankTableResponseModelMapper());
        super.setJTableForJScrollPane(genericJTableModel);
    }

    @Override
    public void JButtonPersonalAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object 
		 set the boundaries and show it 
		*/

        JDialog_AddPAcc pac = new JDialog_AddPAcc(this);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (super.isNewAccount()) {
            DefaultUIAccFormInput defaultUIAccFormInput = pac.getAccFormInput();
            AccountType accountTypeEnum = accountType.equals(AccountType.CHECKING.toString()) ? AccountType.CHECKING : AccountType.SAVING;

            String dateOfBirth = pac.getJTextField_BD().getText();
            LocalDate parseDOB = null;
            try {
                parseDOB = LocalDate.parse(dateOfBirth);
            } catch (Exception e) {
                parseDOB = LocalDate.of(2000, 01, 01); // default
            }
            Person personAccountRequest = BankViewUtil.createBankAccountRequestFromInput(defaultUIAccFormInput, parseDOB);

            BankAccTableModelResponse bankAccTableModelResponse = null;
            try {
                bankAccTableModelResponse = this.bankFrmController
                        .addBankPersonalAccount(personAccountRequest, defaultUIAccFormInput.getAccountnr(), accountTypeEnum, OwnerType.PERSONAL);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            genericJTableModel.addNewRow(bankAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            super.setNewAccount(false);
        }


    }

    @Override
    public void JButtonOrganizationAC_actionPerformed(java.awt.event.ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object 
		 set the boundaries and 
		 show it 
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (super.isNewAccount()) {

            DefaultUIAccFormInput defaultUIAccFormInput = pac.getAccFormInput();
            AccountType accountTypeEnum = accountType.equals(AccountType.CHECKING.toString()) ? AccountType.CHECKING : AccountType.SAVING;

            String noOfEmployee = pac.getJTextField_NoOfEmp().getText();
            Organization organizationRequest = BankViewUtil.createBankOrganizationAccountRequestFromInput(defaultUIAccFormInput, noOfEmployee);
            BankAccTableModelResponse bankAccTableModelResponse = null;
            try {
                bankAccTableModelResponse = this.bankFrmController
                        .addBankCompanyAccount(organizationRequest, defaultUIAccFormInput.getAccountnr(), accountTypeEnum, OwnerType.COMPANY);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            genericJTableModel.addNewRow(bankAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            super.setNewAccount(false);
        }

    }

    @Override
    public void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            GenericJDialog_Deposit dep = new GenericJDialog_Deposit(this, null, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            Double deposit = Double.parseDouble(super.getAmountDeposit());
            BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.deposit(accnr, deposit);
            Double newamount = bankAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 5);
            super.setDepositAmount("0");
        }


    }

    public void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            GenericJDialog_Withdraw wd = new GenericJDialog_Withdraw(this, null, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            Double deposit = Double.parseDouble(getAmountWithdraw());
            BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.withdraw(accnr, deposit);
            Double newamount = bankAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 5);
            if (newamount < 0) {
                JButton jButton_withdraw = super.getDefaultGUIComponents().getJButton_Withdraw();
                JOptionPane.showMessageDialog(jButton_withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(newamount) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
            }
        }


    }

    public void JButtonAddinterest_actionPerformed(java.awt.event.ActionEvent event) {
        GenericJDialog_AddInterest interest = new GenericJDialog_AddInterest(this, null, null);
        interest.setBounds(430, 15, 275, 140);
        interest.show();

        Double _interest = Double.parseDouble(getInterestRate());
        BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.addInterest(_interest);


//        JOptionPane.showMessageDialog(JButton_Addinterest,
//                "Add interest to all accounts", "Add interest to all accounts",
//                JOptionPane.WARNING_MESSAGE);

    }

    @Override
    protected void JButtonGenerateReport_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            // compute new amount
            String report = bankFrmController.getReport(accnr);
            GenericJDialog billFrm = new GenericJDialog(this, report);
            billFrm.setBounds(450, 20, 400, 350);
            billFrm.show();
        }
    }
}
