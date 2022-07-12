package project.ccard.app.services;

import project.framework.core.accountdetails.model.account.IAccount;
import project.framework.core.accountdetails.model.service.DefaultReportingStrategy;

public class CCReportingStrategy extends DefaultReportingStrategy {

    @Override
    public String generateReport(IAccount iAccount) {
        return null;
    }
}
