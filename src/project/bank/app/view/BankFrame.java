package project.bank.app.view;

import project.bank.app.controller.BankFrmController;
import project.bank.app.model.helper.AccountType;
import project.bank.app.model.ui.BankAccTableModelResponse;
import project.bank.app.model.ui.BankTableResponseModelMapper;
import project.framework.gui.AbstractDefaultFrameworkGUI;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.modal.OrgAFormInputModal;
import project.framework.gui.defaults.modal.PAFormInputModal;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BankFrame extends AbstractDefaultFrameworkGUI<BankAccTableModelResponse> {
    private GenericJTableModel<BankAccTableModelResponse> genericBankJTableModel;
    private BankFrmController bankFrmController;

    public BankFrame() {
        super("Banking Application");
        this.bankFrmController = new BankFrmController();
        genericBankJTableModel = new GenericJTableModel<>(new BankTableResponseModelMapper());
        super.setJTableForJScrollPane(genericBankJTableModel);
    }

    @Override
    public void onCreatePersonalAccountButtonClicked(ActionEvent actionEvent) {
        JDialog_AddPAcc pac = new JDialog_AddPAcc(getCurrJFrame());
        pac.setBounds(450, 20, 300, 330);
        genericDefaultJDialogViewHolder.setGenericJDialog_addPersonalAcc(pac);
        pac.show();
        onAddPACDialogDisposed();
    }

    @Override
    public void onAddPACDialogDisposed() {
        JDialog_AddPAcc gDialogAddPAC = (JDialog_AddPAcc) genericDefaultJDialogViewHolder.getGenericJDialog_addPersonalAcc();

        if (gDialogAddPAC.isNewaccount()) {
            PAFormInputModal paFormInputModal = gDialogAddPAC.getPAFormInputModal();
            AccountType accountTypeEnum = gDialogAddPAC.getAccountType().equals(AccountType.CHECKING.toString()) ? AccountType.CHECKING : AccountType.SAVING;

            try {
                BankAccTableModelResponse bankAccTableModelResponse = this.bankFrmController.addBankPersonalAccount(paFormInputModal, accountTypeEnum);
                addDataToGenericJTableModel(bankAccTableModelResponse);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            genericBankJTableModel.resetJTableAnchorSelection();
            gDialogAddPAC.setNewaccount(false);
        }
    }

    @Override
    public void onCreateOrganizationAccountButtonClicked(ActionEvent actionEvent) {
        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(getCurrJFrame());
        genericDefaultJDialogViewHolder.setGenericJDialog_addOrganizationAcc(pac);
        pac.setBounds(450, 20, 300, 330);
        pac.show();
        onAddOrgACDialogDisposed();
    }

    @Override
    public void onAddOrgACDialogDisposed() {
        JDialog_AddCompAcc gDialogAddOrgAcc = (JDialog_AddCompAcc) genericDefaultJDialogViewHolder.getGenericJDialog_addOrganizationAcc();
        if (gDialogAddOrgAcc.isNewaccount()) {

            OrgAFormInputModal orgAFormInputModal = gDialogAddOrgAcc.getOrgAFormInputModal();
            AccountType accountTypeEnum = gDialogAddOrgAcc.getAccountType().equals(AccountType.CHECKING.toString()) ? AccountType.CHECKING : AccountType.SAVING;

            try {
                BankAccTableModelResponse bankAccTableModelResponse = this.bankFrmController.addBankCompanyAccount(orgAFormInputModal, accountTypeEnum);
                addDataToGenericJTableModel(bankAccTableModelResponse);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            genericBankJTableModel.resetJTableAnchorSelection();
            gDialogAddOrgAcc.setNewaccount(false);
        }
    }

//    @Override
//    public void onDepositDialogDisposed(int selectedRow) {
//        GenericJDialog_Deposit gDialogDeposit = genericDefaultJDialogViewHolder.getGenericJDialog_deposit();
//
//        // compute new amount
//        Double deposit = Double.parseDouble(gDialogDeposit.getAmountDeposit());
//        String accnr = gDialogDeposit.getAccnr();
//
//        BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.deposit(accnr, deposit);
//
//        // set new amount to selection model
//        Double currentBalance = bankAccTableModelResponse.getAmount();
//        this.genericBankJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, 5);
//
//    }
//
//    @Override
//    public void onWithdrawDialogDisposed(int selectedRow) {
//        GenericJDialog_Withdraw gDialogWithdraw = genericDefaultJDialogViewHolder.getGenericJDialog_withdraw();
//
//        // compute new amount
//        Double withdraw = Double.parseDouble(gDialogWithdraw.getAmountWithdraw());
//        String accnr = gDialogWithdraw.getAccnr();
//
//        BankAccTableModelResponse bankAccTableModelResponse = bankFrmController.withdraw(accnr, withdraw);
//        Double currentBalance = bankAccTableModelResponse.getAmount();
//
//        // set new amount to selection model
//        this.genericBankJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, 5);
//        if (currentBalance < 0) {
//            JButton jButton_withdraw = getDefaultGUIComponents().getJButton_Withdraw();
//            JOptionPane.showMessageDialog(jButton_withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(currentBalance) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
//        }
//    }

}
