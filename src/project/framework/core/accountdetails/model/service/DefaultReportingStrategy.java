package project.framework.core.accountdetails.model.service;

import project.framework.core.accountdetails.IReportingStrategy;
import project.framework.core.accountdetails.model.account.Entry;
import project.framework.core.accountdetails.model.account.IAccount;

import java.util.Set;

public class DefaultReportingStrategy<T extends IAccount> implements IReportingStrategy<T> {
    @Override
    public String generateReport(T iAccount) {
        Set<Entry> entries = iAccount.getEntries();
        String defaultReport = "Report for id: " + iAccount.getUniqueId() + " \n";
        defaultReport += "Current balance: " + iAccount.getBalance() + " \n";
        defaultReport += "--------------------------- \n";
        for (Entry entry : entries) {
            defaultReport += "Date " + entry.getDate() + " amount " + entry.getTxAmount() + " entry-type " + entry.getTransactionType();
            defaultReport += "\n";
        }
        return defaultReport;
    }
}
