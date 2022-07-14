package project.framework.gui;

import project.framework.gui.defaults.dialogs.*;

public class GenericDefaultJDialogViewHolder<P extends GenericJDialog_AddPersonalAcc, O extends GenericJDialog_AddOrganizationAcc, D extends GenericJDialog_Deposit, W extends GenericJDialog_Withdraw, I extends GenericJDialog_AddInterest> {

    protected P genericJDialog_addPersonalAcc;
    protected O genericJDialog_addOrganizationAcc;
    protected D genericJDialog_deposit;
    protected W genericJDialog_withdraw;
    protected I genericJDialog_addInterest;

    public P getGenericJDialog_addPersonalAcc() {
        return genericJDialog_addPersonalAcc;
    }

    public void setGenericJDialog_addPersonalAcc(P genericJDialog_addPersonalAcc) {
        this.genericJDialog_addPersonalAcc = genericJDialog_addPersonalAcc;
    }

    public O getGenericJDialog_addOrganizationAcc() {
        return genericJDialog_addOrganizationAcc;
    }

    public void setGenericJDialog_addOrganizationAcc(O genericJDialog_addOrganizationAcc) {
        this.genericJDialog_addOrganizationAcc = genericJDialog_addOrganizationAcc;
    }

    public D getGenericJDialog_deposit() {
        return genericJDialog_deposit;
    }

    public void setGenericJDialog_deposit(D genericJDialog_deposit) {
        this.genericJDialog_deposit = genericJDialog_deposit;
    }

    public W getGenericJDialog_withdraw() {
        return genericJDialog_withdraw;
    }

    public void setGenericJDialog_withdraw(W genericJDialog_withdraw) {
        this.genericJDialog_withdraw = genericJDialog_withdraw;
    }

    public I getGenericJDialog_addInterest() {
        return genericJDialog_addInterest;
    }

    public void setGenericJDialog_addInterest(I genericJDialog_addInterest) {
        this.genericJDialog_addInterest = genericJDialog_addInterest;
    }
}
