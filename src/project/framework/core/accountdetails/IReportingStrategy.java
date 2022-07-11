package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.IAccount;

public interface IReportingStrategy {
    public String generateReport(IAccount iAccount);
}
