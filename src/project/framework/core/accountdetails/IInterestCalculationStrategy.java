package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.IAccount;

public interface IInterestCalculationStrategy {
    public double calculateInterest(IAccount iAccount);
}
