package project.ccard.app.view;

import project.ccard.app.controller.CreditFrmController;
import project.ccard.app.model.CreditAccTableModelResponse;
import project.ccard.app.model.CreditTableResponseModelMapper;
import project.framework.gui.AbstractDefaultFrameworkGUI;
import project.framework.gui.GenericJTableModel;
import project.framework.gui.defaults.DefaultGUIComponents;
import project.framework.gui.defaults.modal.PAFormInputModal;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * A basic JFC based application.
 **/
public class CardFrame extends AbstractDefaultFrameworkGUI<CreditAccTableModelResponse> {

    private GenericJTableModel<CreditAccTableModelResponse> genericJTableModel;
    private CreditFrmController creditFrmController;

    public CardFrame() {
        super("Credit Card Processing Application.");
        this.creditFrmController = new CreditFrmController();
        genericJTableModel = new GenericJTableModel<>(new CreditTableResponseModelMapper());
        setJTableForJScrollPane(genericJTableModel);

        // customize buttons and their functionality
        DefaultGUIComponents defaultGUIComponents = getDefaultGUIComponents();
        JButton jButton_personalAC = defaultGUIComponents.getJButton_PersonalAC();
        jButton_personalAC.setText("Add Credit-card account");

        defaultGUIComponents.getJButton_Report().setText("Generate Monthly Report");
        defaultGUIComponents.getJButton_Deposit().setText("Deposit");
        defaultGUIComponents.getJButton_Withdraw().setText("Charge");

        defaultGUIComponents.getJButton_OrganizationAC().setVisible(false);
        defaultGUIComponents.getJButton_Addinterest().setVisible(false);
    }

    @Override
    public void onCreatePersonalAccountButtonClicked(ActionEvent actionEvent) {
        JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(getCurrJFrame());
        genericDefaultJDialogViewHolder.setGenericJDialog_addPersonalAcc(ccac);
        ccac.setBounds(450, 20, 300, 380);
        ccac.show();
        onAddPACDialogDisposed();
    }

    @Override
    public void onAddPACDialogDisposed() {
        JDialog_AddCCAccount gDialogAddCCAccount = (JDialog_AddCCAccount) genericDefaultJDialogViewHolder.getGenericJDialog_addPersonalAcc();

        if (gDialogAddCCAccount.isNewaccount()) {
            PAFormInputModal paFormInputModal = gDialogAddCCAccount.getPAFormInputModal();
            String accType = gDialogAddCCAccount.getAccountType();
            String ccNumber = gDialogAddCCAccount.getCcnumber();
            String expDate = gDialogAddCCAccount.getExpdate();
            try {
                CreditAccTableModelResponse creditAccTableModelResponse = this.creditFrmController.addCreditCardAccount(paFormInputModal, ccNumber, accType, expDate);
                addDataToGenericJTableModel(creditAccTableModelResponse);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(getCurrJFrame(), e.getMessage());
                return;
            }
            genericJTableModel.getjTable().getSelectionModel().setAnchorSelectionIndex(-1);
            gDialogAddCCAccount.setNewaccount(false);
        }
    }

    @Override
    public void onAddOrgACDialogDisposed() {
        // Do nothing, this options isn't available
    }

}
