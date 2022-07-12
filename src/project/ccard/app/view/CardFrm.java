package project.ccard.app.view;

import project.ccard.app.controller.CreditFrmController;
import project.ccard.app.model.CreditAccTableModelResponse;
import project.ccard.app.model.CreditAccountRequestDTO;
import project.ccard.app.model.CreditTableResponseModelMapper;
import project.framework.core.accountdetails.model.party.Party;
import project.framework.gui.AbstractDefaultFrameworkGUI;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.DefaultGUIComponents;
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
public class CardFrm extends AbstractDefaultFrameworkGUI {

    String accountType, expdate, ccnumber;
    private GenericJTableModel<CreditAccTableModelResponse> genericJTableModel;
    CreditFrmController creditFrmController = new CreditFrmController();

    public CardFrm() {
        super("Credit Card Processing Application.");
        genericJTableModel = new GenericJTableModel<>(new CreditTableResponseModelMapper());
        super.setJTableForJScrollPane(genericJTableModel);

        // customize buttons and their functionality
        DefaultGUIComponents defaultGUIComponents = super.getDefaultGUIComponents();
        JButton jButton_personalAC = defaultGUIComponents.getJButton_PersonalAC();
        jButton_personalAC.setText("Add Credit-card account");

        defaultGUIComponents.getJButton_Report().setText("Generate Monthly Report");
        defaultGUIComponents.getJButton_Deposit().setText("Deposit");
        defaultGUIComponents.getJButton_Withdraw().setText("Charge");

        defaultGUIComponents.getJButton_OrganizationAC().setVisible(false);
        defaultGUIComponents.getJButton_Addinterest().setVisible(false);
    }


    @Override
    protected void JButtonPersonalAC_actionPerformed(ActionEvent event) {
/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(this);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();

        if (isNewAccount()) {


            DefaultUIAccFormInput defaultUIAccFormInput = super.getDefaultUIAccFormInput();
            LocalDate expiryDate = LocalDate.now(); //TODO use expDate
            CreditAccountRequestDTO creditAccountRequestDTO = CreditViewUtil
                    .createCreditAccountFromInput(ccnumber, defaultUIAccFormInput.getClientName(), defaultUIAccFormInput.getStreet(), defaultUIAccFormInput.getCity(), defaultUIAccFormInput.getState(), defaultUIAccFormInput.getZip(), defaultUIAccFormInput.getEmail(), expiryDate, accountType);
            Party customer = CCViewUtil.createCCCustomerFromInput(creditAccountRequestDTO);

            CreditAccTableModelResponse creditAccTableModelResponse = this.creditFrmController.addCreditCardAccount(customer, creditAccountRequestDTO);
            genericJTableModel.addNewRow(creditAccTableModelResponse);
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            setNewAccount(false);
        }
    }

    @Override
    protected void JButtonOrganizationAC_actionPerformed(ActionEvent event) {

    }

    public void JButtonGenerateReport_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, 1);

            // compute new amount
            String report = creditFrmController.getReport(accnr);
            GenericJDialog billFrm = new GenericJDialog(this, report);
            billFrm.setBounds(450, 20, 400, 350);
            billFrm.show();
        }
    }

    @Override
    public void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding deposit amount for the current mane
            GenericJDialog_Deposit dep = new GenericJDialog_Deposit(this, null, name);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            String ccNumber = (String) genericJTableModel.getModel().getValueAt(selection, 1);
            Double deposit = Double.parseDouble(getAmountDeposit());
            CreditAccTableModelResponse creditAccTableModelResponse = creditFrmController.deposit(ccNumber, deposit);

            Double newamount = creditAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 4);
        }

    }

    @Override
    public void JButtonWithdraw_actionPerformed(java.awt.event.ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String name = (String) genericJTableModel.getModel().getValueAt(selection, 0);

            //Show the dialog for adding withdraw amount for the current mane
            GenericJDialog_Withdraw wd = new GenericJDialog_Withdraw(this, null, name);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount

            String ccNumber = (String) genericJTableModel.getModel().getValueAt(selection, 1);
            Double deposit = Double.parseDouble(getAmountWithdraw());
            CreditAccTableModelResponse creditAccTableModelResponse = creditFrmController.charge(ccNumber, deposit);

            Double newamount = creditAccTableModelResponse.getAmount();
            genericJTableModel.getModel().setValueAt(String.valueOf(newamount), selection, 4);
            if (newamount < 0) {
                JOptionPane.showMessageDialog(getDefaultGUIComponents().getJButton_Withdraw(), " " + name + " Your balance is negative: $" + String.valueOf(newamount) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
            }
        }

    }

    @Override
    protected void JButtonAddinterest_actionPerformed(ActionEvent event) {

    }

}
