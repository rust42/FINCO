package project.framework.gui.defaults;

import project.framework.core.accountdetails.model.account.Account;
import project.framework.gui.AbstractDefaultFrameworkGUI;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.dialogs.GenericJDialog_AddOrganizationAcc;
import project.framework.gui.defaults.dialogs.GenericJDialog_AddPersonalAcc;
import project.framework.gui.defaults.modal.FormInputUtil;
import project.framework.gui.defaults.modal.OrgAFormInputModal;
import project.framework.gui.defaults.modal.PAFormInputModal;

import javax.swing.*;

public class FincoFrame extends AbstractDefaultFrameworkGUI<Account> {

    public FincoFrame(GenericJTableModel<Account> accountGenericJTableModel) {
        super("Finco Application", accountGenericJTableModel);
    }

    @Override
    public void onAddPACDialogDisposed() {
        GenericJDialog_AddPersonalAcc gDialogAddPAC = genericDefaultJDialogViewHolder.getGenericJDialog_addPersonalAcc();
        if (gDialogAddPAC.isNewaccount()) {
            PAFormInputModal paFormInputModal = gDialogAddPAC.getPAFormInputModal();

            Account iAccount = FormInputUtil.createIAccountFromPAFormInputModal(paFormInputModal);
            try {
                abstractAccountService.addAccount(iAccount);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            addDataToGenericJTableModel(iAccount);
            genericJTableModel.resetJTableAnchorSelection();
            gDialogAddPAC.setNewaccount(false);
        }
    }

    @Override
    public void onAddOrgACDialogDisposed() {
        GenericJDialog_AddOrganizationAcc gDialogAddOrgAcc = genericDefaultJDialogViewHolder.getGenericJDialog_addOrganizationAcc();

        if (gDialogAddOrgAcc.isNewaccount()) {
            OrgAFormInputModal orgAFormInputModal = gDialogAddOrgAcc.getOrgAFormInputModal();

            Account iAccount = FormInputUtil.createIAccountFromOrgFormInputModal(orgAFormInputModal);
            try {
                abstractAccountService.addAccount(iAccount);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            addDataToGenericJTableModel(iAccount);
            genericJTableModel.resetJTableAnchorSelection();
        }
        gDialogAddOrgAcc.setNewaccount(false);
    }

//    @Override
//    public void onDepositDialogDisposed(int selectedRow) {
//        GenericJDialog_Deposit gDialogDeposit = genericDefaultJDialogViewHolder.getGenericJDialog_deposit();
//
//        // compute new amount
//        Double deposit = Double.parseDouble(gDialogDeposit.getAmountDeposit());
//        String accnr = gDialogDeposit.getAccnr();
//
//        abstractAccountService.depositMoney(accnr, deposit);
//        double currentBalance = abstractAccountService.getCurrentBalance(accnr);
//
//        // set new amount to selection model
//        int defaultValueIndex = genericJTableModel.getTableModelRowMapper().getDefaultValueIndex();
//        genericJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, defaultValueIndex);
//    }
//
//    @Override
//    public void onWithdrawDialogDisposed(int selectedRow) {
//        GenericJDialog_Withdraw gDialogWithdraw = genericDefaultJDialogViewHolder.getGenericJDialog_withdraw();
//
//        // compute new amount
//        Double withdraw = Double.parseDouble(gDialogWithdraw.getAmountWithdraw());
//        String accnr = gDialogWithdraw.getAccnr();
//        abstractAccountService.withdrawMoney(accnr, withdraw);
//        double currentBalance = abstractAccountService.getCurrentBalance(accnr);
//
//        // set new amount to selection model
//        int defaultValueIndex = genericJTableModel.getTableModelRowMapper().getDefaultValueIndex();
//        genericJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, defaultValueIndex);
//        if (currentBalance < 0) {
//            JButton jButton_withdraw = getDefaultGUIComponents().getJButton_Withdraw();
//            JOptionPane.showMessageDialog(jButton_withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(currentBalance) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
//        }
//
//    }

}
