package project.framework.core.accountdetails.model.service;

import project.framework.core.accountdetails.IInterestCalculationStrategy;
import project.framework.core.accountdetails.model.account.IAccount;

public class DefaultInterestCalculationStrategy implements IInterestCalculationStrategy {

    @Override
    public double calculateInterest(IAccount iAccount) {
        return iAccount.getInterestRate();
    }
}
