package project.framework.gui;

import project.framework.context.FactoryServiceRetriever;
import project.framework.core.accountdetails.AbstractAccountService;
import project.framework.gui.defaults.DefaultGUIComponents;
import project.framework.gui.defaults.dialogs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public abstract class AbstractDefaultFrameworkGUI<T> implements IDisplayFrameGUI<T, DefaultGUIComponents> {

    private String frameTitle;
    private JFrame jFrame;
    private JScrollPane defaultJScrollPane;

    protected GenericJTableModel<T> genericJTableModel; // table wrapper
    protected DefaultGUIComponents defaultGUIComponents; // default gui components
    protected AbstractAccountService abstractAccountService; // service

    protected GenericDefaultJDialogViewHolder genericDefaultJDialogViewHolder = new GenericDefaultJDialogViewHolder();

    public AbstractDefaultFrameworkGUI(String frameTitle, GenericJTableModel<T> genericAccountJTableModel) {
        this.frameTitle = frameTitle;
        this.genericJTableModel = genericAccountJTableModel;
        this.onInit();
        setJTableForJScrollPane(this.genericJTableModel);
    }

    public AbstractDefaultFrameworkGUI(String frameTitle) {
        this.frameTitle = frameTitle;
        this.onInit();
    }

    private void onInit() {
        abstractAccountService = FactoryServiceRetriever.getService(AbstractAccountService.class);
        this.onCreateJFrame(this.frameTitle);
        defaultGUIComponents = new DefaultGUIComponents(this);
        this.bindGUIComponents();

    }

    @Override
    public void setJTableForJScrollPane(GenericJTableModel<T> genericJTableModel) {
        this.genericJTableModel = genericJTableModel;
        this.defaultJScrollPane = new JScrollPane();
        defaultGUIComponents.setGenericJTableForJScrollPane(this.defaultJScrollPane, genericJTableModel);
    }

    @Override
    public void addDataToGenericJTableModel(T t) {
        genericJTableModel.addNewRow(t);
    }

    @Override
    public void bindGUIComponents() {
        jFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                onExitApplication();
            }
        });
        defaultGUIComponents.getJButton_Exit().addActionListener(e -> System.exit(0));
        defaultGUIComponents.getJButton_PersonalAC().addActionListener(e -> {
            onCreatePersonalAccountButtonClicked(e);
        });
        defaultGUIComponents.getJButton_OrganizationAC().addActionListener(e -> {
            onCreateOrganizationAccountButtonClicked(e);
        });
        defaultGUIComponents.getJButton_Deposit().addActionListener(e -> {
            onDepositButtonClicked(e);
        });
        defaultGUIComponents.getJButton_Withdraw().addActionListener(e -> onWithdrawButtonClicked(e));
        defaultGUIComponents.getJButton_Addinterest().addActionListener(e -> onAddInterestButtonClicked(e));
        defaultGUIComponents.getJButton_Report().addActionListener(e -> onGenerateReportButtonClicked(e));
    }

    public DefaultGUIComponents getDefaultGUIComponents() {
        return defaultGUIComponents;
    }

    @Override
    public JFrame onCreateJFrame(String frameTitle) {
        JFrame jFrame = new JFrame();
        jFrame.setTitle(frameTitle);
        jFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        jFrame.getContentPane().setLayout(new BorderLayout(0, 0));
        jFrame.setSize(675, 410);
        jFrame.setVisible(false);
        this.jFrame = jFrame;
        return jFrame;
    }

    @Override
    public void onExitApplication() {
        try {
            this.jFrame.setVisible(false);    // hide the Frame
            this.jFrame.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {

        }
    }

    @Override
    public JFrame getCurrJFrame() {
        return this.jFrame;
    }

    public void onCreatePersonalAccountButtonClicked(ActionEvent actionEvent) {
        GenericJDialog_AddPersonalAcc gDialogAddPAC = new GenericJDialog_AddPersonalAcc(getCurrJFrame(), "Add personal account");
        genericDefaultJDialogViewHolder.setGenericJDialog_addPersonalAcc(gDialogAddPAC);
        gDialogAddPAC.setBounds(450, 20, 300, 330);
        gDialogAddPAC.show();
        onAddPACDialogDisposed();
    }

    public abstract void onAddPACDialogDisposed();

    public void onCreateOrganizationAccountButtonClicked(ActionEvent actionEvent) {
        GenericJDialog_AddOrganizationAcc gDialogAddOrgAcc = new GenericJDialog_AddOrganizationAcc(getCurrJFrame(), "Add Organization account");
        genericDefaultJDialogViewHolder.setGenericJDialog_addOrganizationAcc(gDialogAddOrgAcc);
        gDialogAddOrgAcc.setBounds(450, 20, 300, 330);
        gDialogAddOrgAcc.show();
        onAddOrgACDialogDisposed();
    }

    public abstract void onAddOrgACDialogDisposed();


    public void onDepositButtonClicked(ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            int uniqueIdIndex = genericJTableModel.getTableModelRowMapper().getUniqueIdIndex();
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, uniqueIdIndex);

            //Show the dialog for adding deposit amount for the current mane
            GenericJDialog_Deposit gDialogDeposit = new GenericJDialog_Deposit(getCurrJFrame(), null, accnr);
            genericDefaultJDialogViewHolder.setGenericJDialog_deposit(gDialogDeposit);
            gDialogDeposit.setBounds(430, 15, 275, 160);
            gDialogDeposit.show();

            onDepositDialogDisposed(selection);
            gDialogDeposit.setAmountDeposit("0");
        }
    }

    public void onDepositDialogDisposed(int selectedRow) {
        GenericJDialog_Deposit gDialogDeposit = genericDefaultJDialogViewHolder.getGenericJDialog_deposit();

        // compute new amount
        Double deposit = Double.parseDouble(gDialogDeposit.getAmountDeposit());
        String accnr = gDialogDeposit.getAccnr();

        abstractAccountService.depositMoney(accnr, deposit);
        double currentBalance = abstractAccountService.getCurrentBalance(accnr);

        // set new amount to selection model
        int defaultValueIndex = genericJTableModel.getTableModelRowMapper().getDefaultValueIndex();
        genericJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, defaultValueIndex);
    }

    public void onWithdrawButtonClicked(ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            int uniqueIdIndex = genericJTableModel.getTableModelRowMapper().getUniqueIdIndex();
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, uniqueIdIndex);

            //Show the dialog for adding deposit amount for the current mane
            GenericJDialog_Withdraw gDialogWithdraw = new GenericJDialog_Withdraw(getCurrJFrame(), null, accnr);
            genericDefaultJDialogViewHolder.setGenericJDialog_withdraw(gDialogWithdraw);
            gDialogWithdraw.setBounds(430, 15, 275, 160);
            gDialogWithdraw.show();

            onWithdrawDialogDisposed(selection);
            gDialogWithdraw.setAmountWithdraw("0");
        }
    }

    public void onWithdrawDialogDisposed(int selectedRow) {
        GenericJDialog_Withdraw gDialogWithdraw = genericDefaultJDialogViewHolder.getGenericJDialog_withdraw();

        // compute new amount
        Double withdraw = Double.parseDouble(gDialogWithdraw.getAmountWithdraw());
        String accnr = gDialogWithdraw.getAccnr();
        abstractAccountService.withdrawMoney(accnr, withdraw);
        double currentBalance = abstractAccountService.getCurrentBalance(accnr);

        // set new amount to selection model
        int defaultValueIndex = genericJTableModel.getTableModelRowMapper().getDefaultValueIndex();
        genericJTableModel.getModel().setValueAt(String.valueOf(currentBalance), selectedRow, defaultValueIndex);
        if (currentBalance < 0) {
            JButton jButton_withdraw = getDefaultGUIComponents().getJButton_Withdraw();
            JOptionPane.showMessageDialog(jButton_withdraw, " Account " + accnr + " : balance is negative: $" + String.valueOf(currentBalance) + " !", "Warning: negative balance", JOptionPane.WARNING_MESSAGE);
        }

    }

    public void onAddInterestButtonClicked(ActionEvent event) {
        GenericJDialog_AddInterest gJDialogInterest = new GenericJDialog_AddInterest(getCurrJFrame(), null, null);
        gJDialogInterest.setBounds(430, 15, 275, 140);
        gJDialogInterest.show();

        String interestRate = gJDialogInterest.getInterestRate();
        if (null != interestRate && interestRate.length() > 0) {
            Double _interest = Double.parseDouble(gJDialogInterest.getInterestRate());
            abstractAccountService.addInterest(_interest);
        }
    }

    public void onGenerateReportButtonClicked(ActionEvent event) {
        // get selected name
        int selection = genericJTableModel.getjTable().getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            int uniqueIdIndex = genericJTableModel.getTableModelRowMapper().getUniqueIdIndex();
            String accnr = (String) genericJTableModel.getModel().getValueAt(selection, uniqueIdIndex);

            // compute new amount
            String report = abstractAccountService.generateReport(accnr);
            GenericJDialog billFrm = new GenericJDialog(getCurrJFrame(), report);
            billFrm.setBounds(450, 20, 400, 350);
            billFrm.show();
        }
    }

}
