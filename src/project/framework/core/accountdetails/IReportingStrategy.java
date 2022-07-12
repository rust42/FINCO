package project.framework.core.accountdetails;

import project.framework.core.accountdetails.model.account.IAccount;

public interface IReportingStrategy<T extends IAccount> {
    public String generateReport(T t);
}
